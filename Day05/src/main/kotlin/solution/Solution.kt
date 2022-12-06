package solution

import java.io.File

fun part1() {
    val stack1: ArrayDeque<Char> = ArrayDeque(elements = listOf('M', 'J', 'C', 'B', 'F', 'R', 'L', 'H'))
    val stack2: ArrayDeque<Char> = ArrayDeque(elements = listOf('Z', 'C', 'D'))
    val stack3: ArrayDeque<Char> = ArrayDeque(elements = listOf('H', 'J', 'F', 'C', 'N', 'G', 'W'))
    val stack4: ArrayDeque<Char> = ArrayDeque(elements = listOf('P', 'J', 'D', 'M', 'T', 'S', 'B'))
    val stack5: ArrayDeque<Char> = ArrayDeque(elements = listOf('N', 'C', 'D', 'R', 'J'))
    val stack6: ArrayDeque<Char> = ArrayDeque(elements = listOf('W', 'L', 'D', 'Q', 'P', 'J', 'G', 'Z'))
    val stack7: ArrayDeque<Char> = ArrayDeque(elements = listOf('P', 'Z', 'T', 'F', 'R', 'H'))
    val stack8: ArrayDeque<Char> = ArrayDeque(elements = listOf('L', 'V', 'M', 'G'))
    val stack9: ArrayDeque<Char> = ArrayDeque(elements = listOf('C', 'B', 'G', 'P', 'F', 'q', 'R', 'J'))

    val containerStacks = listOf(stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9)

    // read input
    val input = mutableListOf<String>()
    File("src/main/kotlin/solution/Input").forEachLine { input.add(it) }

    input.forEach { line ->
        val lineParts = line.split(' ')
        val quantity = lineParts[1].toInt()
        val fromIndex = lineParts[3].toInt()
        val toIndex = lineParts[5].toInt()
        for (i in 0 until quantity) {
            val container = containerStacks[fromIndex - 1].removeLast()
            containerStacks[toIndex - 1].addLast(container)
        }
    }

    // print part 1 result
    containerStacks.forEach { stack ->
        print(stack.last())
    }
}

fun part2() {
    val stack1: ArrayDeque<Char> = ArrayDeque(elements = listOf('M', 'J', 'C', 'B', 'F', 'R', 'L', 'H'))
    val stack2: ArrayDeque<Char> = ArrayDeque(elements = listOf('Z', 'C', 'D'))
    val stack3: ArrayDeque<Char> = ArrayDeque(elements = listOf('H', 'J', 'F', 'C', 'N', 'G', 'W'))
    val stack4: ArrayDeque<Char> = ArrayDeque(elements = listOf('P', 'J', 'D', 'M', 'T', 'S', 'B'))
    val stack5: ArrayDeque<Char> = ArrayDeque(elements = listOf('N', 'C', 'D', 'R', 'J'))
    val stack6: ArrayDeque<Char> = ArrayDeque(elements = listOf('W', 'L', 'D', 'Q', 'P', 'J', 'G', 'Z'))
    val stack7: ArrayDeque<Char> = ArrayDeque(elements = listOf('P', 'Z', 'T', 'F', 'R', 'H'))
    val stack8: ArrayDeque<Char> = ArrayDeque(elements = listOf('L', 'V', 'M', 'G'))
    val stack9: ArrayDeque<Char> = ArrayDeque(elements = listOf('C', 'B', 'G', 'P', 'F', 'q', 'R', 'J'))

    val containerStacks = listOf(stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9)

    // read input
    val input = mutableListOf<String>()
    File("src/main/kotlin/solution/Input").forEachLine { input.add(it) }

    input.forEach { line ->
        val lineParts = line.split(' ')
        val quantity = lineParts[1].toInt()
        val fromIndex = lineParts[3].toInt()
        val toIndex = lineParts[5].toInt()
        val buffer = mutableListOf<Char>()
        for (i in 0 until quantity) {
            buffer.add(containerStacks[fromIndex - 1].removeLast())

        }
        containerStacks[toIndex - 1].addAll(buffer.reversed())
    }

    // print part 1 result
    containerStacks.forEach { stack ->
        print(stack.last())
    }
}

fun main() {
    part1()
    println()
    part2()
}
