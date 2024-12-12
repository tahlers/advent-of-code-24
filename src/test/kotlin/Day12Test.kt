import aoc24.Day12
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day12Test : FunSpec({

    val sample1 = """
        AAAA
        BBCD
        BBCC
        EEEC
   """.trimIndent()

    val sample2 = """
        OOOOO
        OXOXO
        OOOOO
        OXOXO
        OOOOO
   """.trimIndent()


    val sample3 = """
        RRRRIICCFF
        RRRRIICCCF
        VVRRRCCFFF
        VVRCCCJFFF
        VVVVCJJCFE
        VVIVCCJJEE
        VVIIICJJEE
        MIIIIIJJEE
        MIIISIJEEE
        MMMISSJEEE
   """.trimIndent()

    test("calculate solution one test") {
        val result1 = Day12.calculateSolutionOne(sample1)
        val result2 = Day12.calculateSolutionOne(sample2)
        val result3 = Day12.calculateSolutionOne(sample3)
        assertSoftly {
            result1 shouldBe 140
            result2 shouldBe 772
            result3 shouldBe 1930
        }
    }

    test("calculate solution one") {
        val input = this.javaClass.getResource("/Day12.txt")!!.readText()
        val result = Day12.calculateSolutionOne(input)
        result shouldBe 1446042
    }

    test("calculate solution two test") {
        val result1 = Day12.calculateSolutionTwo(sample1)
        val result2 = Day12.calculateSolutionTwo(sample2)
        val result3 = Day12.calculateSolutionTwo(sample3)
        assertSoftly {
            result1 shouldBe 80
            result2 shouldBe 436
            result3 shouldBe 1206
        }
    }

    test("calculate solution two") {
        val input = this.javaClass.getResource("/Day12.txt")!!.readText()
        val result = Day12.calculateSolutionTwo(input)
        result shouldBe 902742
    }
})