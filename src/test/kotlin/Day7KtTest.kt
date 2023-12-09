import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day7KtTest {
    @Test
    fun descriptionInput() {
        val input = "32T3K 765\n" +
                "T55J5 684\n" +
                "KK677 28\n" +
                "KTJJT 220\n" +
                "QQQJA 483"
        val inputList = input.split("\n")
        val output = day7Part1(inputList)
        assertEquals(output, 6440)
    }

    @Test
    fun realInput() {
        val input = File("src/test/resources/input_day7.txt").readLines()
        val output = day7Part1(input)
        println(output)
    }

    @Test
    fun descriptionInput2() {
        val input = "32T3K 765\n" +
                "T55J5 684\n" +
                "KK677 28\n" +
                "KTJJT 220\n" +
                "QQQJA 483"
        val inputList = input.split("\n")

        val output = day7Part2(inputList)
        assertEquals(output, 5905)
    }

    @Test
    fun realInput2() {
        val input = File("src/test/resources/input_day7.txt").readLines()
        val output = day7Part2(input)
        println(output)
    }
}