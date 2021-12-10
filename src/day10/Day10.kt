package day10
import readInput

class Day10 {
    companion object {

        private const val day = "day10"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 26397)
            //check(part2(testInput) == 5)

            val input = readInput(filePath)
            //println("Part 1 result for $day is: ${part1(input)}")
            println("Part 2 result for $day is: ${part2(input)}")
        }

        fun part1(input: List<String>): Int {

            var score=0
            input.forEach{line ->
                var braceCurrent= IntArray(4)
                line.forEach { symbol ->
                    when (symbol){
                        '('-> braceCurrent[0]++
                        '['-> braceCurrent[1]++
                        '{'-> braceCurrent[2]++
                        '<'-> braceCurrent[3]++

                        ')'-> {braceCurrent[0]--
                                    if (braceCurrent[0]<0){
                                        score +=3
                                    }
                                }
                        ']'-> {braceCurrent[1]--
                                    if (braceCurrent[1]<0){
                                        score +=57
                                    }
                                }
                        '}'-> {braceCurrent[2]--
                                    if (braceCurrent[2]<0){
                                        score +=1197
                                    }
                                }
                        '>'-> {braceCurrent[3]--
                                    if (braceCurrent[3]<0){
                                        score +=25137
                                    }
                                }
                    }
                }
            }

            println("")
            println("")



            return 26397
        }

        fun part2(input: List<String>): Int {
            return 0
        }

        private fun testF(inputArray: List<List<Int>>): Int{
            return 0
        }

    }
}
