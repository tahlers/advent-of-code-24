package aoc24

object Day11 {

    fun calculateSolutionOne(input: String): Long {
        val initialStones = parseInput(input)
        val stoneSums = initialStones.map { stoneBlinkedTimes(it, 25) }
        return stoneSums.sum()
    }

    fun calculateSolutionTwo(input: String): Long {
        val initialStones = parseInput(input)
        val stoneSums = initialStones.map { stoneBlinkedTimes(it, 75) }
        return stoneSums.sum()
    }

    private fun stoneBlinkedTimes(
        stone: Long,
        times: Int,
        memory: MutableMap<Pair<Long, Int>, Long> = mutableMapOf()
    ): Long {
        if (times == 0) return 1
        if (memory.containsKey(stone to times)) return memory[stone to times]!!
        return when {
            stone == 0L -> {
                val result = stoneBlinkedTimes(1L, times - 1, memory)
                memory[0L to times] = result
                result
            }

            stone.toString().length % 2 == 0 -> {
                val inputString = stone.toString()
                val length = inputString.length
                val left = inputString.substring(0, length / 2)
                val right = inputString.substring(length / 2, length)
                val leftResult = stoneBlinkedTimes(left.toLong(), times - 1, memory)
                val rightResult = stoneBlinkedTimes(right.toLong(), times - 1, memory)
                memory[stone to times] = leftResult + rightResult
                leftResult + rightResult
            }

            else -> {
                val result = stoneBlinkedTimes(stone * 2024, times - 1, memory)
                memory[stone to times] = result
                result
            }
        }
    }

    private fun parseInput(input: String): List<Long> {
        return input.trim().split(' ').map { it.toLong() }
    }
}
