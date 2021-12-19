package day10
import readInput

class Day10 {
    companion object {

        private const val day = "day10"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 26397)
            check(part2(testInput) == 288957L)

            val input = readInput(filePath)
            println("Part 1 result for $day is: ${part1(input)}")
            println("Part 2 result for $day is: ${part2(input)}")
        }

        fun part1(input: List<String>): Int {
            var lineList=input.toMutableList()
            var result=0

            lineList.forEachIndexed { lineInx, line ->
                var oldLength=1
                var newLength=line.length
                while (oldLength!=newLength){
                    lineList[lineInx]=removeChunk(lineList[lineInx])
                    oldLength=newLength
                    newLength=lineList[lineInx].length
                }

                val penalty = when(lineList[lineInx].findAnyOf(listOf(")","]","}",">"))?.second){
                    ")" -> 3
                    "]" -> 57
                    "}" -> 1197
                    ">" -> 25137
                    else -> 0
                }
                result += penalty
            }
            return result
        }



        fun part2(input: List<String>): Long {
            var lineList=input.toMutableList()
            var removeThis= mutableListOf<Int>()
            val addList=input.toMutableList()

            lineList.forEachIndexed { lineInx, line ->
                var oldLength = 1
                var newLength = line.length
                while (oldLength != newLength) {
                    lineList[lineInx] = removeChunk(lineList[lineInx])
                    oldLength = newLength
                    newLength = lineList[lineInx].length
                }
                when (lineList[lineInx].findAnyOf(listOf(")", "]", "}", ">"))?.second) {
                    ")" -> removeThis.add(lineInx)
                    "]" -> removeThis.add(lineInx)
                    "}" -> removeThis.add(lineInx)
                    ">" -> removeThis.add(lineInx)
                }
            }

            for (k in addList.size-1 downTo 0){
                if(removeThis.contains(k)){
                    addList.removeAt(k)
                    lineList.removeAt(k)
                }else addList[k]=""
            }

            lineList.forEachIndexed { index, line ->
                for (ind in line.length-1 downTo 0){
                    when (line[ind]) {
                        '(' -> addList[index]+=")"
                        '[' -> addList[index]+="]"
                        '{' -> addList[index]+="}"
                        '<' -> addList[index]+=">"
                    }
                }
            }


            var result= Array(addList.size){0L}

            addList.forEachIndexed { index, line ->
                line.forEach { char ->
                    result[index]*=5L
                    when(char){
                        ')' -> result[index]+=1L
                        ']' -> result[index]+=2L
                        '}' -> result[index]+=3L
                        '>' -> result[index]+=4L
                    }
                }
            }

            var sortResult=result.sorted()
            return sortResult[sortResult.size/2]
        }
    }
}

private fun removeChunk(line: String): String{
    var lineOut=line
    for (char in 0 until line.length-1){
        if((line[char+1]==line[char].plus(1))
            || (line[char]=='[' && line[char+1]==line[char].plus(2))
            || (line[char]=='{' && line[char+1]==line[char].plus(2))
            || (line[char]=='<' && line[char+1]==line[char].plus(2))){
            lineOut=lineOut.removeRange(char,char+2)
            break
        }
    }
    return lineOut
}