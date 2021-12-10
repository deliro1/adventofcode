package day08
import readInput

class Day08 {
    companion object {

        private const val day = "day08"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 26)
            check(part2(testInput) == 61229)

            val input = readInput(filePath)
            println("Part 1 result for $day is: ${part1(input)}")
            println("Part 2 result for $day is: ${part2(input)}")
        }

        fun part1(input: List<String>): Int {
            val digitsTemplate = input.map { it.split(" | ") }
            var count =0

            digitsTemplate.forEach {
                val digit = it[1].split(" ")
                digit.forEach{number ->
                    val segCount = number.length
                    if (segCount == 2 || segCount == 3 || segCount == 4 || segCount == 7) {
                        count++
                    }
                }
            }
            return count
        }


        fun part2(input: List<String>): Int {
            val digitsTemplate = input.map { it.split(" | ") }
            var count =0
            var cypher = mutableListOf<String>("","","","","","","","","","")

            digitsTemplate.forEach {
                val digitsStr = it[0].split(" ").map() {k-> String(k.toCharArray().apply { sort() }) }.sortedBy { itt-> itt.length }
                cypher[1]=digitsStr[0]
                cypher[7]=digitsStr[1]
                cypher[4]=digitsStr[2]
                cypher[8]=digitsStr[9]
                var cypherL=cypher.toMutableList()

                digitsStr.forEach{str ->
                    var strList= str.toList()
                    if(str.length==5 && strList.containsAll(cypherL[1].toList())) cypherL[3]=str
                    else if(str.length==5 && strList.containsAll(cypherL[4].toList().minus(cypherL[1].toList()))) cypherL[5]=str
                    else if(str.length==5 && strList.containsAll(cypherL[8].toList().minus(cypherL[4].toList()))) cypherL[2]=str

                    else if(str.length==6 && strList.containsAll(cypherL[8].toList().minus(cypherL[1].toList()))) cypherL[6]=str
                    else if(str.length==6 && strList.containsAll(cypherL[8].toList().minus(cypherL[5].toList()))) cypherL[0]=str
                    else if(str.length==6) cypherL[9]=str
                }
                val digitsCode = it[1].split(" ").map() {k-> String(k.toCharArray().apply { sort() }) }

                var nochnstring=""
                digitsCode.forEach{code ->
                    when(code){
                        cypherL[0] -> nochnstring+="0"
                        cypherL[1] -> nochnstring+="1"
                        cypherL[2] -> nochnstring+="2"
                        cypherL[3] -> nochnstring+="3"
                        cypherL[4] -> nochnstring+="4"
                        cypherL[5] -> nochnstring+="5"
                        cypherL[6] -> nochnstring+="6"
                        cypherL[7] -> nochnstring+="7"
                        cypherL[8] -> nochnstring+="8"
                        cypherL[9] -> nochnstring+="9"
                    }
                }
                count += nochnstring.toInt()

            }
            return count
        }


    }
}