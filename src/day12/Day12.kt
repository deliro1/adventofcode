package day12
import readInput
class Day12 {
    companion object {

        private const val day = "day12"
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
            var connections=input.map { it.split("-") }


            return 0
        }

        fun part2(input: List<String>): Int {
            return 0
        }


    }
}
