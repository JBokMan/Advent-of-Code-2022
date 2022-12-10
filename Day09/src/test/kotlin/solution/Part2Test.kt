package solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Part2Test {

    @Test
    fun part2() {
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

        val result = part2(input)

        assertEquals(1, result)

    }

    @Test
    fun part2Example2() {
        val input = listOf(
            "R 5",
            "U 8",
            "L 8",
            "D 3",
            "R 17",
            "D 10",
            "L 25",
            "U 20"
        )

        val result = part2(input)

        assertEquals(36, result)
    }
}