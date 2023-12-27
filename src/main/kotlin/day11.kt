import kotlin.math.abs

class Galaxy(val posX: Int, val posY: Int, val offsetFactorX: Int = 0, val offsetFactorY: Int = 0)

private fun parseGalaxies(input: List<String>) = input.flatMapIndexed { y: Int, s: String ->
    s.mapIndexed { x: Int, c: Char -> if (c == '#') Galaxy(x, y) else null }.filterNotNull()
}

fun calculateDistance(g1: Galaxy, g2: Galaxy, factor: Long = 1): Long {
    val diffX = abs(g1.posX - g2.posX)
    val diffY = abs(g1.posY - g2.posY)

    val diffOffsetX = abs(g1.offsetFactorX - g2.offsetFactorX)
    val diffOffsetY = abs(g1.offsetFactorY - g2.offsetFactorY)

    return (diffX + diffY).toLong() + (factor - 1) * (diffOffsetX + diffOffsetY).toLong()
}

fun day11Part1(input: List<String>): Long {
    val spaceSizeX = input.size
    val spaceSizeY = input[0].length

    val galaxies = parseGalaxies(input)
    val expanded = expandGalaxies(galaxies, spaceSizeX, spaceSizeY)
    return expanded.flatMap { g1 -> expanded.map { g2 -> calculateDistance(g1, g2) } }.sum() / 2
}

private fun expandGalaxies(
    galaxies: List<Galaxy>,
    spaceSizeX: Int,
    spaceSizeY: Int,
): List<Galaxy> {
    val emptyColumns = (0 until spaceSizeX).filterNot { it in galaxies.map { g -> g.posX } }
    val emptyRows = (0 until spaceSizeY).filterNot { it in galaxies.map { g -> g.posY } }

    val expanded = galaxies.map { g ->
        val newX = g.posX + emptyColumns.count { g.posX > it }
        val newY = g.posY + emptyRows.count { g.posY > it }

        Galaxy(newX, newY)
    }
    return expanded
}

private fun expandGalaxiesWithFactor(
    galaxies: List<Galaxy>,
    spaceSizeX: Int,
    spaceSizeY: Int,
): List<Galaxy> {
    val emptyColumns = (0 until spaceSizeX).filterNot { it in galaxies.map { g -> g.posX } }
    val emptyRows = (0 until spaceSizeY).filterNot { it in galaxies.map { g -> g.posY } }

    val expanded = galaxies.map { g ->
        val offsetFactorX = emptyColumns.count { g.posX > it }
        val offsetFactorY = emptyRows.count { g.posY > it }
        Galaxy(g.posX, g.posY, offsetFactorX, offsetFactorY)
    }
    return expanded
}


fun day11Part2(input: List<String>, factor: Long): Long {
    val spaceSizeX = input.size
    val spaceSizeY = input[0].length

    val galaxies = parseGalaxies(input)
    val expanded = expandGalaxiesWithFactor(galaxies, spaceSizeX, spaceSizeY)

    return expanded.flatMap { g1 -> expanded.map { g2 -> calculateDistance(g1, g2, factor) } }.sum() / 2
}