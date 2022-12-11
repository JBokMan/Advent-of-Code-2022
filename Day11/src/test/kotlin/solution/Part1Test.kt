package solution

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

@Suppress("SpellCheckingInspection")
internal class Part1Test {

    @Test
    fun monkeyGenerationOneMonkey() {
        val input = listOf(
            "Monkey 0:",
            "  Starting items: 79, 98",
            "  Operation: new = old * 19",
            "  Test: divisible by 23",
            "    If true: throw to monkey 2",
            "    If false: throw to monkey 3",
        )

        val result = generateMonkeysFromInput(input)

        val monkeys = arrayOf(
            Monkey(
                index = 0,
                items = mutableListOf(BigInteger.valueOf(79), BigInteger.valueOf(98)),
                operation = { old -> old.multiply(BigInteger.valueOf(19)) },
                test = { worryLevel -> worryLevel.remainder(BigInteger.valueOf(23)) == BigInteger.ZERO },
                testTrueNextMonkey = 2,
                testFalseNextMonkey = 3
            )
        )

        assertEquals(monkeys[0], result[0])
    }

    @Test
    fun monkeyInspectItem() {
        val monkey = Monkey(
            index = 0,
            items = mutableListOf(BigInteger.valueOf(79), BigInteger.valueOf(98)),
            operation = { old -> old.multiply(BigInteger.valueOf(19)) },
            test = { worryLevel -> worryLevel.mod(BigInteger.valueOf(23)) == BigInteger.ZERO },
            testTrueNextMonkey = 2,
            testFalseNextMonkey = 3
        )
        monkey.setSuperModulus(BigInteger.valueOf(23L))

        val result = monkey.inspectNextItem()

        assertEquals(BigInteger.valueOf(63), result.first)
        assertEquals(3, result.second)
    }


    @Test
    fun monkeyGenerationTwoMonkeys() {
        val input = listOf(
            "Monkey 0:",
            "  Starting items: 79, 98",
            "  Operation: new = old + 19",
            "  Test: divisible by 23",
            "    If true: throw to monkey 2",
            "    If false: throw to monkey 3",
            "",
            "Monkey 1:",
            "  Starting items: 54, 65, 75, 74",
            "  Operation: new = old + old",
            "  Test: divisible by 19",
            "    If true: throw to monkey 2",
            "    If false: throw to monkey 0",
        )

        val result = generateMonkeysFromInput(input)

        val monkeys = arrayOf(
            Monkey(
                index = 0,
                items = mutableListOf(BigInteger.valueOf(79), BigInteger.valueOf(98)),
                operation = { old -> old.add(BigInteger.valueOf(19)) },
                test = { worryLevel -> worryLevel.mod(BigInteger.valueOf(23)) == BigInteger.ZERO },
                testTrueNextMonkey = 2,
                testFalseNextMonkey = 3
            ),
            Monkey(
                index = 1,
                items = mutableListOf(
                    BigInteger.valueOf(54),
                    BigInteger.valueOf(65),
                    BigInteger.valueOf(75),
                    BigInteger.valueOf(74)
                ),
                operation = { old -> old + old },
                test = { worryLevel -> worryLevel.mod(BigInteger.valueOf(19)) == BigInteger.ZERO },
                testTrueNextMonkey = 2,
                testFalseNextMonkey = 0
            )
        )

        assertArrayEquals(monkeys, result)
    }

    @Test
    fun part1ExampleInput() {
        val input = listOf(
            "Monkey 0:",
            "  Starting items: 79, 98",
            "  Operation: new = old * 19",
            "  Test: divisible by 23",
            "    If true: throw to monkey 2",
            "    If false: throw to monkey 3",
            "",
            "Monkey 1:",
            "  Starting items: 54, 65, 75, 74",
            "  Operation: new = old + 6",
            "  Test: divisible by 19",
            "    If true: throw to monkey 2",
            "    If false: throw to monkey 0",
            "",
            "Monkey 2:",
            "  Starting items: 79, 60, 97",
            "  Operation: new = old * old",
            "  Test: divisible by 13",
            "    If true: throw to monkey 1",
            "    If false: throw to monkey 3",
            "",
            "Monkey 3:",
            "  Starting items: 74",
            "  Operation: new = old + 3",
            "  Test: divisible by 17",
            "    If true: throw to monkey 0",
            "    If false: throw to monkey 1"
        )

        val result = part1(input)

        assertEquals(10605, result)
    }
}