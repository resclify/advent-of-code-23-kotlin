import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day11KtTest {
    @Test
    fun descriptionInput() {
        val input = "...#......\n" +
                ".......#..\n" +
                "#.........\n" +
                "..........\n" +
                "......#...\n" +
                ".#........\n" +
                ".........#\n" +
                "..........\n" +
                ".......#..\n" +
                "#...#....."
        val inputList = input.split("\n")
        val output = day11Part1(inputList)
        assertEquals(output, 374)
    }

    @Test
    fun realInput() {
        val input = File("src/test/resources/input_day11.txt").readLines()
        val output = day11Part1(input)
        println(output)
        assertEquals(9445168, output)
    }

    @Test
    fun descriptionInput2() {
        val input = "...#......\n" +
                ".......#..\n" +
                "#.........\n" +
                "..........\n" +
                "......#...\n" +
                ".#........\n" +
                ".........#\n" +
                "..........\n" +
                ".......#..\n" +
                "#...#....."
        val inputList = input.split("\n")

        val output = day11Part2(inputList, 10)
        assertEquals(output, 1030)
    }

    @Test
    fun descriptionInput3() {
        val input = "...#......\n" +
                ".......#..\n" +
                "#.........\n" +
                "..........\n" +
                "......#...\n" +
                ".#........\n" +
                ".........#\n" +
                "..........\n" +
                ".......#..\n" +
                "#...#....."
        val inputList = input.split("\n")

        val output = day11Part2(inputList, 100)
        assertEquals(output, 8410)
    }

    @Test
    fun realInput2() {
        val input = File("src/test/resources/input_day11.txt").readLines()
        val output = day11Part2(input, 1_000_000)
        println(output)
        assertEquals(742305960572, output)
    }
}