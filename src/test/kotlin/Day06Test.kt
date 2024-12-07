import aoc24.Day06
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day06Test : FunSpec({

    val sample = """
         ....#.....
         .........#
         ..........
         ..#.......
         .......#..
         ..........
         .#..^.....
         ........#.
         #.........
         ......#...
   """.trimIndent()


    test("calculate solution one test") {
        val result = Day06.calculateSolutionOne(sample)
        result shouldBe 41
    }

    test("calculate solution one") {
        val input = this.javaClass.getResource("/Day06.txt")!!.readText()
        val result = Day06.calculateSolutionOne(input)
        result shouldBe 4752
    }

    test("calculate solution two test") {
        val result = Day06.calculateSolutionTwo(sample)
        result shouldBe 6
    }

    test("calculate solution two") {
        val input = this.javaClass.getResource("/Day06.txt")!!.readText()
        val result = Day06.calculateSolutionTwo(input)
        result shouldBe 1719
    }
})