private class Node(val name: String, val leftNode: String, val rightNode: String)

private fun parseNodes(input: List<String>): List<Node> {
    val nodes = input.drop(2).map {
        val name = it.substring(0, 3)
        val leftNode = it.substring(7, 10)
        val rightNode = it.substring(12, 15)
        Node(name, leftNode, rightNode)
    }
    return nodes.sortedBy { it.name }
}

fun day8Part1(input: List<String>): Int {

    val instructions = input.first()
    val nodes = parseNodes(input)

    var puzzleIterations = 0
    var currentNode = "AAA"
    while (currentNode != "ZZZ") {
        val n = nodes.first { it.name == currentNode }
        val instruction = instructions[puzzleIterations % instructions.length]

        currentNode = if (instruction == 'L') n.leftNode else n.rightNode
        puzzleIterations++
    }
    return puzzleIterations
}


private fun getNextNode(name: String, allNodes: List<Node>, instruction: Char): String {
    val i = allNodes.binarySearch { it.name.compareTo(name) }
    return if (instruction == 'L') allNodes[i].leftNode else allNodes[i].rightNode
}

fun day8Part2(input: List<String>): Long {
    val instructions = input.first()
    val allNodes = parseNodes(input)

    var puzzleIterations = 0L
    val currentNodes = allNodes.filter { it.name.endsWith("A") }.map { it.name }.toTypedArray()

    val hits = Array<MutableList<Long>>(currentNodes.size) { mutableListOf() }

    repeat(1_000_000)
    {
        val instruction = instructions[(puzzleIterations % instructions.length).toInt()]
        puzzleIterations++
        for (i in currentNodes.indices) {
            currentNodes[i] = getNextNode(currentNodes[i], allNodes, instruction)
            if (currentNodes[i][2] == 'Z')
                hits[i].add(puzzleIterations)
        }
    }

    //For the full puzzle input it seems that 'Z's are always found after a full iteration of instructions
    val onlyAfterFullIteration = hits.flatMap { it.map { entry -> entry % instructions.length } }.all { it == 0L }

    // This makes the solution for the full puzzle input just the least common multiple
    return if (onlyAfterFullIteration) {
        hits.map { it.first() / instructions.length }.fold(1L) { acc, it -> acc * it } * instructions.length
    } else {
        day8Part2RawSolution(input)
    }
}

fun day8Part2RawSolution(input: List<String>): Long {
    val instructions = input.first()
    val allNodes = parseNodes(input)

    var puzzleIterations = 0L
    val currentNodes = allNodes.filter { it.name.endsWith("A") }.map { it.name }.toTypedArray()
    loop@ while (true) {
        val instruction = instructions[(puzzleIterations % instructions.length).toInt()]
        puzzleIterations++

        for (i in currentNodes.indices) {
            currentNodes[i] = getNextNode(currentNodes[i], allNodes, instruction)
        }

        if (puzzleIterations % 1_000_000L == 0L)
            println("$puzzleIterations")

        for (s in currentNodes) {
            if (!s.endsWith("Z"))
                continue@loop
        }
        return puzzleIterations
    }
}
