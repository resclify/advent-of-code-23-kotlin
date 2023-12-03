import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day3KtTest {
    @Test
    fun descriptionInput() {
        val input =
            "467..114..\n" + "...*......\n" + "..35..633.\n" + "......#...\n" + "617*......\n" + ".....+.58.\n" + "..592.....\n" + "......755.\n" + "...\$.*....\n" + ".664.598.."
        val inputList = input.split("\n")
        val output = day3Part1(inputList)
        assertEquals(output, 4361)
    }

    @Test
    fun realInput() {
        val input = File("src/test/resources/input_day3.txt").readLines()
        val output = day3Part1(input)
        println(output)
    }

    @Test
    fun descriptionInput2() {
        val input =
            "467..114..\n" +
                    "...*......\n" +
                    "..35..633.\n" +
                    "......#...\n" +
                    "617*......\n" +
                    ".....+.58.\n" +
                    "..592.....\n" +
                    "......755.\n" +
                    "...\$.*....\n" +
                    ".664.598.."
        val inputList = input.split("\n")

        val output = day3Part2(inputList)
        assertEquals(output, 467835)
    }

    @Test
    fun realInput2() {
        val input = File("src/test/resources/input_day3.txt").readLines()
        val output = day3Part2(input)
        println(output)
    }
}