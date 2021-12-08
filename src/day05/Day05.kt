package day05
import readInput
class Day05 {
    companion object {

        private const val day = "day05"
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
            val inputSegmented = input.map { it.split(","," -> ") }.map{ it.map{str -> str.toInt()}}
            val maxvalue=findMaxIn2DList(inputSegmented)+1
            var landscape = Array(maxvalue) { IntArray(maxvalue) { 0 } }
            var xStart: Int; var xEnd: Int; var yStart: Int; var yEnd: Int

            inputSegmented.forEach{
                xStart= it[0]; xEnd= it[2]; yStart= it[1]; yEnd= it[3]
                landscape=horiAndVertiLines(xStart,xEnd, yStart,yEnd,landscape)
            }
            return landscape.sumOf { it.count{element -> element >= 2} }
        }

        fun part2(input: List<String>): Int {
            val inputSegmented = input.map { it.split(","," -> ") }.map{ it.map{str -> str.toInt()}}
            val maxvalue=findMaxIn2DList(inputSegmented)+1
            var landscape = Array(maxvalue) { IntArray(maxvalue) { 0 } }
            var xStart: Int; var xEnd: Int; var yStart: Int; var yEnd: Int

            inputSegmented.forEach{
                xStart= it[0]; xEnd= it[2]; yStart= it[1]; yEnd= it[3]
                landscape=horiAndVertiLines(xStart,xEnd, yStart,yEnd,landscape)

                if(xStart!=xEnd && yStart!=yEnd){
                    val xPosis= xStart.toward(xEnd).toList()
                    val yPosis= yStart.toward(yEnd).toList()
                    for (k in 0..xPosis.lastIndex ){
                        landscape[yPosis[k]][xPosis[k]] +=1
                    }
                }
            }
            return landscape.sumOf { it.count{element -> element >= 2} }
        }


        private fun findMaxIn2DList(inputArray: List<List<Int>>): Int{
            var maxvalue=0
            for(row in inputArray){
                for(element in row){
                    if(element > maxvalue){
                        maxvalue=element}
                }
            }
            return maxvalue
        }

        private fun makeRange(a: Int, b: Int): IntRange{
            return if (a < b) (a..b) else (b..a)
        }
        private infix fun Int.toward(to: Int): IntProgression {
            val step = if (this > to) -1 else 1
            return IntProgression.fromClosedRange(this, to, step)
        }

        private fun horiAndVertiLines(xStart: Int,xEnd: Int, yStart: Int,yEnd: Int,landscape: Array<IntArray>): Array<IntArray> {
            var lineType = ""
            var line = (0..0)
            if (xStart == xEnd) {
                lineType = "y"
                line = makeRange(yStart, yEnd)
            } else if (yStart == yEnd) {
                lineType = "x"
                line = makeRange(xStart, xEnd)
            }
            line.forEach { element ->
                when (lineType) {
                    "x" -> landscape[yStart][element] += 1
                    "y" -> landscape[element][xStart] += 1
                }
            }
            return landscape
        }
    }
}
