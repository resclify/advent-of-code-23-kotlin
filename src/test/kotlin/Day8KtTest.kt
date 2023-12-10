import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class day8KtTest {
    @Test
    fun descriptionInput() {
        val input = "RL\n" +
                "\n" +
                "AAA = (BBB, CCC)\n" +
                "BBB = (DDD, EEE)\n" +
                "CCC = (ZZZ, GGG)\n" +
                "DDD = (DDD, DDD)\n" +
                "EEE = (EEE, EEE)\n" +
                "GGG = (GGG, GGG)\n" +
                "ZZZ = (ZZZ, ZZZ)"
        val inputList = input.split("\n")
        val output = day8Part1(inputList)
        assertEquals(output, 2)
    }
    @Test
    fun descriptionInput3() {
        val input = "LLR\n" +
                "\n" +
                "AAA = (BBB, BBB)\n" +
                "BBB = (AAA, ZZZ)\n" +
                "ZZZ = (ZZZ, ZZZ)"
        val inputList = input.split("\n")
        val output = day8Part1(inputList)
        assertEquals(output, 6)
    }

    @Test
    fun realInput() {
        val input = File("src/test/resources/input_day8.txt").readLines()
        val output = day8Part1(input)
        println(output)
    }

    @Test
    fun descriptionInput2() {
        val input = "LR\n" +
                "\n" +
                "11A = (11B, XXX)\n" +
                "11B = (XXX, 11Z)\n" +
                "11Z = (11B, XXX)\n" +
                "22A = (22B, XXX)\n" +
                "22B = (22C, 22C)\n" +
                "22C = (22Z, 22Z)\n" +
                "22Z = (22B, 22B)\n" +
                "XXX = (XXX, XXX)"
        val inputList = input.split("\n")

        val output = day8Part2(inputList)
        assertEquals(output, 6)
    }

    @Test
    fun realInput2() {
        val input = File("src/test/resources/input_day8.txt").readLines()
        val output = day8Part2(input)
        println(output)
    }
}