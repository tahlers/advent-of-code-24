package aoc24

object Day04 {

    data class Pos(val x: Int, val y: Int) {
        fun adjacentX(): List<List<Pos>> =
            listOf(
                listOf( Pos(x - 1, y - 1), this, Pos(x + 1, y + 1)),
                listOf( Pos(x + 1, y - 1), this, Pos(x - 1, y + 1)),
                listOf( Pos(x - 1, y + 1), this, Pos(x + 1, y - 1)),
                listOf( Pos(x + 1, y + 1), this, Pos(x - 1, y - 1)),
            )

        fun nextInDirections(length: Int = 3): List<List<Pos>> {
            return listOf(
                (1..length).map { Pos(x - it, y - it) },
                (1..length).map { Pos(x, y - it) },
                (1..length).map { Pos(x + it, y - it) },
                (1..length).map { Pos(x - it, y) },
                (1..length).map { Pos(x + it, y) },
                (1..length).map { Pos(x - it, y + it) },
                (1..length).map { Pos(x, y + it) },
                (1..length).map { Pos(x + it, y + it) },
            )
        }
    }

    data class Grid(val xSize: Int, val ySize: Int, val entries: Map<Pos, Char>)

    fun calculateSolutionOne(input: String): Int {
        val grid = parseGrid(input)
        val startPositions = grid.entries.filterValues { it == 'X' }
            .map { it.key }
        val xmasEntries = startPositions
            .map { searchInDirection(grid, it) }
        return xmasEntries.sum()
    }

    fun calculateSolutionTwo(input: String): Int {
        val grid = parseGrid(input)
        val startPositions = grid.entries.filterValues { it == 'A' }
            .map { it.key }
        val xmasEntries = startPositions
            .filter { searchCross(grid, it) }
        return xmasEntries.size
    }

    private fun searchInDirection(grid: Grid, pos: Pos): Int {
        if (grid.entries[pos] != 'X') return 0
        val directions = pos.nextInDirections()
        val directionChars = directions.map { dirPositions ->
            dirPositions.map { grid.entries.getOrDefault(it, '-') }
        }.map { it.joinToString("") }
        return directionChars.filter { it == "MAS" }.size
    }

    private fun searchCross(grid: Grid, pos: Pos): Boolean {
        if (grid.entries[pos] != 'A') return false
        val directions = pos.adjacentX()
        val directionChars = directions.map { dirPositions ->
            dirPositions.map { grid.entries.getOrDefault(it, '-') }
        }.map { it.joinToString("") }
        return directionChars.filter { it == "MAS" }.size == 2
    }

    private fun parseGrid(input: String): Grid {
        val lines = input.trim().lines()
        val xSize = lines[0].length
        val ySize = lines.size
        val gridPairs = lines.flatMapIndexed { y, line ->
            line.mapIndexed { x, chr ->
                Pos(x, y) to chr
            }
        }
        return Grid(xSize, ySize, gridPairs.toMap())
    }
}