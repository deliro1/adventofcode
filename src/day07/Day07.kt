package day07
import readInput
import kotlin.math.abs

class Day07 {
    companion object {

        private const val day = "day07"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 37)
            check(part2(testInput) == 168)

            val input = readInput(filePath)
            println("Part 1 result for $day is: ${part1(input)}")
            println("Part 2 result for $day is: ${part2(input)}")
        }

        private fun part1(input: List<String>): Int {
            val inputInt = input[0].split(",").map { it.toInt() }
            var maxval= inputInt.maxOf{it}
            var neededFuel= IntArray(maxval+1)

            neededFuel.forEachIndexed { index, k ->
                var summedFuel=0
                inputInt.forEach(){
                    summedFuel+= abs(it - index)
                }
                neededFuel[index]=summedFuel
            }
            return neededFuel.toList().minOf { it }
        }

        private fun part2(input: List<String>): Int {
            val inputInt = input[0].split(",").map { it.toInt() }
            var maxval= inputInt.maxOf{it}
            var neededFuel= IntArray(maxval+1)

            neededFuel.forEachIndexed { index, k ->
                var summedFuel=0
                inputInt.forEach(){
                    val n=abs(it - index)
                    summedFuel+= (n*(n+1))/2
                }
                neededFuel[index]=summedFuel
            }
            return neededFuel.toList().minOf { it }
        }
    }
}