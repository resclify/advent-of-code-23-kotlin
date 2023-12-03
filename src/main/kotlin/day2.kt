private class Game(val id: Int, val sets: List<GameSet>) {
    fun isPossible(maxRed: Int, maxGreen: Int, maxBlue: Int): Boolean {
        return sets.all { it.redCount <= maxRed && it.greenCount <= maxGreen && it.blueCount <= maxBlue }
    }

    fun getSetPower(): Int {
        return sets.maxOf { it.redCount } * sets.maxOf { it.greenCount } * sets.maxOf { it.blueCount }
    }
}

private class GameSet(val blueCount: Int, val redCount: Int, val greenCount: Int)

private fun parseGames(input: List<String>): List<Game> {
    return input.map {

        val gameId = it.removePrefix("Game ").substringBefore(":").toInt()
        val sets = parseSet(it.substringAfter(":").split(";"))

        Game(gameId, sets)
    }
}

private fun parseSet(split: List<String>): List<GameSet> {
    return split.map {
        var blue = 0
        var red = 0
        var green = 0

        it.split(",").forEach { colorDescription ->
            val colorName = colorDescription.trim().split(" ").last()
            val colorValue = colorDescription.trim().split(" ").first().toInt()

            when (colorName) {
                "blue" -> blue = colorValue
                "red" -> red = colorValue
                "green" -> green = colorValue
            }
        }

        GameSet(blue, red, green)
    }
}


fun day2Part1(input: List<String>): Int {
    val redMax = 12
    val greenMax = 13
    val blueMax = 14

    val games = parseGames(input)
    return games.filter { it.isPossible(redMax, greenMax, blueMax) }.sumOf { it.id }
}

fun day2Part2(input: List<String>): Int {
    val games = parseGames(input)
    return games.sumOf { it.getSetPower() }
}