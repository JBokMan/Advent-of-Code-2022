package solution

import java.io.File

fun main() {
    // read input
    val input = mutableListOf<String>()
    File("src/main/kotlin/solution/Input").forEachLine { input.add(it) }

    var index = 0
    val grid: Array<IntArray> = Array(size = input.size) { intArrayOf(0) }
    input.forEach { line ->
        val parts = line.split("").subList(1, line.length + 1)
        val result = parts.map { it.toInt() }.toIntArray()
        grid[index] = result
        index += 1
    }

    // process
    val solutionGrid: Array<IntArray> = Array(size = grid.size) { IntArray(size = grid[0].size) }

    val highestTrees = arrayOf(-1, -1, -1, -1)
    for (i in grid.indices) {
        for (j in 0 until grid[0].size) {
            highestTrees[0] = process(
                grid = grid, row = i, column = j, currentHeight = highestTrees[0], solutionGrid = solutionGrid
            )
            highestTrees[1] = process(
                grid = grid, row = j, column = i, currentHeight = highestTrees[1], solutionGrid = solutionGrid
            )
        }
        for (j in grid[0].size - 1 downTo 0) {
            highestTrees[2] = process(
                grid = grid, row = i, column = j, currentHeight = highestTrees[2], solutionGrid = solutionGrid
            )
            highestTrees[3] = process(
                grid = grid, row = j, column = i, currentHeight = highestTrees[3], solutionGrid = solutionGrid
            )
        }
        highestTrees[0] = -1
        highestTrees[1] = -1
        highestTrees[2] = -1
        highestTrees[3] = -1
    }

    // count trees
    var count = 0
    for (i in solutionGrid.indices) {
        for (j in 0 until solutionGrid[0].size) {
            if (solutionGrid[i][j] == 1) {
                count += 1
            }
        }
    }

    // print result
    println(count)

    // part 2
    val scenicScores = mutableListOf<Int>()
    for (i in grid.indices) {
        for (j in grid[0].indices) {
            scenicScores.add(calculateScenicScore(grid, i, j))
        }
    }

    // print result
    println(scenicScores.max())
}

private fun process(
    grid: Array<IntArray>, row: Int, column: Int, currentHeight: Int, solutionGrid: Array<IntArray>
): Int {
    var newHeight = currentHeight
    if (grid[row][column] > newHeight) {
        solutionGrid[row][column] = 1
        newHeight = grid[row][column]
    }
    return newHeight
}

fun calculateScenicScore(grid: Array<IntArray>, row: Int, column: Int): Int {
    val distances = listOf(
        calculateDistance(
            startIndex = column, grid = grid, fixedIndex = row, direction = Direction.RIGHT
        ), calculateDistance(
            startIndex = column, grid = grid, fixedIndex = row, direction = Direction.LEFT
        ), calculateDistance(
            startIndex = row, grid = grid, fixedIndex = column, direction = Direction.UP
        ), calculateDistance(
            startIndex = row, grid = grid, fixedIndex = column, direction = Direction.DOWN
        )
    )

    return distances.reduce { acc, it ->
        acc * it
    }
}

enum class Direction {
    UP, DOWN, LEFT, RIGHT;
}


private fun calculateDistance(
    startIndex: Int,
    grid: Array<IntArray>,
    fixedIndex: Int,
    direction: Direction,
): Int {
    var distance = 0
    val treeHeight = when (direction) {
        Direction.UP -> grid[startIndex][fixedIndex]
        Direction.DOWN -> grid[startIndex][fixedIndex]
        Direction.LEFT -> grid[fixedIndex][startIndex]
        Direction.RIGHT -> grid[fixedIndex][startIndex]
    }

    val progression = when (direction) {
        Direction.UP -> startIndex - 1 downTo 0
        Direction.DOWN -> startIndex + 1 until grid.size
        Direction.LEFT -> startIndex - 1 downTo 0
        Direction.RIGHT -> startIndex + 1 until grid[0].size
    }

    for (i in progression) {
        val nextTreeHeight = when (direction) {
            Direction.UP -> grid[i][fixedIndex]
            Direction.DOWN -> grid[i][fixedIndex]
            Direction.LEFT -> grid[fixedIndex][i]
            Direction.RIGHT -> grid[fixedIndex][i]
        }
        distance += 1
        if (treeHeight <= nextTreeHeight) {
            break
        }
    }
    return distance
}