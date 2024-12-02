import aoc24.Day02
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day02Test : FunSpec({

    val sample = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
   """.trimIndent()


    test("calculate safe report count test") {
        val result = Day02.calculateSafeReportCount(sample)
        result shouldBe 2
    }

    test("calculate safe report count solution one") {
        val input = this.javaClass.getResource("/day02.txt")!!.readText()
        val result =  Day02.calculateSafeReportCount(input)
        result shouldBe 220
    }

    test("calculate safe damper report count test") {
        val result = Day02.calculateSafeDamperReportCount(sample)
        result shouldBe 4
    }

    test("calculate safe damper report count solution one") {
        val input = this.javaClass.getResource("/day02.txt")!!.readText()
        val result =  Day02.calculateSafeDamperReportCount(input)
        result shouldBe 296
    }
})