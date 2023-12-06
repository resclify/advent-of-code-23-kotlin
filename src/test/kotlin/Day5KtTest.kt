import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

class Day5KtTest {
    @Test
    fun descriptionInput() {
        val input =
            "seeds: 79 14 55 13\n" + "\n" + "seed-to-soil map:\n" + "50 98 2\n" + "52 50 48\n" + "\n" + "soil-to-fertilizer map:\n" + "0 15 37\n" + "37 52 2\n" + "39 0 15\n" + "\n" + "fertilizer-to-water map:\n" + "49 53 8\n" + "0 11 42\n" + "42 0 7\n" + "57 7 4\n" + "\n" + "water-to-light map:\n" + "88 18 7\n" + "18 25 70\n" + "\n" + "light-to-temperature map:\n" + "45 77 23\n" + "81 45 19\n" + "68 64 13\n" + "\n" + "temperature-to-humidity map:\n" + "0 69 1\n" + "1 0 69\n" + "\n" + "humidity-to-location map:\n" + "60 56 37\n" + "56 93 4"
        val inputList = input.split("\n")
        val output = day5Part1(inputList)
        assertEquals(output, 35)
    }

    @Test
    fun realInput() {
        val input = File("src/test/resources/input_day5.txt").readLines()
        val output = day5Part1(input)
        println(output)
    }

    @Test
    fun descriptionInput2() {
        val input =
            "seeds: 79 14 55 13\n" + "\n" + "seed-to-soil map:\n" + "50 98 2\n" + "52 50 48\n" + "\n" + "soil-to-fertilizer map:\n" + "0 15 37\n" + "37 52 2\n" + "39 0 15\n" + "\n" + "fertilizer-to-water map:\n" + "49 53 8\n" + "0 11 42\n" + "42 0 7\n" + "57 7 4\n" + "\n" + "water-to-light map:\n" + "88 18 7\n" + "18 25 70\n" + "\n" + "light-to-temperature map:\n" + "45 77 23\n" + "81 45 19\n" + "68 64 13\n" + "\n" + "temperature-to-humidity map:\n" + "0 69 1\n" + "1 0 69\n" + "\n" + "humidity-to-location map:\n" + "60 56 37\n" + "56 93 4"
        val inputList = input.split("\n")

        val output = day5Part2Reversed(inputList)
        assertEquals(output, 46)
    }

    @Test
    fun realInput2() {
        val input = File("src/test/resources/input_day5.txt").readLines()
        val output = day5Part2(input)
        println(output)
    }

    @Test
    fun realInput2Concurrent() {
        val input = File("src/test/resources/input_day5.txt").readLines()
        val time = measureTimeMillis {
            val output = day5Part2Concurrent(input)
            println(output)
        }
        println("Time taken: $time ms")
    }

    @Test
    fun realInput2Reversed() {
        val input = File("src/test/resources/input_day5.txt").readLines()
        val time = measureTimeMillis {
            val output = day5Part2Reversed(input)
            println(output)
        }
        println("Time taken: $time ms")
    }
}