private enum class Cards(val character: Char, val value: Int) {
    Joker('R', 0),
    Two('2', 1),
    Three('3', 2),
    Four('4', 3),
    Five('5', 4),
    Six('6', 5),
    Seven('7', 6),
    Eight('8', 7),
    Nine('9', 8),
    T('T', 9),
    J('J', 10),
    Q('Q', 11),
    K('K', 12),
    A('A', 13)
}

private enum class HandType(val rank: Int) {
    FiveOfAKind(6),
    FourOfAKind(5),
    FullHouse(4),
    ThreeOfAKind(3),
    TwoPair(2),
    OnePair(1),
    HighCard(0)
}

private class Hand(val cards: List<Cards>, val bid: Int) : Comparable<Hand> {
    override fun compareTo(other: Hand): Int {
        val typeA = this.getType()
        val typeB = other.getType()

        if (typeA == typeB) {
            if (cards[0] == other.cards[0]) {
                if (cards[1] == other.cards[1]) {
                    if (cards[2] == other.cards[2]) {
                        if (cards[3] == other.cards[3]) {
                            return cards[4].value.compareTo(other.cards[4].value)
                        }
                        return cards[3].value.compareTo(other.cards[3].value)
                    }
                    return cards[2].value.compareTo(other.cards[2].value)
                }
                return cards[1].value.compareTo(other.cards[1].value)
            }
            return cards[0].value.compareTo(other.cards[0].value)
        } else {
            return typeA.rank.compareTo(typeB.rank)
        }
    }

    fun getType(): HandType {
        val jokers = cards.count { it == Cards.Joker }
        val cardsWithoutJokers = cards.filterNot { it == Cards.Joker }

        val group = cardsWithoutJokers.groupBy { it.character }
        val maxOfAKind = (group.maxOfOrNull { it.value.size } ?: 0) + jokers
        val secondOfAKind = group.values.sortedByDescending { it.size }.getOrNull(1)?.size ?: 0

        when (maxOfAKind) {
            5 -> return HandType.FiveOfAKind
            4 -> return HandType.FourOfAKind
            3 -> return if (secondOfAKind == 2) HandType.FullHouse else HandType.ThreeOfAKind
            2 -> return if (secondOfAKind == 2) HandType.TwoPair else HandType.OnePair
            1 -> return HandType.HighCard
        }
        throw Exception("Parsing Error")
    }
}

fun day7Part1(input: List<String>): Long {
    val hands = parseHands(input)
    return hands.sorted().mapIndexed { index: Int, hand: Hand -> (index + 1).toLong() * hand.bid.toLong() }.sum()
}

private fun parseHands(input: List<String>) = input.map {
    val cards = it.split(" ").first().map { c -> c.toCard() }
    val bid = it.split(" ").last().toInt()
    Hand(cards, bid)
}

private fun Char.toCard(): Cards {
    return Cards.values().first { this == it.character }
}

fun day7Part2(input: List<String>): Long {
    val inputWithJokerReplaced = input.map { it.replace("J", Cards.Joker.character.toString()) }
    val hands = parseHands(inputWithJokerReplaced)
    return hands.sorted().mapIndexed { index: Int, hand: Hand -> (index + 1).toLong() * hand.bid.toLong() }.sum()
}