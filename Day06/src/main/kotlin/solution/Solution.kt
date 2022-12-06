package solution

import java.io.File

fun main() {
    // read input
    val input = mutableListOf<String>()
    File("src/main/kotlin/solution/Input").forEachLine { input.add(it) }

    //println(input)
    val chars = input[0].asSequence()

    var index = 0
    val buffer = ArrayDeque<Char>()

    // process input
    run lit@{
        chars.forEach { char ->
            index += 1
            buffer.add(char)
            if (buffer.size == 4) {
                if (buffer.toSet().size == 4) {
                    return@lit
                }
                buffer.removeFirst()
            }
        }
    }

    //print result part 1
    println(index)

    index = 0
    buffer.clear()

    // process input
    run lit@{
        chars.forEach { char ->
            index += 1
            buffer.add(char)
            if (buffer.size == 14) {
                if (buffer.toSet().size == 14) {
                    return@lit
                }
                buffer.removeFirst()
            }
        }
    }

    // print result part 2
    println(index)
}
