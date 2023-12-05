import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private class Mapping(val sourceRange: LongRange, val offset: Long)

private fun Long.mapTo(mapping: List<Mapping>): Long {
    return this + (mapping.firstOrNull { this in it.sourceRange }?.offset ?: 0)
}

private fun Long.mapToAll(mappings: List<List<Mapping>>): Long {
    return mappings.fold(this) { acc, e -> acc.mapTo(e) }
}

private fun getElementMapping(elementString: String): List<Mapping> {
    return elementString.lines().drop(1).map {
        val rawValues = it.split(" ").map { e -> e.toLong() }
        Mapping(
            sourceRange = LongRange(rawValues[1], rawValues[1] + rawValues[2]), offset = rawValues[0] - rawValues[1]
        )
    }
}

fun day5Part1(input: List<String>): Long {
    val seeds = input[0].substringAfter("seeds: ").split(" ").map { it.toLong() }

    val inputElements = input.joinToString(separator = "\n").split("\n\n")
    val allMappings = inputElements.drop(1).map { getElementMapping(it) }

    return seeds.minOf { it.mapToAll(allMappings) }
}


fun day5Part2(input: List<String>): Long {
    val seeds = input[0].substringAfter("seeds: ").split(" ").map { it.toLong() }.chunked(2)
        .map { LongRange(it.first(), it.first() + it.last()) }

    val inputElements = input.joinToString(separator = "\n").split("\n\n")
    val allMappings = inputElements.drop(1).map { getElementMapping(it) }

    return seeds.minOf { seedRange ->
        println(seedRange)
        seedRange.minOf { seed -> seed.mapToAll(allMappings) }
    }
}

fun day5Part2Concurrent(input: List<String>): Long {
    val seeds = input[0].substringAfter("seeds: ").split(" ").map { it.toLong() }.chunked(2)
        .map { LongRange(it.first(), it.first() + it.last()) }

    val inputElements = input.joinToString(separator = "\n").split("\n\n")
    val allMappings = inputElements.drop(1).map { getElementMapping(it) }

    return runBlocking {
        withContext(Dispatchers.Default) {
            seeds.map { seedRange ->
                async {
                    println("$seedRange started")
                    seedRange.minOf { it.mapToAll(allMappings) }
                }
            }.minBy { it.await() }.await()
        }
    }
}

