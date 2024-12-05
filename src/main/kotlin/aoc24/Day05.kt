package aoc24

object Day05 {

    data class PrintingRules(val orderings: Map<Int, List<Int>>, val updates: List<List<Int>>)

    fun calculateSolutionOne(input: String): Int {
        val rules = parseInput(input)
        val correctOrderUpdates = rules.updates.filter { hasCorrectOrder(rules.orderings, it) }
        val midUpdateNumbers = correctOrderUpdates.map { it[it.size / 2] }
        return midUpdateNumbers.sum()
    }

    fun calculateSolutionTwo(input: String): Int {
        val rules = parseInput(input)
        val falseOrderUpdates = rules.updates.filterNot { hasCorrectOrder(rules.orderings, it) }
        val ordered = falseOrderUpdates.map { orderUpdate(rules.orderings, it) }
        val midUpdateNumbers = ordered.map { it[it.size / 2] }
        return midUpdateNumbers.sum()
    }

    private tailrec fun hasCorrectOrder(ordering: Map<Int, List<Int>>, update: List<Int>): Boolean {
        if (update.isEmpty()) return true
        val remaining = update.drop(1)
        val current = update.first()
        return if (doesNotViolateOrdering(ordering, current, remaining)) {
            hasCorrectOrder(ordering, remaining)
        } else false
    }

    private tailrec fun orderUpdate(
        ordering: Map<Int, List<Int>>,
        update: List<Int>,
        orderedUpdate: List<Int> = emptyList()
    ): List<Int> {
        if (update.isEmpty()) return orderedUpdate
        val next = update.first { candidate ->
            doesNotViolateOrdering(ordering, candidate, update)
        }
        return orderUpdate(ordering, update - next, orderedUpdate + next)
    }

    private fun doesNotViolateOrdering(
        ordering: Map<Int, List<Int>>,
        numberToCheck: Int,
        listToCheck: List<Int>
    ) = listToCheck.none { numberToCheck in ordering.getOrDefault(it, emptyList()) }

    private fun parseInput(input: String): PrintingRules {
        val (orderingInput, updatesInput) = input.trim().split("\n\n")
        val orderings = orderingInput.lines()
            .map { line ->
                val (a, b) = line.split('|')
                a.toInt() to b.toInt()
            }.groupBy({ it.first }, { it.second })
        val updates = updatesInput.lines().map { line -> line.split(',').map(String::toInt) }
        return PrintingRules(orderings, updates)
    }
}