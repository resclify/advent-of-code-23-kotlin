fun day3Part1(input: List<String>): Int {
    val augmentedInput = input.addBorder()

    val numbers = parseNumbers(augmentedInput)
    return numbers.filter { it.isPartNumber(augmentedInput) }.sumOf { it.value }
}

private data class Position(val x: Int, val y: Int)

private data class GearSymbol(val position: Position, val number: Number)

private class Number(val value: Int, val digits: Int, val position: Position) {
    fun isPartNumber(input: List<String>): Boolean {
        val upperBorder = input[position.y - 1].substring(position.x - 1, position.x + digits + 1)
        val leftBorder = input[position.y][position.x - 1]
        val rightBorder = input[position.y][position.x + digits]
        val lowerBorder = input[position.y + 1].substring(position.x - 1, position.x + digits + 1)

        val notNumberOrDot = Regex("[^.0-9]")
        return (upperBorder + leftBorder + rightBorder + lowerBorder).contains(notNumberOrDot)
    }

    fun getGearSymbols(input: List<String>): List<GearSymbol> {
        val upperBorder = input[position.y - 1].substring(position.x - 1, position.x + digits + 1)
        val leftBorder = input[position.y][position.x - 1]
        val rightBorder = input[position.y][position.x + digits]
        val lowerBorder = input[position.y + 1].substring(position.x - 1, position.x + digits + 1)

        val gears = mutableListOf<GearSymbol>()
        gears.addAll(upperBorder.mapIndexedNotNull { index, c ->
            if (c == '*') {
                GearSymbol(Position(position.x - 1 + index, position.y - 1), this)
            } else {
                null
            }
        })

        gears.addAll(lowerBorder.mapIndexedNotNull { index, c ->
            if (c == '*') {
                GearSymbol(Position(position.x - 1 + index, position.y + 1), this)
            } else {
                null
            }
        })

        if (leftBorder == '*') {
            gears.add(GearSymbol(Position(position.x - 1, position.y), this))
        }
        if (rightBorder == '*') {
            gears.add(GearSymbol(Position(position.x + this.digits, position.y), this))
        }
        return gears
    }
}

private fun List<String>.addBorder(): List<String> {
    val ret = mutableListOf(".".repeat(this[0].length + 2))
    ret.addAll(this.map { ".$it." })
    ret.add(".".repeat(this[0].length + 2))
    return ret
}

private fun parseNumbers(input: List<String>): List<Number> {
    return input.flatMapIndexed { lineIndex, line ->
        line.splitNumbersWithIndices()
            .map { Number(it.number.toInt(), it.number.length, Position(it.index, lineIndex)) }
    }
}

private class NumberWithIndex(val index: Int, val number: String)

private fun String.splitNumbersWithIndices(): List<NumberWithIndex> {
    var currentPos = 0
    var remainingString = this

    val numbersList = mutableListOf<NumberWithIndex>()

    while (remainingString.any { it.isDigit() }) {
        val firstDigit = remainingString.indexOfFirst { it.isDigit() }
        currentPos += firstDigit
        remainingString = remainingString.substring(firstDigit)

        if (remainingString.isNumber()) {
            numbersList.add(NumberWithIndex(currentPos, remainingString))
            return numbersList
        } else {
            val firstNonDigit = remainingString.indexOfFirst { !it.isDigit() }
            val number = remainingString.substring(0, firstNonDigit)
            numbersList.add(NumberWithIndex(currentPos, number))
            currentPos += firstNonDigit
            remainingString = remainingString.substring(firstNonDigit)
        }
    }

    return numbersList
}


private fun String.isNumber(): Boolean {
    return all { it.isDigit() } && isNotEmpty()
}

fun day3Part2(input: List<String>): Int {
    val augmentedInput = input.addBorder()

    val numbers = parseNumbers(augmentedInput)
    val gearSymbols = numbers.flatMap { it.getGearSymbols(augmentedInput) }

    return gearSymbols.sumOf { g1 ->
        gearSymbols.sumOf { g2 ->
            if (g1.position == g2.position && g1.number.position != g2.number.position) {
                g1.number.value * g2.number.value
            } else {
                0
            }
        }
    } / 2
}