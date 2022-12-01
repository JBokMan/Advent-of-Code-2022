package day01

import java.io.File

fun main() {
    // Read input
    val input = mutableListOf<String>()
    File("src/main/kotlin/day01/day01-input").forEachLine { input.add(it) }

    // Sum up calories of each elf
    val elves = mutableListOf(0)
    for (line in input) {
        if (line.isEmpty()) {
            elves.add(0)
        } else {
            elves[elves.size - 1] = elves.last().plus(line.toInt())
        }
    }

    // Print result part one
    println(elves.max())


    elves.sortDescending()
    val topElves = elves.take(3)

    // Print result part two
    println(topElves.sum())
}