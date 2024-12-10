import aoc24.Day10
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day10Test : FunSpec({

    val sample = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
   """.trimIndent()

    test("calculate solution one test") {
        val result = Day10.calculateSolutionOne(sample)
        result shouldBe 36
    }

    test("calculate solution one") {
        val input = this.javaClass.getResource("/Day10.txt")!!.readText()
        val result = Day10.calculateSolutionOne(input)
        result shouldBe 510
    }

    test("calculate solution two test") {
        val result = Day10.calculateSolutionTwo(sample)
        result shouldBe 81
    }

    test("calculate solution two") {
        val input = this.javaClass.getResource("/Day10.txt")!!.readText()
        val result = Day10.calculateSolutionTwo(input)
        result shouldBe 1058
    }
})