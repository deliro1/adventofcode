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
            var cypher = Array<String>(10){""}

            digitsTemplate.forEach {
                val digitsStr = it[0].split(" ").map() {k-> String(k.toCharArray().apply { sort() }) }.sortedBy { itt-> itt.length }
                cypher[1]=digitsStr[0]
                cypher[7]=digitsStr[1]
                cypher[4]=digitsStr[2]
                cypher[8]=digitsStr[9]


                digitsStr.forEach{str ->
                    var strList= str.toList()
                    if(str.length==5 && strList.containsAll(cypher[1].toList())) cypher[3]=str
                    else if(str.length==5 && strList.containsAll(cypher[4].toList().minus(cypher[1].toList()))) cypher[5]=str
                    else if(str.length==5 && strList.containsAll(cypher[8].toList().minus(cypher[4].toList()))) cypher[2]=str
                    else if(str.length==6 && strList.containsAll(cypher[8].toList().minus(cypher[1].toList()))) cypher[6]=str
                    else if(str.length==6 && strList.containsAll(cypher[8].toList().minus(cypher[5].toList()))) cypher[0]=str
                    else if(str.length==6) cypher[9]=str
                }
                val digitsCode = it[1].split(" ").map() {k-> String(k.toCharArray().apply { sort() }) }

                var nochnstring=""
                digitsCode.forEach{code ->
                    when(code){
                        cypher[0] -> nochnstring+="0"
                        cypher[1] -> nochnstring+="1"
                        cypher[2] -> nochnstring+="2"
                        cypher[3] -> nochnstring+="3"
                        cypher[4] -> nochnstring+="4"
                        cypher[5] -> nochnstring+="5"
                        cypher[6] -> nochnstring+="6"
                        cypher[7] -> nochnstring+="7"
                        cypher[8] -> nochnstring+="8"
                        cypher[9] -> nochnstring+="9"
                    }
                }
                count += nochnstring.toInt()
            }
            return count
        }
    }
}