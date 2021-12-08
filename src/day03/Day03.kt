package day03
import readInput

class Day03 {
    companion object {

        private const val day = "day03"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            val testInput = readInput("${filePath}_test")
            //check(part1(testInput) == 7)
            //check(part2(testInput) == 5)

            val input = readInput(filePath)
            println("Part 1 result for $day is: ${part1(input)}")
            println("Part 2 result for $day is: ${part2(input)}")
        }

        fun part1(input: List<String>): Int {
            val listSize = input.size
            val incidence = mutableListOf<Int>(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            //Find out how to init a zero list not like this trash
            val gamma = mutableListOf<Int>(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            val epsilon = mutableListOf<Int>(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
            input.forEach() {
                it.forEachIndexed { index, c ->
                    incidence[index] += c.toString().toInt()
                }
            }
            //println("incidence: $incidence")
            incidence.forEachIndexed { index, i ->
                if (i > listSize / 2) {
                    gamma[index] = 1
                    epsilon[index] = 0
                } else if (i == listSize / 2) {
                    println("Error: same amount of 0 and 1")
                }
            }
            val gammaInt = gamma.joinToString("").toInt(2)
            val epsilonInt = epsilon.joinToString("").toInt(2)
            println("Result: $epsilonInt * $gammaInt equals ${epsilonInt * gammaInt}")
            return epsilonInt * gammaInt
        }

        fun part2(input: List<String>): Int {





            return 0
        }
    }
}