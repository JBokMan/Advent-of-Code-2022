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
    val paths = extractPathsFromInput(input)
    val range = getXYValueRange(paths)
    val firstXIndex = range.first.first
    val rows = range.second.second + 2
    val columns = range.second.second - range.second.first + 3

    var grid: Array<CharArray> = Array(size = rows) { CharArray(size = columns, init = { '.' }) }
    grid = addSource(grid, firstXIndex)

    printGrid(grid)

    grid = addRocks(paths, grid, firstXIndex)

    var count = 0
    try {
        while (true) {
            grid = dropSandAt(grid, getXIndexOf(500, firstXIndex), 0)
            count += 1
        }
    } catch (e: ArrayIndexOutOfBoundsException) {

    }

    return count
}

fun part2(input: List<String>): Int {
    val puffer = 10000
    val paths = extractPathsFromInput(input)
    val range = getXYValueRange(paths)
    val firstXIndex = range.first.first - puffer
    val rows = range.second.second + 3
    val columns = (range.first.second + puffer) - (range.first.first - puffer) + 3

    println(range)

    var grid: Array<CharArray> = Array(size = rows) { CharArray(size = columns, init = { '.' }) }
    grid = addSource(grid, firstXIndex)

    printGrid(grid)

    grid = addRocks(paths, grid, firstXIndex)
    grid = addFloor(grid)

    printGrid(grid)

    var count = 0
    try {
        while (true) {
            grid = dropSandAt(grid, getXIndexOf(500, firstXIndex), 0)
            count += 1
        }
    } catch (e: ArrayIndexOutOfBoundsException) {

    }

    return count
}

fun addFloor(grid: Array<CharArray>): Array<CharArray> {
    grid[grid.size - 1] = CharArray(grid[0].size) { '#' }
    return grid
}

fun addSource(grid: Array<CharArray>, firstXIndex: Int): Array<CharArray> {
    val source = Pair(getXIndexOf(500, firstXIndex), 0)
    grid[source.second][source.first] = '+'
    return grid
}

fun extractPathsFromInput(input: List<String>): List<List<Pair<Int, Int>>> {
    val paths = mutableListOf<MutableList<Pair<Int, Int>>>()
    input.forEach { line ->
        val path = mutableListOf<Pair<Int, Int>>()
        var runningLine = line
        while (hasNextPair(runningLine)) {
            path.add(getNextPair(runningLine))
            runningLine = removeNextPair(runningLine)
        }
        paths.add(path)
    }
    return paths
}


fun addRocks(paths: List<List<Pair<Int, Int>>>, grid: Array<CharArray>, xStartIndex: Int): Array<CharArray> {
    paths.forEach { path ->
        for (i in 1 until path.size) {
            val part1 = path[i - 1]
            val part2 = path[i]
            val isVertical = part1.first == part2.first
            if (isVertical) {
                val xValue = getXIndexOf(part1.first, xStartIndex)
                val range = getRange(part1.second, part2.second)
                for (i in range.first..range.second) {
                    grid[i][xValue] = '#'
                }
            } else {
                val yValue = part1.second
                val range = getRange(part1.first, part2.first)
                for (i in range.first..range.second) {
                    val xValue = getXIndexOf(i, xStartIndex)
                    grid[yValue][xValue] = '#'
                }
            }
        }
    }
    return grid
}

fun getRange(first: Int, second: Int): Pair<Int, Int> {
    if (first >= second) {
        return Pair(second, first)
    }
    return Pair(first, second)
}

fun printGrid(grid: Array<CharArray>) {
    for (i in grid.indices) {
        for (j in grid[0].indices) {
            print(grid[i][j])
        }
        println()
    }
    println()
}

fun getXIndexOf(scaledIndex: Int, first: Int): Int {
    return scaledIndex - (first - 1)
}

fun removeNextPair(line: String): String {
    return line.substringAfter(" -> ", "")
}

fun hasNextPair(line: String): Boolean {
    return line.isNotBlank()
}

fun getNextPair(line: String): Pair<Int, Int> {
    val parts = line.substringBefore(" ->").split(',')
    return Pair(parts[0].toInt(), parts[1].toInt())
}

fun getXYValueRange(paths: List<List<Pair<Int, Int>>>): Pair<Pair<Int, Int>, Pair<Int, Int>> {
    var smallestXValue = 500
    var biggestXValue = 500

    var smallestYValue = 0
    var biggestYValue = 0

    paths.forEach { path ->
        path.forEach { point ->
            if (point.first < smallestXValue) {
                smallestXValue = point.first
            }
            if (point.first > biggestXValue) {
                biggestXValue = point.first
            }
            if (point.second < smallestYValue) {
                smallestYValue = point.second
            }
            if (point.second > biggestYValue) {
                biggestYValue = point.second
            }
        }
    }

    return Pair(Pair(smallestXValue, biggestXValue), Pair(smallestYValue, biggestYValue))
}

fun dropSand(grid: Array<CharArray>, xStartIndex: Int): Array<CharArray> {
    val source = Pair(getXIndexOf(500, xStartIndex), 0)
    var y = 1
    while (grid[y][source.first] != '#' && grid[y][source.first] != 'o') {
        y += 1
    }
    if (y == 1)
        if (grid[y][source.first] == 'o') {
            if (grid[y][source.first - 1] == '.') {
                return dropSandAt(grid, source.first - 1, y)
            } else if (grid[y][source.first + 1] == '.') {
                return dropSandAt(grid, source.first + 1, y)
            } else {
                grid[y - 1][source.first] = 'o'
            }
        } else {
            grid[y - 1][source.first] = 'o'
        }
    return grid
}

fun dropSandAt(grid: Array<CharArray>, x: Int, y: Int): Array<CharArray> {
    if (grid[y][x] == 'o') {
        throw ArrayIndexOutOfBoundsException()
    }
    if (grid[y + 1][x] == '.') {
        return dropSandAt(grid, x, y + 1)
    }
    if (grid[y + 1][x - 1] == '.') {
        return dropSandAt(grid, x - 1, y + 1)
    } else if (grid[y + 1][x + 1] == '.') {
        return dropSandAt(grid, x + 1, y + 1)
    }

    grid[y][x] = 'o'

    return grid
}


