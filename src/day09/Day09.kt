package day09
import readInput

class Day09 {
    companion object {

        private const val day = "day09"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 15)
            check(part2(testInput) == 1134)

            val input = readInput(filePath)
            println("Part 1 result for $day is: ${part1(input)}")
            println("Part 2 result for $day is: ${part2(input)}")
        }

        private fun part1(input: List<String>): Int {

            var dangerLevel=0
            val inputInt= input.map { it.map { f-> f.digitToInt() } }


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

            val inputInt= input.map { it.map { f-> f.digitToInt() }.toMutableList() }
            var basinMap =inputInt
            var basinSizes= mutableListOf<Int>()

            inputInt.forEachIndexed { y, line ->
                line.forEachIndexed { x, value ->
                    if (value <=8) basinMap[y][x]=0
                }
            }

            basinMap.forEachIndexed { y, line ->
                line.forEachIndexed { x, value ->
                    var basincount= mutableListOf<Int>(0)
                    checkForBasin(x,y,basincount,line,basinMap)
                    if(basincount[0]!=0){
                        basinSizes.add(basincount[0])
                    }
                }
            }
            return basinSizes.sorted().takeLast(3).reduce { acc, i ->  acc*i}
        }

        fun checkForBasin(x:Int,y:Int,basincount:MutableList<Int>,line:MutableList<Int>,basinMap:List<MutableList<Int>>){
            if (basinMap[y][x]==9) return
            else if (basinMap[y][x]==1) return
            else if (basinMap[y][x]==0) {
                basincount[0]++
                basinMap[y][x]=1

                if(x>0) checkForBasin(x-1,y,basincount,line,basinMap)
                if(y>0) checkForBasin(x,y-1,basincount,line,basinMap)
                if(x<line.size-1) checkForBasin(x+1,y,basincount,line,basinMap)
                if(y<basinMap.size-1) checkForBasin(x,y+1,basincount,line,basinMap)
            }

        }

    }
}