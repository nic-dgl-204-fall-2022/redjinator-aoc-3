import java.io.File

fun calculateStream(input: String): List<Int> {
    var insideGarbage   = false
    var skipNextChar    = false

    var nestLevel           = 0
    var groupSum            = 0
    var insideGarbageCount  = 0

    for (char in input) {

        // if skipNextChar is true, we skip the next character and reset the boolean
        if (skipNextChar) {
            skipNextChar = false
            continue
        }
        if (insideGarbage) { // if insideGarbage is true
            when (char) {
                '!' -> skipNextChar = true      // if the next character is !, we skip it
                '>' -> insideGarbage = false    // if the next char is > we exit garbage state
                else -> insideGarbageCount++    // otherwise we count the garbage
            }
        } else {                                // if insideGarbage is false
            when (char) {                       // check the currect character
                '{' -> nestLevel++              // if it's a {, increase the nestLevel
                '}' -> {                        // if it's a }, increase the groupSum and decrease the nestLevel
                    groupSum += nestLevel
                    nestLevel--
                }
                '<' -> insideGarbage = true // if it's a <, set insideGarbage to true
            }
        }
    }

    return listOf(groupSum, insideGarbageCount)

}

fun part1(input: String) = calculateStream(input)[0]
fun part2(input: String) = calculateStream(input)[1]

fun main() {
    val input = File("src/Day9.txt").readText()

    println(part1(input))
    println(part2(input))
}
