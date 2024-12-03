import aoc24.Day03
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day03Test : FunSpec({

    val sample = """
        xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
   """.trimIndent()

    val sampleTwo = """
        xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
    """.trimIndent()


    test("calculate solution one test") {
        val result = Day03.calculateSolutionOne(sample)
        result shouldBe 161
    }

    test("calculate solution one") {
        val input = this.javaClass.getResource("/day03.txt")!!.readText()
        val result =  Day03.calculateSolutionOne(input)
        result shouldBe 174960292
    }

    test("calculate solution two test") {
        val result = Day03.calculateSolutionTwo(sampleTwo)
        result shouldBe 48
    }

    test("calculate solution two") {
        val input = this.javaClass.getResource("/day03.txt")!!.readText()
        val result =  Day03.calculateSolutionTwo(input)
        result shouldBe 56275602
    }
})