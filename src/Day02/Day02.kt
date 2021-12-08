package day02
import readInput

class Day02 {
    companion object {

        private const val day = "day02"
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
            val directions = input.map { it.split(" ") }
            var horizontalPosition = 0
            var depth = 0
            directions.forEach {
                when (it[0]) {
                    "forward" -> horizontalPosition += it[1].toInt()
                    "down" -> depth += it[1].toInt()
                    "up" -> depth -= it[1].toInt()
                    else -> println("not a valid movement direction")
                }
            }
            return depth * horizontalPosition
        }

        fun part2(input: List<String>): Int {
            val directions = input.map { it.split(" ") }
            var horizontalPosition = 0
            var depth = 0
            var aim = 0
            directions.forEach {
                when (it[0]) {
                    "forward" -> {
                        horizontalPosition += it[1].toInt(); depth += aim * it[1].toInt()
                    }
                    "down" -> aim += it[1].toInt()
                    "up" -> aim -= it[1].toInt()
                    else -> println("not a valid movement direction")
                }
            }
            return depth * horizontalPosition
        }
    }
}