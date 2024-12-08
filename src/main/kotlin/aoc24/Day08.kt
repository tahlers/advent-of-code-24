package aoc24

object Day08 {

    data class Pos(val x: Int, val y: Int)

    data class Grid(val xMax: Int, val yMax: Int, val antennas: Map<Pos, Char>) {
        fun inGrid(pos: Pos) = pos.x in 0..xMax && pos.y in 0..yMax
    }

    fun calculateSolutionOne(input: String): Int {
        val grid = parseInput(input.trim())
        val antinodes = antinodes(grid)
        return antinodes.size
    }

    fun calculateSolutionTwo(input: String): Int {
        val grid = parseInput(input.trim())
        val antinodes = antinodes(grid, true)
        return antinodes.size
    }

    private fun antinodes(grid: Grid, withHarmonics: Boolean = false): Set<Pos> {
        val antennaPosByName = grid.antennas.entries.groupBy({ it.value }, { it.key })
        val antinodes = antennaPosByName.flatMap { (_, antennaPos) ->
            if (withHarmonics) {
                antinodesForAntennaListHarmonic(antennaPos, grid)
            } else {
                antinodesForAntennaList(antennaPos, grid)
            }
        }
        return antinodes.toSet()
    }

    private fun antinodesForAntennaList(antennas: List<Pos>, grid: Grid): Set<Pos> {
        val combinations = pairCombinations(antennas)
        val antinodes = combinations.flatMap {
            listOf(
                calcNextAntinote(it.first, it.second),
                calcNextAntinote(it.second, it.first)
            )
        }
        return antinodes.filter { grid.inGrid(it) }.toSet()
    }

    private fun antinodesForAntennaListHarmonic(antennas: List<Pos>, grid: Grid): Set<Pos> {
        val combinations = pairCombinations(antennas)
        val antinodes = combinations.flatMap {
            val forwardHarmonics = generateHarmonics(it.first, it.second, grid)
            val backwardHarmonics = generateHarmonics(it.second, it.first, grid)
            forwardHarmonics + backwardHarmonics
        }
        return antinodes.toSet()
    }

    private fun generateHarmonics(pos1: Pos, pos2: Pos, grid: Grid): List<Pos> {
        val seq = generateSequence(pos1 to pos2) { (first, second) ->
            val next = calcNextAntinote(first, second)
            if (grid.inGrid(next)) second to next else null
        }
        return seq.map { it.second }.toList()
    }


    private fun calcNextAntinote(pos1: Pos, pos2: Pos): Pos = Pos(
        pos1.x + (pos2.x - pos1.x) * 2,
        pos1.y + (pos2.y - pos1.y) * 2
    )

    private fun <T> pairCombinations(input: List<T>): List<Pair<T, T>> {
        tailrec fun internal(input1: List<T>, input2: List<T>, results: List<Pair<T, T>>): List<Pair<T, T>> {
            if (input1.isEmpty() && input2.isEmpty()) return results
            if (input1.isEmpty()) return internal(input2, input2.drop(2), results)
            val nextFirst = input1.first()
            val newPairs = input2.map { nextFirst to it }
            return internal(input1.drop(1), input2.drop(1), results + newPairs)
        }
        if (input.size < 2) return emptyList()
        return internal(input, input.drop(1), emptyList())
    }

    private fun parseInput(input: String): Grid {
        val lines = input.trim().lines()
        val maxX = lines.first().length - 1
        val maxY = lines.size - 1
        val antennas = lines.flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, c ->
                when (c) {
                    '.' -> null
                    else -> Pos(x, y) to c
                }
            }
        }
        return Grid(maxX, maxY, antennas.toMap())
    }
}