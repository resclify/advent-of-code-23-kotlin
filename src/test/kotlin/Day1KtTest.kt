import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day1KtTest {
    @Test
    fun descriptionInput() {
        val input = "1abc2\npqr3stu8vwx\na1b2c3d4e5f\ntreb7uchet".split("\n")

        val output = day1Part1(input)
        assertEquals(output, 142)
    }

    @Test
    fun realInput() {
        val input = File("src/test/resources/input_day1.txt").readText().split("\n")
        val output = day1Part1(input)
        println(output)
    }

    @Test
    fun descriptionInput2() {
        val input =
            "two1nine\neightwothree\nabcone2threexyz\nxtwone3four\n4nineeightseven2\nzoneight234\n7pqrstsixteen".split("\n")

        val output = day1Part2(input)
        assertEquals(output, 281)
    }

    @Test
    fun realInput2() {
        val input = File("src/test/resources/input_day1.txt").readText().split("\n")
        val output = day1Part2(input)
        println(output)
    }

}