package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Part2Test {

    @Test
    fun part2() {
        val input = listOf(
            "addx 15",
            "addx -11",
            "addx 6",
            "addx -3",
            "addx 5",
            "addx -1",
            "addx -8",
            "addx 13",
            "addx 4",
            "noop",
            "addx -1",
            "addx 5",
            "addx -1",
            "addx 5",
            "addx -1",
            "addx 5",
            "addx -1",
            "addx 5",
            "addx -1",
            "addx -35",
            "addx 1",
            "addx 24",
            "addx -19",
            "addx 1",
            "addx 16",
            "addx -11",
            "noop",
            "noop",
            "addx 21",
            "addx -15",
            "noop",
            "noop",
            "addx -3",
            "addx 9",
            "addx 1",
            "addx -3",
            "addx 8",
            "addx 1",
            "addx 5",
            "noop",
            "noop",
            "noop",
            "noop",
            "noop",
            "addx -36",
            "noop",
            "addx 1",
            "addx 7",
            "noop",
            "noop",
            "noop",
            "addx 2",
            "addx 6",
            "noop",
            "noop",
            "noop",
            "noop",
            "noop",
            "addx 1",
            "noop",
            "noop",
            "addx 7",
            "addx 1",
            "noop",
            "addx -13",
            "addx 13",
            "addx 7",
            "noop",
            "addx 1",
            "addx -33",
            "noop",
            "noop",
            "noop",
            "addx 2",
            "noop",
            "noop",
            "noop",
            "addx 8",
            "noop",
            "addx -1",
            "addx 2",
            "addx 1",
            "noop",
            "addx 17",
            "addx -9",
            "addx 1",
            "addx 1",
            "addx -3",
            "addx 11",
            "noop",
            "noop",
            "addx 1",
            "noop",
            "addx 1",
            "noop",
            "noop",
            "addx -13",
            "addx -19",
            "addx 1",
            "addx 3",
            "addx 26",
            "addx -30",
            "addx 12",
            "addx -1",
            "addx 3",
            "addx 1",
            "noop",
            "noop",
            "noop",
            "addx -9",
            "addx 18",
            "addx 1",
            "addx 2",
            "noop",
            "noop",
            "addx 9",
            "noop",
            "noop",
            "noop",
            "addx -1",
            "addx 2",
            "addx -37",
            "addx 1",
            "addx 3",
            "noop",
            "addx 15",
            "addx -21",
            "addx 22",
            "addx -6",
            "addx 1",
            "noop",
            "addx 2",
            "addx 1",
            "noop",
            "addx -10",
            "noop",
            "noop",
            "addx 20",
            "addx 1",
            "addx 2",
            "addx 2",
            "addx -6",
            "addx -11",
            "noop",
            "noop",
            "noop"
        )

        val crtRow0 = "##..##..##..##..##..##..##..##..##..##..".toCharArray()
        val crtRow1 = "###...###...###...###...###...###...###.".toCharArray()
        val crtRow2 = "####....####....####....####....####....".toCharArray()
        val crtRow3 = "#####.....#####.....#####.....#####.....".toCharArray()
        val crtRow4 = "######......######......######......####".toCharArray()
        val crtRow5 = "#######.......#######.......#######.....".toCharArray()
        val expected = arrayOf(crtRow0, crtRow1, crtRow2, crtRow3, crtRow4, crtRow5)

        val result = part2(input)

        println("EXPECTED")
        printCrt(expected)
        println()
        println("RESULT")
        printCrt(result)

        Assertions.assertArrayEquals(crtRow0, result[0])
        Assertions.assertArrayEquals(crtRow1, result[1])
        Assertions.assertArrayEquals(crtRow2, result[2])
        Assertions.assertArrayEquals(crtRow3, result[3])
        Assertions.assertArrayEquals(crtRow4, result[4])
        Assertions.assertArrayEquals(crtRow5, result[5])
    }
}