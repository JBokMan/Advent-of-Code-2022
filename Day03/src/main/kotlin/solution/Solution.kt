package solution

import java.io.File

fun main() {
    val priorities = arrayOf(
        '_',
        'a',
        'b',
        'c',
        'd',
        'e',
        'f',
        'g',
        'h',
        'i',
        'j',
        'k',
        'l',
        'm',
        'n',
        'o',
        'p',
        'q',
        'r',
        's',
        't',
        'u',
        'v',
        'w',
        'x',
        'y',
        'z'
    )

    // read input
    val input = mutableListOf<String>()
    File("src/main/kotlin/solution/Input").forEachLine { input.add(it) }

    var sumOfPriorities = 0

    // process input
    input.forEach { line ->
        val firstHalf = line.subSequence(0, line.length / 2)
        val secondHalf = line.subSequence(line.length / 2, line.lastIndex + 1)

        val firstHalfSortedSet = firstHalf.toSortedSet()
        val secondHalfSortedSet = secondHalf.toSortedSet()

        val wrongItem = firstHalfSortedSet.intersect(secondHalfSortedSet).toList().first()
        val isUpperCase = wrongItem.isUpperCase()
        val priorityOfItem: Int =
            if (isUpperCase)
                26 + priorities.indexOf(
                    wrongItem.lowercase().toCharArray().first()
                )
            else priorities.indexOf(wrongItem)

        sumOfPriorities += priorityOfItem
    }

    // print result of part 1
    println(sumOfPriorities)

    // part 2
    var priorityOfBadges = 0

    val iterator = input.iterator()
    while (iterator.hasNext()) {
        val elf1 = iterator.next()
        val elf2 = iterator.next()
        val elf3 = iterator.next()

        val elf1SortedSet = elf1.toSortedSet()
        val elf2SortedSet = elf2.toSortedSet()
        val elf3SortedSet = elf3.toSortedSet()

        val badge = elf1SortedSet.intersect(elf2SortedSet).intersect(elf3SortedSet).toList().first()
        val isUpperCase = badge.isUpperCase()
        val priorityOfItem: Int =
            if (isUpperCase)
                26 + priorities.indexOf(
                    badge.lowercase().toCharArray().first()
                )
            else priorities.indexOf(badge)

        priorityOfBadges += priorityOfItem
    }

    // print result of part 2
    println(priorityOfBadges)
}

