fun day1Part1(input: List<String>): Int {
    return input.sumOf {
        it.first { c -> c.isDigit() }.digitToInt() * 10 + it.last { c -> c.isDigit() }.digitToInt()
    }
}

fun String.findFirstDigit(stringToDigit: Map<String, Int>): Int {
    val searchResult = this.findAnyOf(stringToDigit.keys)
    return stringToDigit[searchResult?.second] ?: error("")
}

fun String.findLastDigit(stringToDigit: Map<String, Int>): Int {
    val searchResult = this.findLastAnyOf(stringToDigit.keys)
    return stringToDigit[searchResult?.second] ?: error("")
}

fun day1Part2(input: List<String>): Int {
    val stringToDigit = mapOf(
        "one" to 1,
        "1" to 1,
        "two" to 2,
        "2" to 2,
        "three" to 3,
        "3" to 3,
        "four" to 4,
        "4" to 4,
        "five" to 5,
        "5" to 5,
        "six" to 6,
        "6" to 6,
        "seven" to 7,
        "7" to 7,
        "eight" to 8,
        "8" to 8,
        "nine" to 9,
        "9" to 9,
    )
    return input.sumOf { it.findFirstDigit(stringToDigit) * 10 + it.findLastDigit(stringToDigit) }
}