import aoc24.Day08
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day08Test : FunSpec({

    val sample = """
        ............
        ........0...
        .....0......
        .......0....
        ....0.......
        ......A.....
        ............
        ............
        ........A...
        .........A..
        ............
        ............
   """.trimIndent()

    test("calculate solution one test") {
        val result = Day08.calculateSolutionOne(sample)
        result shouldBe 14
    }

    test("calculate solution one") {
        val input = this.javaClass.getResource("/Day08.txt")!!.readText()
        val result = Day08.calculateSolutionOne(input)
        result shouldBe 269
    }

    test("calculate solution two test") {
        val result = Day08.calculateSolutionTwo(sample)
        result shouldBe 34
    }

    test("calculate solution two") {
        val input = this.javaClass.getResource("/Day08.txt")!!.readText()
        val result = Day08.calculateSolutionTwo(input)
        result shouldBe 949
    }
})