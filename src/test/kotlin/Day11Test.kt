import aoc24.Day11
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day11Test : FunSpec({

    val sample = """
        125 17
   """.trimIndent()

    test("calculate solution one test") {
        val result = Day11.calculateSolutionOne(sample)
        result shouldBe 55312L
    }

    test("calculate solution one") {
        val input = this.javaClass.getResource("/Day11.txt")!!.readText()
        val result = Day11.calculateSolutionOne(input)
        result shouldBe 199982L
    }

    test("calculate solution two test") {
        val result = Day11.calculateSolutionTwo(sample)
        result shouldBe 65601038650482L
    }

    test("calculate solution two") {
        val input = this.javaClass.getResource("/Day11.txt")!!.readText()
        val result = Day11.calculateSolutionTwo(input)
        result shouldBe 237149922829154L
    }
})