import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class day9KtTest {
    @Test
    fun descriptionInput() {
        val input = "0 3 6 9 12 15\n" +
                "1 3 6 10 15 21\n" +
                "10 13 16 21 30 45"
        val inputList = input.split("\n")
        val output = day9Part1(inputList)
        assertEquals(output, 114)
    }

    @Test
    fun realInput() {
        val input = File("src/test/resources/input_day9.txt").readLines()
        val output = day9Part1(input)
        println(output)
    }

    @Test
    fun descriptionInput2() {
        val input = "0 3 6 9 12 15\n" +
                "1 3 6 10 15 21\n" +
                "10 13 16 21 30 45"
        val inputList = input.split("\n")

        val output = day9Part2(inputList)
        assertEquals(output, 2)
    }

    @Test
    fun realInput2() {
        val input = File("src/test/resources/input_day9.txt").readLines()
        val output = day9Part2(input)
        println(output)
    }
}