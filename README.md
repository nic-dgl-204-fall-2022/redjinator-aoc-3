# redjinator-aoc-3
# Redjinator Advent of Code (3 of 3)

[Day 9 - Advent of Code 2017](https://adventofcode.com/2017/day/9)

## Overview of problem
We have a stream of characters, and we need to count the number of groups and the number of garbage characters.
There is a special set of rules that determine how the characters are interpreted.
* chars between `{` and `}` are a group
* chars between `<` and `>` are garbage
* `!` is a special character that cancels the next character
* `{` and `}` are special characters that can be escaped with `!`
* `<` and `>` are special characters that can be escaped with `!`


Sample of `input.txt`
```
{{{{{<!>,<!o,!!!>{>,{<<a!>u!>eio!>ui>}},{},{{<!!e!>>},{{<uo'<!!!>>},{<!>'!>},<e!>,<"ea!!!>!>,<>}}}},{{{{<"a">,<!><<>}}}},{{{{{},{<{!!"!><ae<},!>!>!!!>}'!!>}},<!!a{!!!!!>!>,<!!!>,<{oiu<a!!,<>},{<>}}}}
```

### Obstacles
* Getting the data from the text file.
* parse the data while keeping track of where we are in the stream. (i.e. in a group, in garbage, etc.)
* Keeping track of the number of groups and garbage characters.


### Complete Solution

#### part1() and part2() functions
This time we ended up using the same function for both solutions so I've included the full document below since the same function is used in both parts of the problem.

```kotlin
fun calculateStream(input: String): List<Int> {
    
    // keeping track of where we are in the stream.
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
```
### Obstacle #1
I altered the parameters to take in a String instead of a `List<String>` because using the `readInput()` from the `Utils.kt` file didn't give us an idea format to parse the data. Instead, I used the `readText()` function to get the data as a String which made it easier to parse.

### Obstacle #2
Keeping track of where we were in the stream wasn't too hard. We just needed to keep track of a few variables as we move character by character and update our state as follows (See code comments above, I had a hard time explaining it here so I broke it down line by line there so it's clear what I'm referring to).
```kotlin
    var insideGarbage   = false
    var skipNextChar    = false
    var nestLevel           = 0
```

### Obstacle #3
Keeping track of the number of groups and garbage characters was pretty easy too. We just needed to keep track of a few variables as we move character by character and increment our counts as we find ourselves under the right conditions
```kotlin
    var groupSum            = 0
    var insideGarbageCount  = 0
```

---


## Reflection

How long did it take me to find a complete solution?
I'd say it took me about 4 hours to get this working. I spent a lot of time trying to figure out how to parse the data. Looking at it now it looks so simple but I was having a hard time visualizing how I wanted to move through the data. But I approached it from the same way I read bar charts one by one keeping state as I update data each move forward and it worked out.I kinda brute forced this and kept hacking at it until it worked, I know I shouldn't have and I think I did only 1 commit, but I think it's because the structure of the file was so small I was able to keep everything in sight and make a small change then run again to test. Seemed like it was the quick and easy way to do it at the time but I know I should have been more disciplined and made more commits and likely spent more time than I should have using that method in the long run. I did most of this in the late night (it's 2:28am) and used [Kotlinlang](https://kotlinlang.org/) as my primary reference but admittingly I referenced a python solution to the problem near the end to help me with a problem but I can't seem to find it now. It was on GitHub however. I'll update here if I find it. 

End






Welcome to the Advent of Code[^aoc] Kotlin project created by [redjinator][github] using the [Advent of Code Kotlin Template][template] delivered by JetBrains.

In this repository, redjinator is about to provide solutions for the puzzles using [Kotlin][kotlin] language.

If you're stuck with Kotlin-specific questions or anything related to this template, check out the following resources:

- [Kotlin docs][docs]
- [Kotlin Slack][slack]
- Template [issue tracker][issues]


[^aoc]:
[Advent of Code][aoc] – An annual event of Christmas-oriented programming challenges started December 2015.
Every year since then, beginning on the first day of December, a programming puzzle is published every day for twenty-five days.
You can solve the puzzle and provide an answer using the language of your choice.

[aoc]: https://adventofcode.com
[docs]: https://kotlinlang.org/docs/home.html
[github]: https://github.com/redjinator
[issues]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template/issues
[kotlin]: https://kotlinlang.org
[slack]: https://surveys.jetbrains.com/s3/kotlin-slack-sign-up
[template]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template





OLD
---
Welcome to the Advent of Code[^aoc] Kotlin project created by [redjinator][github] using the [Advent of Code Kotlin Template][template] delivered by JetBrains.

In this repository, redjinator is about to provide solutions for the puzzles using [Kotlin][kotlin] language.

If you're stuck with Kotlin-specific questions or anything related to this template, check out the following resources:

- [Kotlin docs][docs]
- [Kotlin Slack][slack]
- Template [issue tracker][issues]


[^aoc]:
    [Advent of Code][aoc] – An annual event of Christmas-oriented programming challenges started December 2015.
    Every year since then, beginning on the first day of December, a programming puzzle is published every day for twenty-five days.
    You can solve the puzzle and provide an answer using the language of your choice.

[aoc]: https://adventofcode.com
[docs]: https://kotlinlang.org/docs/home.html
[github]: https://github.com/redjinator
[issues]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template/issues
[kotlin]: https://kotlinlang.org
[slack]: https://surveys.jetbrains.com/s3/kotlin-slack-sign-up
[template]: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template
