package solution

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@Suppress("SpellCheckingInspection")
internal class Part1Test {

    @Test
    fun part1Example() {
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

        val result = part1(input)

        assertEquals(13, result)
    }

    @Test
    fun packetExample1() {
        val input = listOf(
            "[1,1,3,1,1]",
            "[1,1,5,1,1]"
        )

        val result = isLeftAndRightInRightOrder(input[0], input[1])

        assertTrue(result!!)
    }

    @Test
    fun packetExample2() {
        val input = listOf(
            "[[1],[2,3,4]]",
            "[[1],4]"
        )

        val result = isLeftAndRightInRightOrder(input[0], input[1])

        assertTrue(result!!)
    }

    @Test
    fun packetExample3() {
        val input = listOf(
            "[9]",
            "[[8,7,6]]"
        )

        val result = isLeftAndRightInRightOrder(input[0], input[1])

        assertFalse(result!!)
    }

    @Test
    fun packetExample4() {
        val input = listOf(
            "[[4,4],4,4]",
            "[[4,4],4,4,4]"
        )

        val result = isLeftAndRightInRightOrder(input[0], input[1])

        assertTrue(result!!)
    }

    @Test
    fun packetExample5() {
        val input = listOf(
            "[7,7,7,7]",
            "[7,7,7]"
        )

        val result = isLeftAndRightInRightOrder(input[0], input[1])

        assertFalse(result!!)
    }

    @Test
    fun packetExample6() {
        val input = listOf(
            "[]",
            "[3]"
        )

        val result = isLeftAndRightInRightOrder(input[0], input[1])

        assertTrue(result!!)
    }

    @Test
    fun packetExample7() {
        val input = listOf(
            "[[[]]]",
            "[[]]"
        )

        val result = isLeftAndRightInRightOrder(input[0], input[1])

        assertFalse(result!!)
    }

    @Test
    fun packetExample8() {
        val input = listOf(
            "[1,[2,[3,[4,[5,6,7]]]],8,9]",
            "[1,[2,[3,[4,[5,6,0]]]],8,9]"
        )

        val result = isLeftAndRightInRightOrder(input[0], input[1])

        assertFalse(result!!)
    }

    @Test
    fun nextElement1() {
        val input = "[[1,1],2,3]"

        val result = getNextElement(input)

        assertEquals("[1,1]", result)
    }

    @Test
    fun nextElement2() {
        val input = "[1]"

        val result = getNextElement(input)

        assertEquals("1", result)
    }

    @Test
    fun nextElement3() {
        val input = "[[1]]"

        val result = getNextElement(input)

        assertEquals("[1]", result)
    }

    @Test
    fun nextElement4() {
        val input = "[[[1]]]"

        val result = getNextElement(input)

        assertEquals("[[1]]", result)
    }

    @Test
    fun nextElement5() {
        val input = "[1,2]"

        val result = getNextElement(input)

        assertEquals("1", result)
    }

    @Test
    fun nextElement6() {
        val input = "[[1],2]"

        val result = getNextElement(input)

        assertEquals("[1]", result)
    }

    @Test
    fun nextElement7() {
        val input = "[1,[2]]"

        val result = getNextElement(input)

        assertEquals("1", result)
    }

    @Test
    fun nextElement8() {
        val input = "[10,[[10,7,3,0,10],10],[[8,7,7,2,8]],0]"

        val result = getNextElement(input)

        assertEquals("10", result)
    }
}