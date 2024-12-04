import aoc24.Day04
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day04Test : FunSpec({

    val sample = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
   """.trimIndent()


    test("calculate solution one test") {
        val result = Day04.calculateSolutionOne(sample)
        result shouldBe 18
    }

    test("calculate solution one") {
        val input = this.javaClass.getResource("/day04.txt")!!.readText()
        val result =  Day04.calculateSolutionOne(input)
        result shouldBe 2532
    }

    test("calculate solution two test") {
        val result = Day04.calculateSolutionTwo(sample)
        result shouldBe 9
    }

    test("calculate solution two") {
        val input = this.javaClass.getResource("/day04.txt")!!.readText()
        val result =  Day04.calculateSolutionTwo(input)
        result shouldBe 1941
    }
})