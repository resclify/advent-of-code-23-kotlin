private fun calcDiffs(seq: List<Int>): List<List<Int>> {
    val diffs = mutableListOf(seq)
    do {
        val diff = diffs.last().windowed(2).map { it[1] - it[0] }
        diffs.add(diff)
    } while (!diff.all { it == 0 })
    return diffs
}

private fun calcPastExtrapolation(diffs: List<List<Int>>): Int {
    var extrapolated = 0
    for (i in diffs.size - 2 downTo 0) {
        extrapolated += diffs[i].last()
    }
    return extrapolated
}

private fun calcPreExtrapolation(diffs: List<List<Int>>): Int {
    var extrapolated = 0
    for (i in diffs.size - 2 downTo 0) {
        extrapolated = diffs[i].first() - extrapolated
    }
    return extrapolated
}

fun day9Part1(input: List<String>): Int {
    val numberSequences = input.map { s -> s.split(" ").map { it.toInt() } }

    return numberSequences.map { calcDiffs(it) }.map { calcPastExtrapolation(it) }.sumOf { it }
}

fun day9Part2(input: List<String>): Int {
    val numberSequences = input.map { s -> s.split(" ").map { it.toInt() } }

    return numberSequences.map { calcDiffs(it) }.map { calcPreExtrapolation(it) }.sumOf { it }
}
