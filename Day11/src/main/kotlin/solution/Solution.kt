@file:Suppress("SpellCheckingInspection")

package solution

import java.io.File
import java.math.BigInteger

fun main() {
    // read input
    val input = mutableListOf<String>()
    File("src/main/kotlin/solution/Input").forEachLine { input.add(it) }

    println("part 1 solution ${part1(input)}")
    println("part 2 solution ${part2(input)}")
}

fun part1(input: List<String>): Long {
    val monkeys: Array<Monkey> = generateMonkeysFromInput(input)
    for (round in 0 until 20) {
        for (monkey in monkeys) {
            while (monkey.hasNextItem()) {
                val itemMonkeyPair = monkey.inspectNextItem()
                val item = itemMonkeyPair.first
                val monkeyThrownTo = itemMonkeyPair.second
                monkeys[monkeyThrownTo].addItem(item)
            }
        }
    }

    return monkeys.map { it.inspectionCount }.sortedDescending().take(2).reduce { acc, it -> acc * it }
}

fun part2(input: List<String>): Long {
    val monkeys: Array<Monkey> = generateMonkeysFromInput(input)
    for (round in 0 until 10000) {
        for (monkey in monkeys) {
            while (monkey.hasNextItem()) {
                val itemMonkeyPair = monkey.inspectNextItem(moreWorried = true)
                val item = itemMonkeyPair.first
                val monkeyThrownTo = itemMonkeyPair.second
                monkeys[monkeyThrownTo].addItem(item)
            }
        }
    }

    return monkeys.map { it.inspectionCount }.sortedDescending().take(2).reduce { acc, it -> acc * it }
}


fun generateMonkeysFromInput(input: List<String>): Array<Monkey> {
    val monkeys = mutableListOf<Monkey>()
    val moduli = mutableListOf<BigInteger>()

    val monkeyIterator = input.iterator()
    while (monkeyIterator.hasNext()) {
        val indexLine = monkeyIterator.next()
        val index = indexLine.substringAfter(" ").substringBefore(":").toInt()

        val startingItemsLine = monkeyIterator.next()
        val startingItems =
            startingItemsLine.substringAfter(":").filter { !it.isWhitespace() }.split(',').map { it.toInt() }
                .toMutableList()

        val operationLine = monkeyIterator.next()
        val operand = operationLine.substringAfter("old ").substringBefore(" ")[0]
        val numberOrVariable = operationLine.substringAfter("$operand ")
        val operation = { old: BigInteger ->
            when (operand) {
                '+' -> if (numberOrVariable == "old") old.add(old) else old.add(numberOrVariable.toBigInteger())
                '*' -> if (numberOrVariable == "old") old.multiply(old) else old.multiply(
                    numberOrVariable.toBigInteger()
                )

                else -> BigInteger.ZERO
            }
        }

        val testLine = monkeyIterator.next()
        val modulus = testLine.substringAfter("by ").toBigInteger()
        moduli.add(modulus)
        val test = { worryLevel: BigInteger -> worryLevel.gcd(modulus) != BigInteger.ONE }

        val testTrueNextMonkeyLine = monkeyIterator.next()
        val testTrueNextMonkey = testTrueNextMonkeyLine.substringAfter("monkey ").toInt()

        val testFalseNextMonkeyLine = monkeyIterator.next()
        val testFalseNextMonkey = testFalseNextMonkeyLine.substringAfter("monkey ").toInt()

        monkeys.add(
            Monkey(
                index = index,
                items = startingItems.map { it.toBigInteger() }.toMutableList(),
                operation = operation,
                test = test,
                testTrueNextMonkey = testTrueNextMonkey,
                testFalseNextMonkey = testFalseNextMonkey
            )
        )
        if (monkeyIterator.hasNext()) {
            monkeyIterator.next()
        }
    }

    val superModulus = moduli.reduce { acc, it -> acc.multiply(it) }
    for (i in monkeys.indices) {
        monkeys[i].setSuperModulus(superModulus)
    }
    return monkeys.toTypedArray()
}

class Monkey(
    val index: Int,
    val items: MutableList<BigInteger>,
    private val operation: (BigInteger) -> BigInteger,
    private val test: (BigInteger) -> Boolean,
    private val testTrueNextMonkey: Int,
    private val testFalseNextMonkey: Int
) {
    var inspectionCount = 0L
    private var superModulus: BigInteger = BigInteger.ONE

    fun inspectNextItem(moreWorried: Boolean = false): Pair<BigInteger, Int> {
        inspectionCount += 1

        var itemWorryLevel = items.removeFirst()
        itemWorryLevel = itemWorryLevel.mod(superModulus)
        itemWorryLevel = operation(itemWorryLevel)
        if (!moreWorried) {
            itemWorryLevel = itemWorryLevel.divide(BigInteger.valueOf(3))
        }

        val testResult = test(itemWorryLevel)
        if (testResult) {
            return Pair(itemWorryLevel, testTrueNextMonkey)
        }
        return Pair(itemWorryLevel, testFalseNextMonkey)
    }

    fun hasNextItem(): Boolean {
        return items.size > 0
    }

    fun addItem(item: BigInteger) {
        items.add(item)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Monkey) {
            return false
        }
        if (other.index == index && other.items == items && other.operation(BigInteger.ZERO) == operation(BigInteger.ZERO) && other.operation(
                BigInteger.ONE
            ) == operation(
                BigInteger.ONE
            ) && other.operation(BigInteger.valueOf(-1)) == operation(BigInteger.valueOf(-1)) && other.operation(
                BigInteger.valueOf(1234)
            ) == operation(BigInteger.valueOf(1234)) && other.operation(BigInteger.valueOf(-1234)) == operation(
                BigInteger.valueOf(-1234)
            ) && other.test(BigInteger.ZERO) == test(BigInteger.ZERO) && other.test(BigInteger.ONE) == test(BigInteger.ONE) && other.test(
                BigInteger.valueOf(-1)
            ) == test(
                BigInteger.valueOf(-1)
            ) && other.test(BigInteger.valueOf(1234)) == test(
                BigInteger.valueOf(1234)
            ) && other.test(BigInteger.valueOf(-1234)) == test(BigInteger.valueOf(-1234)) && other.testTrueNextMonkey == testTrueNextMonkey && other.testFalseNextMonkey == testFalseNextMonkey
        ) {
            return true
        }
        return false
    }

    override fun toString(): String {
        return "index: $index, startingItems: $items, operation: $operation, test: $test, testTrueNextMonkey: $testTrueNextMonkey, testFalseNextMonkey: $testFalseNextMonkey"
    }

    fun setSuperModulus(superModulus: BigInteger) {
        this.superModulus = superModulus
    }
}


