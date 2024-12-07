import aoc24.Day05
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day05Test : FunSpec({

    val sample = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13
        
        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
   """.trimIndent()


    test("calculate solution one test") {
        val result = Day05.calculateSolutionOne(sample)
        result shouldBe 143
    }

    test("calculate solution one") {
        val input = this.javaClass.getResource("/day05.txt")!!.readText()
        val result =  Day05.calculateSolutionOne(input)
        result shouldBe 6260
    }

    test("calculate solution two test") {
        val result = Day05.calculateSolutionTwo(sample)
        result shouldBe 123
    }

    test("calculate solution two") {
        val input = this.javaClass.getResource("/day05.txt")!!.readText()
        val result =  Day05.calculateSolutionTwo(input)
        result shouldBe 5346
    }
})