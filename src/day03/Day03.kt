package day03
import readInput

class Day03 {
    companion object {

        private const val day = "day03"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 198)
            check(part2(testInput) == 230)

            val input = readInput(filePath)
            println("Part 1 result for $day is: ${part1(input)}")
            println("Part 2 result for $day is: ${part2(input)}")
        }

        fun part1(input: List<String>): Int {

            val incidence = Array(input[0].length){0}
            val gamma  = Array(input[0].length){0}
            val epsilon  = Array(input[0].length){1}

            input.forEach{line ->
                line.forEachIndexed { index, c ->
                    incidence[index] += c.digitToInt()
                }
            }
            incidence.forEachIndexed { index, i ->
                if (i>input.size/2){
                    gamma[index]=1
                    epsilon[index]=0
                } else if(i==input.size/2){
                    println("Error: same amount of 0 and 1")
                }
            }
            val gammaInt= gamma.joinToString("").toInt(2)
            val epsilonInt= epsilon.joinToString("").toInt(2)

            return epsilonInt * gammaInt
        }


        fun part2(input: List<String>): Int {

            val inputO2= input.toMutableList()
            val inputCO2= input.toMutableList()
            var o2 = 0
            var co2 = 0

            for (k in input[0].indices){
                val commonBito2=findCommonbit(inputO2,k)
                val commonBitco2=findCommonbit(inputCO2,k)
                input.forEach{line ->
                    if (line[k].digitToInt()!=commonBito2 && inputO2.size>1){
                        inputO2.remove(line)
                    }else if(inputO2.size==1){
                        o2=inputO2[0].toInt(2)
                    }
                    if (line[k].digitToInt()==commonBitco2 && inputCO2.size>1){
                        inputCO2.remove(line)
                    }else if(inputCO2.size==1){
                        co2=inputCO2[0].toInt(2)
                    }
                }
            }
            return o2*co2
        }

        fun findCommonbit(input: List<String>, position: Int): Int{
            var ones=0.0
            var mostCommon=0
            for (k in input.indices){
                if(input[k][position]=='1') ones++
            }
            if (ones>=input.size.toDouble()/2) mostCommon=1
            return mostCommon
        }
    }
}