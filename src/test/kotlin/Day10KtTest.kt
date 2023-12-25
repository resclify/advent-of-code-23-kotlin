import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class Day10KtTest {
    @Test
    fun descriptionInput() {
        val input = "-L|F7\n" +
                "7S-7|\n" +
                "L|7||\n" +
                "-L-J|\n" +
                "L|-JF"
        val inputList = input.split("\n")
        val output = day10Part1(inputList)
        assertEquals(output, 4)
    }

    @Test
    fun descriptionInput1() {
        val input = "7-F7-\n" +
                ".FJ|7\n" +
                "SJLL7\n" +
                "|F--J\n" +
                "LJ.LJ\n"
        val inputList = input.split("\n")
        val output = day10Part1(inputList)
        assertEquals(output, 8)
    }

    @Test
    fun realInput() {
        val input = File("src/test/resources/input_day10.txt").readLines()
        val output = day10Part1(input)
        println(output)
        assertEquals(output, 6947)
    }

    @Test
    fun descriptionInput2() {
        val input = "...........\n" +
                ".S-------7.\n" +
                ".|F-----7|.\n" +
                ".||.....||.\n" +
                ".||.....||.\n" +
                ".|L-7.F-J|.\n" +
                ".|..|.|..|.\n" +
                ".L--J.L--J.\n" +
                "..........."
        val inputList = input.split("\n")

        val output = day10Part2(inputList)
        assertEquals(output, 4)
    }

    @Test
    fun descriptionInput3() {
        val input = "..........\n" +
                ".S------7.\n" +
                ".|F----7|.\n" +
                ".||....||.\n" +
                ".||....||.\n" +
                ".|L-7F-J|.\n" +
                ".|..||..|.\n" +
                ".L--JL--J.\n" +
                ".........."
        val inputList = input.split("\n")

        val output = day10Part2(inputList)
        assertEquals(output, 4)
    }

    @Test
    fun descriptionInput4() {
        val input = ".F----7F7F7F7F-7....\n" +
                ".|F--7||||||||FJ....\n" +
                ".||.FJ||||||||L7....\n" +
                "FJL7L7LJLJ||LJ.L-7..\n" +
                "L--J.L7...LJS7F-7L7.\n" +
                "....F-J..F7FJ|L7L7L7\n" +
                "....L7.F7||L7|.L7L7|\n" +
                ".....|FJLJ|FJ|F7|.LJ\n" +
                "....FJL-7.||.||||...\n" +
                "....L---J.LJ.LJLJ..."
        val inputList = input.split("\n")

        val output = day10Part2(inputList)
        assertEquals(output, 8)
    }

    @Test
    fun descriptionInput5() {
        val input = "FF7FSF7F7F7F7F7F---7\n" +
                "L|LJ||||||||||||F--J\n" +
                "FL-7LJLJ||||||LJL-77\n" +
                "F--JF--7||LJLJ7F7FJ-\n" +
                "L---JF-JLJ.||-FJLJJ7\n" +
                "|F|F-JF---7F7-L7L|7|\n" +
                "|FFJF7L7F-JF7|JL---7\n" +
                "7-L-JL7||F7|L7F-7F7|\n" +
                "L.L7LFJ|||||FJL7||LJ\n" +
                "L7JLJL-JLJLJL--JLJ.L"
        val inputList = input.split("\n")

        val output = day10Part2(inputList)
        assertEquals(output, 10)
    }

    @Test
    fun realInput2() {
        val input = File("src/test/resources/input_day10.txt").readLines()
        val output = day10Part2(input)
        println(output)
        assertEquals(output, 273)
    }
}