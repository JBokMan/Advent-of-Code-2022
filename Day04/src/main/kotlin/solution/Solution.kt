package solution

import java.io.File

fun includesRange(firstStart: Int, firstEnd: Int, secondStart: Int, secondEnd: Int): Boolean {
    if (firstStart == secondStart || firstEnd == secondEnd) {
        return true
    }
    if (secondStart > firstStart) {
        if (secondEnd < firstEnd) {
            return true
        }
    } else {
        if (firstEnd < secondEnd) {
            return true
        }
    }
    return false
}

fun rangeOverlap(firstStart: Int, firstEnd: Int, secondStart: Int, secondEnd: Int): Boolean {
    if (includesRange(firstStart, firstEnd, secondStart, secondEnd)) {
        return true
    }
    if (secondStart in firstStart until firstEnd+1) {
        return true
    }
    if (secondEnd in firstStart until firstEnd+1) {
        return true
    }
    return false
}

fun main() {

    // read input
    val input = mutableListOf<String>()
    File("src/main/kotlin/solution/Input").forEachLine { input.add(it) }

    var sum = 0

    input.forEach { line ->
        val splittedLine = line.split(',')
        val firstElveRange = splittedLine[0].split('-')
        val secondElveRange = splittedLine[1].split('-')

        val result = includesRange(
            firstElveRange[0].toInt(),
            firstElveRange[1].toInt(),
            secondElveRange[0].toInt(),
            secondElveRange[1].toInt()
        )
        if (result) {
            sum += 1
        }
    }

    println(sum)

    // part 2
    sum = 0

    input.forEach { line ->
        val splittedLine = line.split(',')
        val firstElveRange = splittedLine[0].split('-')
        val secondElveRange = splittedLine[1].split('-')

        val result = rangeOverlap(
            firstElveRange[0].toInt(),
            firstElveRange[1].toInt(),
            secondElveRange[0].toInt(),
            secondElveRange[1].toInt()
        )
        if (result) {
            sum += 1
        }
    }

    println(sum)
}

