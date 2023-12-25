private fun parseGrid(input: List<String>): Grid {
    val tiles = input.mapIndexed { y, s ->
        s.mapIndexed { x, c -> Tile(TileType.fromChar(c), Coordinate(x, y)) }.toTypedArray()
    }.toTypedArray()

    val startX = tiles.map { it.indexOfFirst { t -> t.type == TileType.Start } }.first { it != -1 }
    val startY = tiles.indexOfFirst { it.any { t -> t.type == TileType.Start } }

    val grid = Grid(tiles, Coordinate(startX, startY))
    return grid
}

private enum class TileType(val char: Char) {
    Vertical('|'), Horizontal('-'), BendNE('L'), BendNW('J'), BendSW('7'), BendSE('F'), Ground('.'), Start('S');

    companion object {
        fun fromChar(c: Char): TileType {
            return TileType.values().find { it.char == c } ?: Ground
        }
    }
}

private data class Coordinate(val x: Int, val y: Int) {
    fun nextUp() = Coordinate(x, y - 1)
    fun nextDown() = Coordinate(x, y + 1)
    fun nextLeft() = Coordinate(x - 1, y)

    fun nextRight() = Coordinate(x + 1, y)
    fun getAllCoordinatesAround(): List<Coordinate> {
        return listOf(
            Coordinate(x + 1, y),
            Coordinate(x + 1, y + 1),
            Coordinate(x, y + 1),
            Coordinate(x - 1, y + 1),
            Coordinate(x - 1, y),
            Coordinate(x - 1, y - 1),
            Coordinate(x, y - 1),
            Coordinate(x + 1, y - 1)
        )
    }
}

private data class Tile(
    var type: TileType,
    val coordinate: Coordinate,
    var value: Int = Int.MAX_VALUE,
    var partOfLoop: Boolean = false,
    var horizontalEnclosed: Boolean = false,
) {

    fun getNeighbors(grid: Grid): List<Tile> {
        return when (type) {
            TileType.Vertical -> listOf(grid.getTile(coordinate.nextUp()), grid.getTile(coordinate.nextDown()))
            TileType.Horizontal -> listOf(grid.getTile(coordinate.nextLeft()), grid.getTile(coordinate.nextRight()))
            TileType.BendNE -> listOf(grid.getTile(coordinate.nextUp()), grid.getTile(coordinate.nextRight()))
            TileType.BendNW -> listOf(grid.getTile(coordinate.nextUp()), grid.getTile(coordinate.nextLeft()))
            TileType.BendSE -> listOf(grid.getTile(coordinate.nextDown()), grid.getTile(coordinate.nextRight()))
            TileType.BendSW -> listOf(grid.getTile(coordinate.nextDown()), grid.getTile(coordinate.nextLeft()))
            TileType.Ground -> listOf()
            TileType.Start -> listOf()
        }
    }

    fun getNextTile(grid: Grid, prevTile: Tile): Tile {
        return this.getNeighbors(grid).filterNot { it == prevTile }.first()
    }
}

private class Grid(private val tiles: Array<Array<Tile>>, private val start: Coordinate) {

    private fun calculateGridValues() {
        val startTile = getTile(start)
        startTile.value = 0

        val neighbors = start.getAllCoordinatesAround().map { getTile(it) }
        val startDirections = neighbors.filter { it.getNeighbors(this).contains(startTile) }
        startDirections.forEach() {
            var prevTile = startTile
            var currentTile = it
            var pipes = 1
            currentTile.partOfLoop = true
            while (currentTile.type != TileType.Start) {
                if (currentTile.value > pipes) {
                    currentTile.value = pipes
                }
                pipes++
                val nextTile = currentTile.getNextTile(this, prevTile)
                prevTile = currentTile
                currentTile = nextTile
                currentTile.partOfLoop = true
            }
        }
    }

    private fun replaceStartWithLoopTile() {
        val startTile = getTile(start)

        val neighbors = start.getAllCoordinatesAround().map { getTile(it) }
        val startDirections = neighbors.filter { it.getNeighbors(this).contains(startTile) }
        val s1 = startDirections.first().coordinate
        val s2 = startDirections.last().coordinate

        startTile.type = when {
            start.nextRight() == s1 && start.nextLeft() == s2 -> TileType.Vertical
            start.nextRight() == s2 && start.nextLeft() == s1 -> TileType.Vertical

            start.nextUp() == s1 && start.nextDown() == s2 -> TileType.Vertical
            start.nextUp() == s2 && start.nextDown() == s1 -> TileType.Vertical

            start.nextUp() == s1 && start.nextRight() == s2 -> TileType.BendNE
            start.nextUp() == s2 && start.nextRight() == s1 -> TileType.BendNE

            start.nextUp() == s1 && start.nextLeft() == s2 -> TileType.BendNW
            start.nextUp() == s2 && start.nextLeft() == s1 -> TileType.BendNW

            start.nextDown() == s1 && start.nextLeft() == s2 -> TileType.BendSW
            start.nextDown() == s2 && start.nextLeft() == s1 -> TileType.BendSW

            start.nextDown() == s1 && start.nextRight() == s2 -> TileType.BendSE
            start.nextDown() == s2 && start.nextRight() == s1 -> TileType.BendSE

            else -> {
                TileType.Start
            }
        }
    }

    fun getTile(coordinate: Coordinate): Tile {
        return if (coordinate.x < 0 || coordinate.y < 0 || coordinate.y >= tiles.size || coordinate.x >= tiles.first().size) {
            tiles[0][0]
        } else {
            tiles[coordinate.y][coordinate.x]
        }
    }

    fun getFarthestPoint(): Int {
        calculateGridValues()
        return getMaxTileValue()
    }

    private fun getMaxTileValue() =
        tiles.mapNotNull { it.filter { t -> t.value != Int.MAX_VALUE }.maxOfOrNull { t1 -> t1.value } }.max()

    fun getInlinerTiles(): Int {
        calculateGridValues()
        replaceStartWithLoopTile()
        findHorizontalEnclosed()
        return tiles.sumOf { it.count { t -> t.horizontalEnclosed } }
    }


    private fun findHorizontalEnclosed() {
        for (y in tiles.indices) {
            var inside = false
            var lastCorner = TileType.Ground
            for (x in tiles.first().indices) {
                val t = tiles[y][x]
                if (t.partOfLoop && (t.type == TileType.Vertical)) {
                    inside = !inside
                }
                if (t.partOfLoop && (t.type == TileType.BendNE)) {
                    lastCorner = TileType.BendNE
                }
                if (t.partOfLoop && (t.type == TileType.BendSE)) {
                    lastCorner = TileType.BendSE

                }
                if (t.partOfLoop && (t.type == TileType.BendNW)) {
                    if (lastCorner == TileType.BendSE) {
                        inside = !inside
                    }
                }
                if (t.partOfLoop && (t.type == TileType.BendSW)) {
                    if (lastCorner == TileType.BendNE) {
                        inside = !inside
                    }
                }
                if (!t.partOfLoop && inside) {
                    t.horizontalEnclosed = true
                }
            }
        }
    }
}

fun day10Part1(input: List<String>): Int {
    val grid = parseGrid(input)
    return grid.getFarthestPoint()
}

fun day10Part2(input: List<String>): Int {
    val grid = parseGrid(input)
    return grid.getInlinerTiles()
}