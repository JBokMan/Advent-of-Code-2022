package day02

import java.io.File

enum class RockPaperScissors {
    ROCK,
    PAPER,
    SCISSORS;
}

enum class RoundOutcome {
    LOSE,
    DRAW,
    WIN;
}

fun mapToRPS(code: String): RockPaperScissors? {
    return when (code) {
        "A", "X" -> {
            RockPaperScissors.ROCK
        }

        "B", "Y" -> {
            RockPaperScissors.PAPER
        }

        "C", "Z" -> {
            RockPaperScissors.SCISSORS
        }

        else -> null
    }
}

fun mapToRoundOutcome(code: String): RoundOutcome? {
    return when (code) {
        "X" -> {
            RoundOutcome.LOSE
        }

        "Y" -> {
            RoundOutcome.DRAW
        }

        "Z" -> {
            RoundOutcome.WIN
        }

        else -> null
    }
}

fun getPointsOfSingleRound(opponent: RockPaperScissors, strategy: RockPaperScissors): Int {
    var roundScore = when (strategy) {
        RockPaperScissors.ROCK -> 1
        RockPaperScissors.PAPER -> 2
        RockPaperScissors.SCISSORS -> 3
    }

    when (opponent) {
        RockPaperScissors.ROCK -> when (strategy) {
            RockPaperScissors.ROCK -> roundScore += 3
            RockPaperScissors.PAPER -> roundScore += 6
            RockPaperScissors.SCISSORS -> roundScore += 0
        }

        RockPaperScissors.PAPER -> when (strategy) {
            RockPaperScissors.ROCK -> roundScore += 0
            RockPaperScissors.PAPER -> roundScore += 3
            RockPaperScissors.SCISSORS -> roundScore += 6
        }

        RockPaperScissors.SCISSORS -> when (strategy) {
            RockPaperScissors.ROCK -> roundScore += 6
            RockPaperScissors.PAPER -> roundScore += 0
            RockPaperScissors.SCISSORS -> roundScore += 3
        }
    }

    return roundScore
}

fun getResponse(opponent: RockPaperScissors, strategy: RoundOutcome): RockPaperScissors {
    return when (opponent) {
        RockPaperScissors.ROCK -> when (strategy) {
            RoundOutcome.LOSE -> RockPaperScissors.SCISSORS
            RoundOutcome.DRAW -> RockPaperScissors.ROCK
            RoundOutcome.WIN -> RockPaperScissors.PAPER
        }

        RockPaperScissors.PAPER -> when (strategy) {
            RoundOutcome.LOSE -> RockPaperScissors.ROCK
            RoundOutcome.DRAW -> RockPaperScissors.PAPER
            RoundOutcome.WIN -> RockPaperScissors.SCISSORS
        }

        RockPaperScissors.SCISSORS -> when (strategy) {
            RoundOutcome.LOSE -> RockPaperScissors.PAPER
            RoundOutcome.DRAW -> RockPaperScissors.SCISSORS
            RoundOutcome.WIN -> RockPaperScissors.ROCK
        }
    }
}

fun main() {
    // read input
    val input = mutableListOf<String>()
    File("src/main/kotlin/day02/day02-input").forEachLine { input.add(it) }

    var myScore = 0

    // parse every line
    input.forEach { round ->
        val splittedRound = round.split(" ")
        val oppenentChoice = mapToRPS(splittedRound[0])
        val strategyChoice = mapToRPS(splittedRound[1])
        if (oppenentChoice != null && strategyChoice != null) {
            myScore += getPointsOfSingleRound(oppenentChoice, strategyChoice)
        }
    }

    // print result part 1
    println(myScore)

    // reset score
    myScore = 0

    // parse every line
    input.forEach { round ->
        val splittedRound = round.split(" ")
        val oppenentChoice = mapToRPS(splittedRound[0])
        val strategyRoundOutcome = mapToRoundOutcome(splittedRound[1])
        if (oppenentChoice != null && strategyRoundOutcome != null) {
            val strategyChoice = getResponse(oppenentChoice, strategyRoundOutcome)
            myScore += getPointsOfSingleRound(oppenentChoice, strategyChoice)
        }
    }

    // print result part 2
    println(myScore)
}

