package aoc24

import kotlin.math.abs

object Day01 {

    private val whitespaceRegex = """\s+""".toRegex()

    fun calculateSumOfDistances(input: String): Int {
        val (aNumbers, bNumbers) = parseInput(input)
        val aNumbersSorted = aNumbers.sorted()
        val bNumbersSorted = bNumbers.sorted()
        val distances = aNumbersSorted.zip(bNumbersSorted) { a, b -> abs(a - b) }
        return distances.sum()
    }

    fun calculateSimilarityScoreSum(input: String ): Int {
        val (aNumbers, bNumbers) = parseInput(input)
        val frequencies = bNumbers.groupBy { it }
            .mapValues { it.value.size }
        val similarityScores = aNumbers.map { it * frequencies.getOrDefault(it, 0) }
        return similarityScores.sum()
    }

    private fun parseInput(input: String ): Pair<List<Int>, List<Int>> {
        val pairs = input.trim().lines().map { line ->
            val (a, b) = line.split(whitespaceRegex)
            a.toInt() to b.toInt()
        }
        val aNumbers = pairs.map { it.first }
        val bNumbers = pairs.map { it.second }
        return Pair(aNumbers, bNumbers)
    }
}