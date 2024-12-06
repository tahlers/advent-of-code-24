package aoc24

import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.plus
import kotlinx.collections.immutable.toPersistentSet

object Day06 {

    enum class Direction {
        UP, LEFT, DOWN, RIGHT
    }

    data class Pos(val x: Int, val y: Int) {
        fun newPos(dir: Direction): Pos =
            when (dir) {
                Direction.UP -> Pos(x, y - 1)
                Direction.LEFT -> Pos(x - 1, y)
                Direction.RIGHT -> Pos(x + 1, y)
                Direction.DOWN -> Pos(x, y + 1)
            }
    }

    data class Guard(val pos: Pos, val direction: Direction) {
        fun move() = copy(pos = pos.newPos(direction))
        fun turnRight() = copy(
            direction = when (direction) {
                Direction.UP -> Direction.RIGHT
                Direction.RIGHT -> Direction.DOWN
                Direction.DOWN -> Direction.LEFT
                Direction.LEFT -> Direction.UP
            }
        )
    }

    data class Grid(val xSize: Int, val ySize: Int, val obstacles: PersistentSet<Pos>, val guard: Guard) {
        fun inGrid(pos: Pos) = pos.x in 0..<xSize && pos.y in 0..<ySize
    }

    fun calculateSolutionOne(input: String): Int {
        val grid = parseInput(input.trim())
        val path = simulateGuard(grid)
        return path.size
    }

    fun calculateSolutionTwo(input: String): Int {
        val grid = parseInput(input.trim())
        val possiblePositions = simulateGuard(grid) - grid.guard.pos
        val newObstaclePositions = possiblePositions.filter { pos ->
            guardOnLoop(grid.copy(obstacles = grid.obstacles + pos))
        }
        return newObstaclePositions.size
    }

    private fun simulateGuard(grid: Grid, path: PersistentSet<Pos> = persistentSetOf()): Set<Pos> {
        val nextStep = nextStep(grid)
        return if (!grid.inGrid(grid.guard.pos)) {
            return path
        } else {
            simulateGuard(nextStep, path + nextStep.guard.pos)
        }
    }

    private fun guardOnLoop(grid: Grid, guardsPath: PersistentSet<Guard> = persistentSetOf(grid.guard)): Boolean {
        val nextStep = nextStep(grid)
        if (!grid.inGrid(grid.guard.pos)) return false
        return if (nextStep.guard in guardsPath) return true else guardOnLoop(nextStep, guardsPath + nextStep.guard)
    }

    private fun nextStep(grid: Grid): Grid {
        val movedGuard = grid.guard.move()
        return if (movedGuard.pos in grid.obstacles) {
            grid.copy(guard = grid.guard.turnRight())
        } else {
            grid.copy(guard = movedGuard)
        }
    }

    private fun parseInput(input: String): Grid {
        val lines = input.trim().lines()
        val maxX = lines.first().length - 1
        val maxY = lines.size - 1
        val gridElements = lines.flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, c ->
                when (c) {
                    '#' -> false to Pos(x, y)
                    '^' -> true to Pos(x, y)
                    else -> null
                }
            }
        }
        val obstacles = gridElements.filter { !it.first }.map { it.second }.toPersistentSet()
        val guard = Guard(gridElements.first { it.first }.second, Direction.UP)
        return Grid(maxX, maxY, obstacles, guard)
    }
}