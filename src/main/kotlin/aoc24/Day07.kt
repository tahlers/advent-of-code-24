package aoc24

object Day07 {

    fun calculateSolutionOne(input: String): Long {
        val rules = parseInput(input)
        val possible = rules.filter { rule ->
            possibleResults(rule.second).any { it == rule.first }
        }
        val results = possible.map { it.first }
        return results.sum()
    }

    fun calculateSolutionTwo(input: String): Long {
        val rules = parseInput(input)
        val possible = rules.filter { rule ->
            possibleWithConcat(rule.second).any { it == rule.first }
        }
        val results = possible.map { it.first }
        return results.sum()
    }

    private fun possibleResults(numbers: List<Long>): List<Long> {
        tailrec fun possibleResultsInternal(numbers: List<Long>, results: List<Long>): List<Long> {
            if (numbers.isEmpty()) return results
            val current = numbers.first()
            val newResults = results.flatMap { result -> listOf(result + current, result * current) }
            return possibleResultsInternal(numbers.drop(1), newResults)
        }
        return possibleResultsInternal(numbers.drop(1), listOf(numbers.first()))
    }

    private fun possibleWithConcat(numbers: List<Long>): List<Long> {
        tailrec fun possibleWithConcatInternal(numbers: List<Long>, results: List<Long>): List<Long> {
            if (numbers.isEmpty()) return results
            val current = numbers.first()
            val newResults = results.flatMap { result -> listOf(
                result + current,
                result * current,
                concat(result, current)
            ) }
            return possibleWithConcatInternal(numbers.drop(1), newResults)
        }
        return possibleWithConcatInternal(numbers.drop(1), listOf(numbers.first()))
    }

    private fun concat(a: Long, b: Long) = (a.toString() + b.toString()).toLong()

    private fun parseInput(input: String): List<Pair<Long, List<Long>>> = input.trim().lines()
        .map { line ->
            val result = line.substringBefore(':').toLong()
            val numbers = line.substringAfter(':')
                .trim()
                .split(' ')
                .map(String::toLong)
            result to numbers
        }
}