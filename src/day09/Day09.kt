package day09
import readInput

class Day09 {
    companion object {

        private const val day = "day09"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 15)
            //check(part2(testInput) == 5)

            val input = readInput(filePath)
            println("Part 1 result for $day is: ${part1(input)}")
            println("Part 2 result for $day is: ${part2(input)}")
        }

        private fun part1(input: List<String>): Int {

            var dangerLevel=0
            var inputInt= input.map { it.map { f-> f.digitToInt() } }


            inputInt.forEachIndexed { y, line ->
                line.forEachIndexed { x, value ->
                    if ((x==0 ||value < inputInt[y][x-1])   &&  (x==line.size-1     || value < inputInt[y][x+1]) &&
                        (y==0 ||value < inputInt[y-1][x])   &&  (y==inputInt.size-1 || value < inputInt[y+1][x])){
                        dangerLevel += value+1
                    }
                }
            }
            return dangerLevel
        }

        private fun part2(input: List<String>): Int {
            return 0
        }

        private fun testF(inputArray: List<List<Int>>): Int{
            return 0
        }

    }
}