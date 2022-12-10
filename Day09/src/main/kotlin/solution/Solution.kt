package solution

import java.io.File
import kotlin.math.absoluteValue

enum class Direction {
    UP, DOWN, LEFT, RIGHT;
}


fun main() {
    // read input
    val input = mutableListOf<String>()
    File("src/main/kotlin/solution/Input").forEachLine { input.add(it) }

    println(part1(input))
    println(part2(input))
}

fun part1(input: List<String>): Int {
    val moves = generateMoveSequence(input)
    val gridAttributes = calculateGrid(moves)

    val gridSizes = gridAttributes.first
    val rows = gridSizes.first
    val columns = gridSizes.second

    val startingPosition = gridAttributes.second
    val startRowIndex = startingPosition.first
    val startColumnIndex = startingPosition.second

    val grid: Array<CharArray> = Array(size = rows) { CharArray(size = columns, init = { '.' }) }
    var headPosition = Pair(startRowIndex, startColumnIndex)
    var tailPosition = Pair(startRowIndex, startColumnIndex)
    grid[startRowIndex][startColumnIndex] = '#'

    // process moves
    moves.forEach { move ->
        headPosition = when (move) {
            Direction.UP -> moveUp(headPosition)
            Direction.DOWN -> moveDown(headPosition)
            Direction.LEFT -> moveLeft(headPosition)
            Direction.RIGHT -> moveRight(headPosition)
        }


        if (tailPosition.first == headPosition.first && tailPosition.second == headPosition.second + 2) {
            tailPosition = moveLeft(tailPosition)
        } else if (tailPosition.first == headPosition.first && tailPosition.second == headPosition.second - 2) {
            tailPosition = moveRight(tailPosition)
        } else if (tailPosition.second == headPosition.second && tailPosition.first == headPosition.first + 2) {
            tailPosition = moveUp(tailPosition)
        } else if (tailPosition.second == headPosition.second && tailPosition.first == headPosition.first - 2) {
            tailPosition = moveDown(tailPosition)
        } else if (!areTouching(headPosition, tailPosition) && (!sameColumn(
                headPosition, tailPosition
            ) || !sameRow(headPosition, tailPosition))
        ) {
            if (tailPosition.first < headPosition.first) {
                if (tailPosition.second < headPosition.second) {
                    tailPosition = moveRightAndDown(tailPosition)
                }
                if (tailPosition.second > headPosition.second) {
                    tailPosition = moveLeftAndDown(tailPosition)
                }
            } else if (tailPosition.first > headPosition.first) {
                if (tailPosition.second < headPosition.second) {
                    tailPosition = moveRightAndUp(tailPosition)
                }
                if (tailPosition.second > headPosition.second) {
                    tailPosition = moveLeftAndUp(tailPosition)
                }
            }
        }
        grid[tailPosition.first][tailPosition.second] = '#'
    }

    var spotsVisited = 0
    for (i in 0 until rows) {
        spotsVisited += grid[i].filter { c -> c == '#' }.size
    }

    return spotsVisited
}

fun part2(input: List<String>): Int {
    val moves = generateMoveSequence(input)
    val gridAttributes = calculateGrid(moves)

    val gridSizes = gridAttributes.first
    val rows = gridSizes.first
    val columns = gridSizes.second

    val startingPosition = gridAttributes.second
    val startRowIndex = startingPosition.first
    val startColumnIndex = startingPosition.second

    val grid: Array<CharArray> = Array(size = rows) { CharArray(size = columns, init = { '.' }) }

    val positions = mutableListOf<Pair<Int, Int>>()
    repeat(10) {
        positions.add(Pair(startRowIndex, startColumnIndex))
    }
    grid[startRowIndex][startColumnIndex] = '#'

    // process moves
    moves.forEach { move ->
        positions[0] = when (move) {
            Direction.UP -> moveUp(positions[0])
            Direction.DOWN -> moveDown(positions[0])
            Direction.LEFT -> moveLeft(positions[0])
            Direction.RIGHT -> moveRight(positions[0])
        }

        for (i in 1 until positions.size) {
            if (positions[i].first == positions[i - 1].first && positions[i].second == positions[i - 1].second + 2) {
                positions[i] = moveLeft(positions[i])
            } else if (positions[i].first == positions[i - 1].first && positions[i].second == positions[i - 1].second - 2) {
                positions[i] = moveRight(positions[i])
            } else if (positions[i].second == positions[i - 1].second && positions[i].first == positions[i - 1].first + 2) {
                positions[i] = moveUp(positions[i])
            } else if (positions[i].second == positions[i - 1].second && positions[i].first == positions[i - 1].first - 2) {
                positions[i] = moveDown(positions[i])
            } else if (!areTouching(positions[i - 1], positions[i]) && (!sameColumn(
                    positions[i - 1], positions[i]
                ) || !sameRow(positions[i - 1], positions[i]))
            ) {
                if (positions[i].first < positions[i - 1].first) {
                    if (positions[i].second < positions[i - 1].second) {
                        positions[i] = moveRightAndDown(positions[i])
                    }
                    if (positions[i].second > positions[i - 1].second) {
                        positions[i] = moveLeftAndDown(positions[i])
                    }
                } else if (positions[i].first > positions[i - 1].first) {
                    if (positions[i].second < positions[i - 1].second) {
                        positions[i] = moveRightAndUp(positions[i])
                    }
                    if (positions[i].second > positions[i - 1].second) {
                        positions[i] = moveLeftAndUp(positions[i])
                    }
                }
            }
        }
        grid[positions.last().first][positions.last().second] = '#'
    }

    var spotsVisited = 0
    for (i in 0 until rows) {
        spotsVisited += grid[i].filter { c -> c == '#' }.size
    }

    return spotsVisited
}


