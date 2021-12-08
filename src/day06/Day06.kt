package day06
import readInput
class Day06 {
    companion object {

        private const val day = "day06"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 5934)
            check(part2(testInput) == 26984457539)

            val input = readInput(filePath)
            println("Part 1 result for $day is: ${part1(input)}")
            println("Part 2 result for $day is: ${part2(input)}")
        }

        private fun part1(input: List<String>): Int {
            val inputInt=input[0].split(",").map { it.toInt() }.toMutableList()

            for (k in 1..80) {
                var amountNew=0
                inputInt.forEachIndexed { index, i ->
                    if (i == 0) {
                        inputInt[index] = 6
                        amountNew++
                    } else {
                        inputInt[index]--
                    }
                }
                repeat(amountNew) { inputInt.add(8) }
            }
            return inputInt.size
        }

        private fun part2(input: List<String>): Long {
            val inputInt = input[0].split(",").map { it.toInt() }
            val fishAges = LongArray(9)
            inputInt.forEach { fishAges[it]++ }

            for (k in 0 until 256) {
                val amountNew = fishAges[0]
                for (curIndex in 0..7) {
                    fishAges[curIndex] = fishAges[curIndex + 1]
                }
                fishAges[8]= amountNew
                fishAges[6]+=amountNew
            }
            return fishAges.sum()
        }
        
    }
}