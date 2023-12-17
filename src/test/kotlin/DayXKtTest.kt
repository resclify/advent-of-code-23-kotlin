import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class dayXKtTest {
    @Test
    fun descriptionInput() {
        val input = ""
        val inputList = input.split("\n")
        val output = dayXPart1(inputList)
        assertEquals(output, 4361)
    }

    @Test
    fun realInput() {
        val input = File("src/test/resources/input_dayX.txt").readLines()
        val output = dayXPart1(input)
        println(output)
    }

    @Test
    fun descriptionInput2() {
        val input = ""
        val inputList = input.split("\n")

        val output = dayXPart2(inputList)
        assertEquals(output, 467835)
    }

    @Test
    fun realInput2() {
        val input = File("src/test/resources/input_dayX.txt").readLines()
        val output = dayXPart2(input)
        println(output)
    }
}