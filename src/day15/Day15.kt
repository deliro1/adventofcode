package day15
import readInput

class Day15 {
    companion object {

        private const val day = "day15"
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

            var graphIter = Array(10) { Array(10) { 0 } }
            var graph = Array(10) { Array(10) { 0 } }
            graphIter.forEachIndexed { y, reihe ->
                reihe.forEachIndexed { x, knoten ->
                    graphIter[y][x] = input[y][x].digitToInt()
                    graph[y][x] = input[y][x].digitToInt()
                }
            }

            var abstand = Array(10) { Array(10) { 99999 } }
            var vorgaenger = Array(10) { Array(10) { 0 } }
            var wegLänge = 0

            var k = 0
            while (k == 0) {
                var kleinstes = 0
                graphIter.forEachIndexed { y, reihe ->
                    reihe.forEachIndexed { x, knoten ->
                    }
                }



                k = 4
            }








            return 0
        }

        fun part2(input: List<String>): Int {
            return 0
        }


    }
}
