@file:Suppress("SpellCheckingInspection")

package solution

import java.io.File

fun main() {
    // read input
    val input = mutableListOf<String>()
    File("src/main/kotlin/solution/Input").forEachLine { input.add(it) }

    println("part 1 solution ${part1(input)}")
    println("part 2 solution ${part2(input)}")
}

fun part1(input: List<String>): Int {
    val pairs = mutableListOf<Pair<String, String>>()

    for (i in input.indices step 3) {
        println(i)
        pairs.add(Pair(input[i], input[i + 1]))
    }

    var index = 1
    val rightOrderedIndexes = mutableListOf<Int>()

    pairs.forEach { pair ->
        val rightOrder = isLeftAndRightInRightOrder(pair.first, pair.second)
        println()
        if (rightOrder == true || rightOrder == null) {
            rightOrderedIndexes.add(index)
        }
        index += 1
    }

    println(rightOrderedIndexes)
    return rightOrderedIndexes.sum()
}

fun part2(input: List<String>): Int {
    val extraPackets = listOf(
        "[[2]]",
        "[[6]]"
    )
    val sortedPackets = sortWithExtraPackets(input, extraPackets)

    val index1 = sortedPackets.indexOf(extraPackets[0]) + 1
    val index2 = sortedPackets.indexOf(extraPackets[1]) + 1

    return index1 * index2
}

fun isLeftAndRightInRightOrder(left: String, right: String): Boolean? {
    println("Compare $left vs $right")
    var leftRunning = left
    var rightRunning = right

    while (hasNextElement(leftRunning)) {
        if (!hasNextElement(rightRunning)) {
            return false
        }

        val nextLeftElement = getNextElement(leftRunning)
        val nextRightElement = getNextElement(rightRunning)

        println("Compare $nextLeftElement vs $nextRightElement")

        if (isNumeric(nextLeftElement) && isNumeric(nextRightElement)) {
            if (nextLeftElement.toInt() > nextRightElement.toInt()) {
                return false
            }
            if (nextLeftElement.toInt() < nextRightElement.toInt()) {
                return true
            }
        } else if (!isNumeric(nextLeftElement) && !isNumeric(nextRightElement)) {
            val result = isLeftAndRightInRightOrder(nextLeftElement, nextRightElement)
            if (result != null) {
                return result
            }
        } else if (!isNumeric(nextLeftElement) && isNumeric(nextRightElement)) {
            val result = isLeftAndRightInRightOrder(nextLeftElement, "[$nextRightElement]")
            if (result != null) {
                return result
            }
        } else if (isNumeric(nextLeftElement) && !isNumeric(nextRightElement)) {
            val result = isLeftAndRightInRightOrder("[$nextLeftElement]", nextRightElement)
            if (result != null) {
                return result
            }
        }

        leftRunning = removeNextElement(leftRunning)
        rightRunning = removeNextElement(rightRunning)

        println()
    }

    if (hasNextElement(rightRunning)) {
        return true
    }

    return null
}

fun hasNextElement(packet: String): Boolean {
    println("hasNextElement packet: $packet")
    val nextElement = getNextElement(packet)
    val result = nextElement.isNotBlank()
    println("has next element $result")
    return result
}

fun removeNextElement(packet: String): String {
    println("removeNextElement of packet $packet")
    val nextElement = getNextElement(packet)
    var newPacket = "[${packet.substringAfter(nextElement)}"
    if (newPacket[1] == ',') {
        newPacket = "[${newPacket.substringAfter(',')}"
    }
    println("newPacket $newPacket")
    return newPacket
}

fun getNextElement(packet: String): String {
    println("getNextElement of packet $packet")

    val endIndex = findEndIndex(packet)
    val nextElement = packet.substring(startIndex = 1, endIndex = endIndex)
    println("endIndex $endIndex")

    println("nextElement is $nextElement")
    return nextElement
}

fun findEndIndex(packet: String): Int {
    val startsWithBracket = packet[1] == '['
    if (startsWithBracket) {
        var bracketCounter = 0
        for (i in 1 until packet.length) {
            if (packet[i] == '[') {
                bracketCounter += 1
            } else if (packet[i] == ']') {
                bracketCounter -= 1
            }

            if (bracketCounter == 0) {
                return i + 1
            }
        }
    } else {
        for (i in 1 until packet.length) {
            if (isNumeric(packet[i].toString())) {
                continue
            }
            return i
        }
    }
    return 1
}

fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}

fun sortWithExtraPackets(input: List<String>, extraPackets: List<String>): List<String> {
    var completeList = mutableListOf<String>()
    completeList.addAll(input)
    completeList.addAll(extraPackets)
    completeList = completeList.filter { it.isNotBlank() }.toMutableList()

    val rightOrderComparator =
        Comparator { str1: String, str2: String ->
            when (isLeftAndRightInRightOrder(str1, str2)) {
                true -> -1
                false -> 1
                null -> 0
            }
        }
    completeList.sortWith(rightOrderComparator)
    return completeList
}
