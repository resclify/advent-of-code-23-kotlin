private class Race(val totalTime: Long, val currentRecord: Long) {
    fun getRange() = LongRange(1, totalTime - 1)
}

private fun beatRecord(holdTime: Long, totalTime: Long, minDistance: Long): Boolean {
    return holdTime * (totalTime - holdTime) > minDistance
}

fun day6Part1(input: List<String>): Int {
    val totalTimes = input.first().substringAfter("Time:").split(" ").filterNot { it.isEmpty() }.map { it.toLong() }
    val records = input.last().substringAfter("Distance:").split(" ").filterNot { it.isEmpty() }.map { it.toLong() }

    val races = totalTimes.zip(records).map { Race(it.first, it.second) }
    val racesWon = races.map { race -> race.getRange().count { beatRecord(it, race.totalTime, race.currentRecord) } }
    return racesWon.fold(1) { acc, i -> acc * i }
}

fun binarySearchLowLimit(range: LongRange, totalTime: Long, record: Long): Long {
    var tLow = range.first
    var tHigh = range.last
    while (true) {
        val t = (tHigh - tLow) / 2 + tLow

        if (!beatRecord(t - 1, totalTime, record) && beatRecord(t, totalTime, record)) {
            return t
        }
        if (beatRecord(t, totalTime, record)) {
            tHigh = t
        } else {
            tLow = t
        }
    }
}

fun binarySearchHighLimit(range: LongRange, totalTime: Long, record: Long): Long {
    var tLow = range.first
    var tHigh = range.last
    while (true) {
        val t = (tHigh - tLow) / 2 + tLow

        if (beatRecord(t, totalTime, record) && !beatRecord(t + 1, totalTime, record)) {
            return t
        }
        if (beatRecord(t, totalTime, record)) {
            tLow = t
        } else {
            tHigh = t
        }
    }
}


fun day6Part2(input: List<String>): Long {
    val totalTime = input.first().replace(" ", "").substringAfter("Time:").toLong()
    val record = input.last().replace(" ", "").substringAfter("Distance:").toLong()

    // binary search
    val lowLimit = binarySearchLowLimit(LongRange(0, totalTime / 2), totalTime, record)
    val highLimit = binarySearchHighLimit(LongRange(totalTime / 2, totalTime), totalTime, record)
    return highLimit - lowLimit + 1
}