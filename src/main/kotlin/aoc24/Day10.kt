package aoc24

typealias Trail = List<Pair<Day10.Pos, Int>>

object Day10 {

    data class Pos(val x: Int, val y: Int) {
        fun adjacent(): List<Pos> = listOf(
            Pos(x, y - 1), Pos(x + 1, y), Pos(x, y + 1), Pos(x - 1, y)
        )
    }

    data class Grid(val xMax: Int, val yMax: Int, val heights: Map<Pos, Int>) {
        fun inGrid(pos: Pos) = pos.x in 0..xMax && pos.y in 0..yMax
    }

    fun calculateSolutionOne(input: String): Int {
        val grid = parseInput(input)
        val startingPos = grid.heights.entries
            .filter { it.value == 0 }
            .map { listOf( it.key to  it.value)  }
        val trails = findTrails(grid, startingPos)
        val trailsByHead = trails.groupBy { it.first().first }
        val trailsByHeadWithLast = trailsByHead.mapValues { it.value.map {it.last()}.toSet() }
        return trailsByHeadWithLast.values.sumOf { it.size }
    }

    fun calculateSolutionTwo(input: String): Int {
        val grid = parseInput(input)
        val startingPos = grid.heights.entries
            .filter { it.value == 0 }
            .map { listOf( it.key to  it.value)  }
        val trails = findTrails(grid, startingPos)
        return trails.size
    }

    private fun findTrails(grid: Grid, trails: List<Trail>, result: List<Trail> = emptyList()): List<Trail> {
        if (trails.isEmpty()) return result
        val (finished, ongoing) = trails.partition { it.last().second == 9 }
        val newTrails = ongoing.flatMap { trail ->
            val currentStep = trail.last()
            val nextSteps = nextPossibleSteps(grid, currentStep.first, currentStep.second)
            nextSteps.map { next -> trail + (next to currentStep.second + 1) }
        }
        return findTrails(grid, newTrails, result + finished)
    }

    private fun nextPossibleSteps(grid: Grid, currentPos: Pos, currentHeight: Int): List<Pos> {
        val possiblePositions = currentPos.adjacent().filter { grid.inGrid(it) }
        return possiblePositions.filter { grid.heights[it] == currentHeight + 1 }
    }

    private fun parseInput(input: String): Grid {
        val lines = input.trim().lines()
        val maxX = lines.first().length - 1
        val maxY = lines.size - 1
        val heights = lines.flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, c -> Pos(x, y) to c.digitToInt() }
        }
        return Grid(maxX, maxY, heights.toMap())
    }
}


