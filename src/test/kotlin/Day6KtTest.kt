import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day6KtTest {
    @Test
    fun descriptionInput() {
        val input = "Time:      7  15   30\n" + "Distance:  9  40  200"
        val inputList = input.split("\n")
        val output = day6Part1(inputList)
        assertEquals(output, 288)
    }

    @Test
    fun realInput() {
        val input = File("src/test/resources/input_day6.txt").readLines()
        val output = day6Part1(input)
        println(output)
    }

    @Test
    fun descriptionInput2() {
        val input = "Time:      7  15   30\n" +
                "Distance:  9  40  200"
        val inputList = input.split("\n")

        val output = day6Part2(inputList)
        assertEquals(output, 71503)
    }

    @Test
    fun realInput2() {
        val input = File("src/test/resources/input_day6.txt").readLines()
        val output = day6Part2(input)
        println(output)
    }
}