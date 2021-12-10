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
                //val digitsStrB = it[0].split(" ").sortedBy {itt-> itt.length }
                val digitsStr = it[0].split(" ").map() {k-> String(k.toCharArray().apply { sort() }) }.sortedBy { itt-> itt.length }

                cypher[1]=digitsStr[0]
                cypher[7]=digitsStr[1]
                cypher[4]=digitsStr[2]
                cypher[8]=digitsStr[9]

                var AA= Regex("[${digitsStr[1]}]")
                var CC= cypher[4].replace(Regex("[${cypher[1]}]"),"")

                digitsStr.forEach{str ->
                    var A= str.toCharArray()
                    var B= cypher[1].toCharArray()
                    var C=A.contains(B)

                    if(str.length==5 && str.contains(cypher[1])) cypher[3]=str
                    else if(str.length==5 && str.contains(cypher[4].replace(Regex("[${cypher[1]}]"),""))) cypher[5]=str
                    else if(str.length==5 && str.contains(cypher[8].replace(Regex("[${cypher[4]}]"),""))) cypher[2]=str

                    else if(str.length==6 && str.contains(cypher[8].replace(Regex("[${cypher[1]}]"),""))) cypher[6]=str
                    else if(str.length==6 && str.contains(cypher[8].replace(Regex("[${cypher[5]}]"),""))) cypher[0]=str
                    else if(str.length==6) cypher[9]=str
                    }

            }
                //var digitsInt = mutableListOf<Int>()

            return 61229
        }


    }
}