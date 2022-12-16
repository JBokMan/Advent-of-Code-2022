package solution

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@Suppress("SpellCheckingInspection")
internal class Part1Test {

    @Test
    fun part1Example() {
        val input = listOf(
            "498,4 -> 498,6 -> 496,6",
            "503,4 -> 502,4 -> 502,9 -> 494,9"
        )

        val result = part1(input)

        assertEquals(24, result)
    }

    @Test
    fun drop1Sand() {
        val pairOfPreparation = prepareTest()
        val grid = pairOfPreparation.first
        val firstXIndex = pairOfPreparation.second

        val result = dropSand(grid, firstXIndex)
        val resultAsString = gridAsStringWithouthBorders(result)

        val expected = listOf(
            "......+...",
            "..........",
            "..........",
            "..........",
            "....#...##",
            "....#...#.",
            "..###...#.",
            "........#.",
            "......o.#.",
            "#########."
        )

        printStringGrid(expected)
        printStringGrid(resultAsString)
        assertEquals(expected, resultAsString)
    }

    @Test
    fun drop2Sand() {
        val pairOfPreparation = prepareTest()
        var grid = pairOfPreparation.first
        val firstXIndex = pairOfPreparation.second

        repeat(2) {
            grid = dropSand(grid, firstXIndex)
        }
        val resultAsString = gridAsStringWithouthBorders(grid)

        val expected = listOf(
            "......+...",
            "..........",
            "..........",
            "..........",
            "....#...##",
            "....#...#.",
            "..###...#.",
            "........#.",
            ".....oo.#.",
            "#########."
        )

        printStringGrid(expected)
        printStringGrid(resultAsString)
        assertEquals(expected, resultAsString)
    }

    @Test
    fun drop5Sand() {
        val pairOfPreparation = prepareTest()
        var grid = pairOfPreparation.first
        val firstXIndex = pairOfPreparation.second

        repeat(5) {
            grid = dropSand(grid, firstXIndex)
        }
        val resultAsString = gridAsStringWithouthBorders(grid)

        val expected = listOf(
            "......+...",
            "..........",
            "..........",
            "..........",
            "....#...##",
            "....#...#.",
            "..###...#.",
            "......o.#.",
            "....oooo#.",
            "#########."
        )

        printStringGrid(expected)
        printStringGrid(resultAsString)
        assertEquals(expected, resultAsString)
    }

    @Test
    fun drop22Sand() {
        val pairOfPreparation = prepareTest()
        var grid = pairOfPreparation.first
        val firstXIndex = pairOfPreparation.second

        repeat(22) {
            grid = dropSand(grid, firstXIndex)
        }
        val resultAsString = gridAsStringWithouthBorders(grid)

        val expected = listOf(
            "......+...",
            "..........",
            "......o...",
            ".....ooo..",
            "....#ooo##",
            "....#ooo#.",
            "..###ooo#.",
            "....oooo#.",
            "...ooooo#.",
            "#########."
        )

        printStringGrid(expected)
        printStringGrid(resultAsString)
        assertEquals(expected, resultAsString)
    }

    @Test
    fun drop24Sand() {
        val pairOfPreparation = prepareTest()
        var grid = pairOfPreparation.first
        val firstXIndex = pairOfPreparation.second

        repeat(24) {
            grid = dropSand(grid, firstXIndex)
        }
        val resultAsString = gridAsStringWithouthBorders(grid)

        val expected = listOf(
            "......+...",
            "..........",
            "......o...",
            ".....ooo..",
            "....#ooo##",
            "...o#ooo#.",
            "..###ooo#.",
            "....oooo#.",
            ".o.ooooo#.",
            "#########."
        )

        printStringGrid(expected)
        printStringGrid(resultAsString)
        assertEquals(expected, resultAsString)
    }

    private fun printStringGrid(stringGrid: List<String>) {
        for (i in stringGrid.indices) {
            for (j in stringGrid[0].indices) {
                print(stringGrid[i][j])
            }
            println()
        }
        println()
    }

    private fun prepareTest(): Pair<Array<CharArray>, Int> {
        val input = listOf(
            "498,4 -> 498,6 -> 496,6",
            "503,4 -> 502,4 -> 502,9 -> 494,9"
        )

        val paths = extractPathsFromInput(input)
        val range = getXYValueRange(paths)
        val firstXIndex = range.first.first
        val rows = range.second.second + 2
        val columns = range.second.second - range.second.first + 3
        var grid: Array<CharArray> = Array(size = rows) { CharArray(size = columns, init = { '.' }) }
        grid = addSource(grid, firstXIndex)
        grid = addRocks(paths, grid, firstXIndex)
        return Pair(grid, firstXIndex)
    }

    private fun gridAsStringWithouthBorders(grid: Array<CharArray>): List<String> {
        val stringGrid = mutableListOf<String>()
        val line = mutableListOf<Char>()
        for (i in 0 until grid.size - 1) {
            for (j in 1 until grid[0].size - 1) {
                line.add(grid[i][j])
            }
            stringGrid.add(line.joinToString(""))
            line.clear()
        }
        return stringGrid
    }

    @Test
    fun part2Example() {
        val input = listOf(
            "498,4 -> 498,6 -> 496,6",
            "503,4 -> 502,4 -> 502,9 -> 494,9"
        )

        val result = part2(input)

        assertEquals(93, result)
    }
}