package aoc24

object Day03 {

    private val mulRegex = """mul\(\d\d?\d?,\d\d?\d?\)""".toRegex()

    fun calculateSolutionOne(input: String): Int {
        val mulResults = mulRegex.findAll(input.trim()).map { matchResult ->
            doMul(matchResult.value)
        }
        return mulResults.sum()
    }

    fun calculateSolutionTwo(input: String): Int {
        val mulResults = mulRegex.findAll(input.trim()).map { matchResult ->
            val before = input.substring(0, matchResult.range.first)
            if (shouldDoMul(before)) doMul(matchResult.value) else 0
        }
        return mulResults.sum()
    }

    private fun doMul(input: String): Int {
        val (a, b) = input.substringAfter("mul(").substringBefore(")").split(',')
        return a.toInt() * b.toInt()
    }

    private fun shouldDoMul(input: String): Boolean {
        val lastDo = input.lastIndexOf("do()")
        val lastDont = input.lastIndexOf("don't()")
        return lastDont <= lastDo
    }
}