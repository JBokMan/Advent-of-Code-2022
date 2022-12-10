package solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import solution.Direction.*

internal class Part1Test {

    @Test
    fun part1Example1() {
        val input = listOf(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2"
        )

        val result = part1(input)

        assertEquals(13, result)
    }

    @Test
    fun generateMoves() {
        val input = listOf(
            "R 1",
            "D 2",
            "U 3",
            "L 1"
        )

        val result = generateMoveSequence(input)

        assertEquals(listOf(RIGHT, DOWN, DOWN, UP, UP, UP, LEFT), result)
    }

    @Test
    fun calculateGrid() {
        val input = listOf(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2"
        )
        val moves = generateMoveSequence(input)
        val result = calculateGrid(moves)

        assertEquals(Pair(Pair(5, 6), Pair(4,0)), result)
    }

    @Test
    fun calculateGridWithGoingFarLeftAndUp() {
        val input = listOf(
            "R 1",
            "L 10",
            "D 1",
            "U 10"
        )
        val moves = generateMoveSequence(input)
        val result = calculateGrid(moves)

        assertEquals(Pair(Pair(11, 11), Pair(9,9)), result)
    }
}