@file:Suppress("SpellCheckingInspection")

package solution

import java.io.File

fun main() {
    // read input
    val input = mutableListOf<String>()
    File("src/main/kotlin/solution/Input").forEachLine { input.add(it) }

    println("part 1 solution ${part1(input)}")
    val part2Result = part2(input)
    printCrt(part2Result)
}

fun part1(commands: List<String>): Int {
    val runningCommands = commands.iterator()
    var cycles = 0
    var xRegister = 1

    var commandProcessCycleCount = 0
    var commandFinished = false
    val registerList = mutableListOf<Int>()

    while (runningCommands.hasNext()) {
        val nextCommand = runningCommands.next()
        while (!commandFinished) {
            cycles += 1
            println("cycle: $cycles xRegister: $xRegister command: $nextCommand commandProcessCycleCount: $commandProcessCycleCount")
            registerList.add(xRegister)
            if (nextCommand == "noop") {
                commandFinished = true
            }
            if (nextCommand.startsWith("addx")) {
                if (commandProcessCycleCount == 1) {
                    xRegister += nextCommand.split(" ")[1].toInt()
                    commandFinished = true
                }
            }
            commandProcessCycleCount += 1
        }

        commandFinished = false
        commandProcessCycleCount = 0
    }

    val startIndex = 19
    val filteredRegisters = mutableListOf<Pair<Int, Int>>()
    for (i in startIndex until registerList.size step 40) {
        filteredRegisters.add(Pair(registerList[i], i + 1))
    }
    println(filteredRegisters)

    return filteredRegisters.sumOf { pair -> pair.first * pair.second }
}

fun part2(commands: List<String>): Array<CharArray> {
    val crt: Array<CharArray> =
        Array(size = 6) { CharArray(size = 40, init = { '.' }) }

    val runningCommands = commands.iterator()
    var cycles = 0
    var row = 0
    var xRegister = 1

    var commandProcessCycleCount = 0
    var commandFinished = false

    while (runningCommands.hasNext()) {
        val nextCommand = runningCommands.next()
        while (!commandFinished) {
            cycles += 1

            val column = (cycles - 1) % 40
            if (xRegister in IntRange(start = column - 1, endInclusive = column + 1)) {
                crt[row][column] = '#'
            }

            if (nextCommand == "noop") {
                commandFinished = true
            }
            if (nextCommand.startsWith("addx")) {
                if (commandProcessCycleCount == 1) {
                    xRegister += nextCommand.split(" ")[1].toInt()
                    commandFinished = true
                }
            }
            commandProcessCycleCount += 1

            if ((cycles) % 40 == 0) {
                row += 1
            }
        }

        commandFinished = false
        commandProcessCycleCount = 0
    }

    return crt
}

fun printCrt(crt: Array<CharArray>) {
    for (i in crt.indices) {
        for (j in crt[0].indices) {
            print(crt[i][j])
        }
        println()
    }
}