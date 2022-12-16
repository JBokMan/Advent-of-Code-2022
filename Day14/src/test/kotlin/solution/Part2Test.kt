package solution

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Part2Test {

    @Test
    fun part2Example() {
        val input = listOf(
            "[1,1,3,1,1]",
            "[1,1,5,1,1]",
            "",
            "[[1],[2,3,4]]",
            "[[1],4]",
            "",
            "[9]",
            "[[8,7,6]]",
            "",
            "[[4,4],4,4]",
            "[[4,4],4,4,4]",
            "",
            "[7,7,7,7]",
            "[7,7,7]",
            "",
            "[]",
            "[3]",
            "",
            "[[[]]]",
            "[[]]",
            "",
            "[1,[2,[3,[4,[5,6,7]]]],8,9]",
            "[1,[2,[3,[4,[5,6,0]]]],8,9]"
        )
        val extraPackets = listOf(
            "[[2]]",
            "[[6]]"
        )
        val result = part2(input)

        Assertions.assertEquals(140, result)
    }
}