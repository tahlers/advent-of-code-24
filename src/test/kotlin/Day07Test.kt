import aoc24.Day07
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day07Test : FunSpec({

    val sample = """
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
   """.trimIndent()


    test("calculate solution one test") {
        val result = Day07.calculateSolutionOne(sample)
        result shouldBe 3749L
    }

    test("calculate solution one") {
        val input = this.javaClass.getResource("/Day07.txt")!!.readText()
        val result = Day07.calculateSolutionOne(input)
        result shouldBe 2654749936343L
    }

    test("calculate solution two test") {
        val result = Day07.calculateSolutionTwo(sample)
        result shouldBe 11387L
    }

    test("calculate solution two") {
        val input = this.javaClass.getResource("/Day07.txt")!!.readText()
        val result = Day07.calculateSolutionTwo(input)
        result shouldBe 124060392153684L
    }
})