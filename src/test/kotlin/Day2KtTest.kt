import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day2KtTest {
    @Test
    fun descriptionInput() {
        val input =
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" + "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" + "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" + "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" + "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"

        val inputList = input.split("\n")
        val output = day2Part1(inputList)
        assertEquals(output, 8)
    }

    @Test
    fun realInput() {
        val input = File("src/test/resources/input_day2.txt").readLines()
        val output = day2Part1(input)
        println(output)
    }

    @Test
    fun descriptionInput2() {
        val input =
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
                    "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
                    "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
                    "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
                    "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        val inputList = input.split("\n")

        val output = day2Part2(inputList)
        assertEquals(output, 2286)
    }

    @Test
    fun realInput2() {
        val input = File("src/test/resources/input_day2.txt").readLines()
        val output = day2Part2(input)
        println(output)
    }

}