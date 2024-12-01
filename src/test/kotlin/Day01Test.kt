import aoc24.Day01
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day01Test : FunSpec({

    val sample = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
   """.trimIndent()


    test("calculate distanceSum test") {
        val result = Day01.calculateSumOfDistances(sample)
        result shouldBe 11
    }

    test("calculate distanceSum solution one") {
        val input = this.javaClass.getResource("/day01.txt")!!.readText()
        val result = Day01.calculateSumOfDistances(input)
        result shouldBe 1651298
    }

    test("calculate similarity score test") {
        val result = Day01.calculateSimilarityScoreSum(sample)
        result shouldBe 31
    }

    test("calculate similarity score solution two") {
        val input = this.javaClass.getResource("/day01.txt")!!.readText()
        val result = Day01.calculateSimilarityScoreSum(input)
        result shouldBe 21306195
    }
})