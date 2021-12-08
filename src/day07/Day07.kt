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
            var maxval = findMaxInList(inputInt)
            //var maxval = inputInt.maxOrNull()?:0
            var neededFuel= IntArray(maxval+1)

            neededFuel.forEachIndexed { index, k ->
                var summedFuel=0
                inputInt.forEach(){
                    summedFuel+= abs(it - index)
                }
                neededFuel[index]=summedFuel
            }
            return findMinInList(neededFuel.toList())
        }

        private fun part2(input: List<String>): Int {
            val inputInt = input[0].split(",").map { it.toInt() }
            var maxval = findMaxInList(inputInt)
            //var maxval = inputInt.maxOrNull()?:0
            var neededFuel= IntArray(maxval+1)

            neededFuel.forEachIndexed { index, k ->
                var summedFuel=0
                inputInt.forEach(){
                    val n=abs(it - index)
                    summedFuel+= (n*(n+1))/2
                }
                neededFuel[index]=summedFuel
            }
            return findMinInList(neededFuel.toList())

        }

        private fun findMaxInList(inputArray: List<Int>): Int{
            var maxvalue=0
                for(element in inputArray){
                    if(element > maxvalue){
                        maxvalue=element}
                }
            return maxvalue
        }
        private fun findMinInList(inputArray: List<Int>): Int{
            var minvalue=1000000000
            for(element in inputArray){
                if(element < minvalue){
                    minvalue=element}
            }
            return minvalue
        }

    }
}