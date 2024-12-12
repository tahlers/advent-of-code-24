package aoc24

import kotlinx.collections.immutable.PersistentSet as PSet
import kotlinx.collections.immutable.persistentSetOf as pSetOf
import kotlinx.collections.immutable.toPersistentSet as toPSet

object Day12 {

    data class Pos(val x: Int, val y: Int) {
        fun adjacent(): List<Pos> = listOf(
            Pos(x, y - 1), Pos(x + 1, y), Pos(x, y + 1), Pos(x - 1, y)
        )

        fun distance(other: Pos): Int = kotlin.math.abs(x - other.x) + kotlin.math.abs(y - other.y)
    }

    data class Fence(val inside: Pos, val outside: Pos) {
        fun adjacent(): Pair<Fence, Fence> {
            val insideAdjacent = inside.adjacent() - outside
            val outsideAdjacent = outside.adjacent() - inside
            val adjacentFence = insideAdjacent.flatMap { fencePos ->
                outsideAdjacent.filter { it.distance(fencePos) == 1 }.map { Fence(fencePos, it) }
            }
            return adjacentFence[0] to adjacentFence[1]
        }
    }

    data class Region(val plantType: Char, val areas: PSet<Pos>, val fences: PSet<Fence>)

    data class Grid(val xMax: Int, val yMax: Int, val plants: Map<Pos, Char>)

    fun calculateSolutionOne(input: String): Int {
        val grid = parseInput(input)
        val regions = calcRegions(grid)
        val prices = regions.map { region -> region.areas.size * region.fences.size }
        return prices.sum()
    }

    fun calculateSolutionTwo(input: String): Int {
        val grid = parseInput(input)
        val regions = calcRegions(grid)
        val prices = regions.map { region -> region.areas.size * sidesForRegion(region) }
        return prices.sum()
    }

    private tailrec fun calcRegions(
        grid: Grid, unhandledPlants: Map<Pos, Char> = grid.plants, finishedRegions: Set<Region> = emptySet()
    ): Set<Region> {
        if (unhandledPlants.isEmpty()) return finishedRegions
        val newStartPos = unhandledPlants.keys.first()
        val regionPositions = findSamePlant(grid, pSetOf(newStartPos), pSetOf())
        val fences = fencesForArea(grid, regionPositions)
        val region = Region(grid.plants[newStartPos]!!, regionPositions, fences)
        return calcRegions(grid, unhandledPlants - regionPositions, finishedRegions + region)
    }

    private fun findSamePlant(grid: Grid, current: PSet<Pos>, visited: PSet<Pos>): PSet<Pos> {
        if (current.isEmpty()) return visited
        val newCurrent = current.flatMap { pos ->
            pos.adjacent().filter { grid.plants[it] == grid.plants[pos] }
        }.toPSet()
        return findSamePlant(grid, newCurrent.removeAll(visited), visited.addAll(current))
    }

    private fun fencesForArea(grid: Grid, area: PSet<Pos>): PSet<Fence> {
        return area.flatMap { pos ->
            pos.adjacent().filter { grid.plants[pos] != grid.plants[it] }.map { Fence(pos, it) }
        }.toPSet()
    }

    private fun sidesForRegion(region: Region): Int {
        tailrec fun groupFences(
            ungrouped: PSet<Fence>, current: PSet<Fence>, grouped: PSet<PSet<Fence>>
        ): PSet<PSet<Fence>> {
            if (ungrouped.isEmpty()) return if (current.isEmpty()) grouped else grouped.add(current)
            if (current.isEmpty()) {
                val next = ungrouped.first()
                return groupFences(ungrouped.remove(next), pSetOf(next), grouped)
            }
            val nextPossibleFences =
                current.flatMap { it.adjacent().toList() }.filter { it !in current && it in ungrouped }
            return if (nextPossibleFences.isEmpty()) groupFences(
                ungrouped.removeAll(current), pSetOf(), grouped.add(current)
            )
            else groupFences(ungrouped, current.addAll(nextPossibleFences), grouped)
        }
        return groupFences(region.fences, pSetOf(), pSetOf()).size
    }

    private fun parseInput(input: String): Grid {
        val lines = input.trim().lines()
        val maxX = lines.first().length - 1
        val maxY = lines.size - 1
        val plants = lines.flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, c -> Pos(x, y) to c }
        }
        return Grid(maxX, maxY, plants.toMap())
    }
}