package dayXX
import readInput

class DayXX {
    companion object {

        private const val day = "dayXX"
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
            return 0
        }

        fun part2(input: List<String>): Int {
            return 0
        }


    }
}