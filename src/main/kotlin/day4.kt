private class Card(val winningNumbers: List<Int>, val numbersYouHave: List<Int>, var timesWon: Int = 1)

private fun Int.doubleWinnings(): Int {
    return 1.shl(this - 1)
}

fun day4Part1(input: List<String>): Int {
    val cards = parseCards(input)

    return cards.sumOf { card ->
        (card.numbersYouHave.count { card.winningNumbers.contains(it) }).doubleWinnings()
    }
}

private fun parseCards(input: List<String>): List<Card> {
    return input.map { s ->
        val winningNumbers =
            s.substringAfter(": ").substringBefore(" |").split(" ").filterNot { it.isEmpty() }.map { it.trim().toInt() }
        val yourNumbers = s.substringAfter("| ").split(" ").filterNot { it.isEmpty() }.map { it.trim().toInt() }
        Card(winningNumbers, yourNumbers)
    }
}

fun day4Part2(input: List<String>): Int {
    val cards = parseCards(input)
    cards.forEachIndexed { index, card ->
        val winningsAmount = (card.numbersYouHave.count { card.winningNumbers.contains(it) })
        if (winningsAmount > 0) {
            for (i in index + 1..index + winningsAmount) {
                cards[i].timesWon += card.timesWon
            }
        }
    }
    return cards.sumOf { it.timesWon }
}