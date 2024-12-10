import aoc24.Day09
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day09Test : FunSpec({

    val sample = """
        2333133121414131402
   """.trimIndent()

    test("calculate solution one test") {
        val result = Day09.calculateSolutionOne(sample)
        result shouldBe 1928L
    }

    test("calculate solution one") {
        val input = this.javaClass.getResource("/Day09.txt")!!.readText()
        val result = Day09.calculateSolutionOne(input)
        result shouldBe 6463499258318L
    }

    test("calculate solution two test") {
        val result = Day09.calculateSolutionTwo(sample)
        result shouldBe 2858L
    }

    test("calculate solution two") {
        val input = this.javaClass.getResource("/Day09.txt")!!.readText()
        val result = Day09.calculateSolutionTwo(input)
        result shouldBe 6493634986625L
    }
})