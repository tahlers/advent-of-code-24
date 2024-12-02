package aoc24

import kotlin.math.abs
import kotlin.math.sign

object Day02 {

    fun calculateSafeReportCount(input: String): Int {
        val reports = parseReportInput(input)
        val filteredReports = reports.filter { report -> isSafe(report) }
        return filteredReports.size
    }

    fun calculateSafeDamperReportCount(input: String): Int {
        val reports = parseReportInput(input)
        val filteredReports = reports.filter { report -> subReports(report).any { isSafe(it) } }
        return filteredReports.size
    }

    private fun subReports(report: List<Int>): List<List<Int>> =
        report.indices
            .map { reportIndex -> report.filterIndexed{ idx, _ -> idx != reportIndex } }

    private fun isSafe(report: List<Int>): Boolean {
        val isLevelDiffOk = report.zipWithNext()
            .all { (a, b) -> abs(a - b) in 1 .. 3 }
        val directionSet = report.zipWithNext()
            .map { (a, b) -> sign((a - b).toDouble()).toInt() }
            .toSet()
        val isDirectionOk = directionSet.size == 1 && directionSet.first() != 0
        return isLevelDiffOk && isDirectionOk
    }

    private fun parseReportInput(input: String): List<List<Int>> =
        input.trim()
            .lines()
            .map { line ->
                line.split(" ").map { it.toInt() }
            }
}