fun areTouching(headPosition: Pair<Int, Int>, tailPosition: Pair<Int, Int>): Boolean {
    if (headPosition.first == tailPosition.first && headPosition.second == tailPosition.second) {
        return true
    }
    if (headPosition.first == tailPosition.first) {
        if (headPosition.second - 1 == tailPosition.second) {
            return true
        }
        if (headPosition.second + 1 == tailPosition.second) {
            return true
        }
    }
    if (headPosition.second == tailPosition.second) {
        if (headPosition.first + 1 == tailPosition.first) {
            return true
        }
        if (headPosition.first - 1 == tailPosition.first) {
            return true
        }
    }
    if (headPosition.first == tailPosition.first + 1 && headPosition.second == tailPosition.second + 1) {
        return true
    }
    if (headPosition.first == tailPosition.first + 1 && headPosition.second == tailPosition.second - 1) {
        return true
    }
    if (headPosition.first == tailPosition.first - 1 && headPosition.second == tailPosition.second + 1) {
        return true
    }
    if (headPosition.first == tailPosition.first - 1 && headPosition.second == tailPosition.second - 1) {
        return true
    }
    return false
}

fun printGrid(grid: Array<CharArray>, headPosition: Pair<Int, Int>, tailPosition: Pair<Int, Int>) {
    for (i in grid.indices) {
        for (j in grid[0].indices) {
            if (i == headPosition.first && j == headPosition.second) {
                print('H')
            } else if (i == tailPosition.first && j == tailPosition.second) {
                print('T')
            } else {
                print(grid[i][j])
            }
        }
        println()
    }
    println()
}

fun printGrid(grid: Array<CharArray>, positions: List<Pair<Int, Int>>) {
    for (i in grid.indices) {
        for (j in grid[0].indices) {
            var toPrint = grid[i][j]
            for (k in positions.size - 1 downTo 0) {
                if (i == positions[k].first && j == positions[k].second) {
                    toPrint = k.digitToChar()
                }
            }
            print(toPrint)
        }
        println()
    }
    println()
}

fun calculateGrid(moves: List<Direction>): Pair<Pair<Int, Int>, Pair<Int, Int>> {
    var currentRow = 0
    var currentColumn = 0

    var rowsToTop = 0
    var rowsToBottom = 0

    var columnsToLeft = 0
    var columnsToRight = 0

    moves.forEach { move ->
        when (move) {
            Direction.UP -> currentRow += 1
            Direction.DOWN -> currentRow -= 1
            Direction.LEFT -> currentColumn -= 1
            Direction.RIGHT -> currentColumn += 1
        }

        if (currentRow > 0 && currentRow > rowsToTop) {
            rowsToTop = currentRow
        }
        if (currentRow < 0 && currentRow.absoluteValue > rowsToBottom) {
            rowsToBottom = currentRow.absoluteValue
        }
        if (currentColumn > 0 && currentColumn > columnsToRight) {
            columnsToRight = currentColumn
        }
        if (currentColumn < 0 && currentColumn.absoluteValue > columnsToLeft) {
            columnsToLeft = currentColumn.absoluteValue
        }
    }

    val numberOfRows = rowsToTop + rowsToBottom + 1
    val numberOfColumns = columnsToLeft + columnsToRight + 1
    val rowStartIndex = rowsToTop
    val columnStartIndex = columnsToLeft

    return Pair(first = Pair(numberOfRows, numberOfColumns), second = Pair(rowStartIndex, columnStartIndex))
}

fun generateMoveSequence(input: List<String>): List<Direction> {
    val moves = mutableListOf<Direction>()
    input.forEach { line ->
        val parts = line.split(" ")
        val direction: Direction = when (parts[0]) {
            "U" -> Direction.UP
            "D" -> Direction.DOWN
            "L" -> Direction.LEFT
            "R" -> Direction.RIGHT
            else -> null
        }!!

        val moveCount = parts[1].toInt()

        repeat(moveCount) {
            moves.add(direction)
        }
    }
    return moves
}

fun sameColumn(position1: Pair<Int, Int>, position2: Pair<Int, Int>): Boolean {
    val column1 = position1.second
    val column2 = position2.second
    return column1 == column2
}

fun sameRow(pair: Pair<Int, Int>, pair1: Pair<Int, Int>): Boolean {
    val rowA = pair.first
    val rowB = pair1.first
    return rowA == rowB
}

fun moveUp(knot: Pair<Int, Int>): Pair<Int, Int> {
    return knot.copy(first = knot.first - 1)
}

fun moveDown(knot: Pair<Int, Int>): Pair<Int, Int> {
    return knot.copy(first = knot.first + 1)
}

fun moveLeft(knot: Pair<Int, Int>): Pair<Int, Int> {
    return knot.copy(second = knot.second - 1)
}

fun moveRight(knot: Pair<Int, Int>): Pair<Int, Int> {
    return knot.copy(second = knot.second + 1)
}

fun moveLeftAndDown(knot: Pair<Int, Int>): Pair<Int, Int> {
    return moveDown(moveLeft(knot))
}

fun moveLeftAndUp(knot: Pair<Int, Int>): Pair<Int, Int> {
    return moveUp(moveLeft(knot))
}

fun moveRightAndDown(knot: Pair<Int, Int>): Pair<Int, Int> {
    return moveDown(moveRight(knot))
}

fun moveRightAndUp(knot: Pair<Int, Int>): Pair<Int, Int> {
    return moveUp(moveRight(knot))
}

