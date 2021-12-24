package day13
import readInput

class Day13 {
    companion object {

        private const val day = "day13"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 17)
            //check(part2(testInput) == 5)

            val input = readInput(filePath)
            println("Part 1 result for $day is: ${part1(input)}")
            println("Part 2 result for $day is: ${part2(input)}")
        }

        fun part1(input: List<String>): Int {
            var initialDotLocs= input.dropLastWhile{ it!="" }.dropLast(1).map { line->
                line.split(",").map { str -> str.toInt() }
            }
            var foldingCommands= input.takeLastWhile { it!="" }.map { line->
                line.removePrefix("fold along ").split("=")
            }

            var paperArray= Array(1500){Array(1500){0} }
            initialDotLocs.forEach{ locs ->
                paperArray[locs[1]][locs[0]]++
            }
            var result=0
            foldingCommands.forEachIndexed { index,command ->

                paperArray.forEachIndexed{yloc, row ->
                    row.forEachIndexed { xloc, element ->
                        if(command[0]=="x" && xloc>command[1].toInt() && paperArray[yloc][xloc]==1){
                            paperArray[yloc][2*command[1].toInt()-xloc]=1
                            paperArray[yloc][xloc]=0
                        }
                        if(command[0]=="y" && yloc>command[1].toInt() && paperArray[yloc][xloc]==1){
                            paperArray[2*command[1].toInt()-yloc][xloc]=1
                            paperArray[yloc][xloc]=0
                        }
                    }
                }
                if(index==0){
                    result=paperArray.sumOf { it.sum() }
                }
            }
            return result
        }

        fun part2(input: List<String>): Int {
            var initialDotLocs= input.dropLastWhile{ it!="" }.dropLast(1).map { line->
                line.split(",").map { str -> str.toInt() }
            }
            var foldingCommands= input.takeLastWhile { it!="" }.map { line->
                line.removePrefix("fold along ").split("=")
            }

            var paperArray= Array(1500){Array(1500){0} }
            initialDotLocs.forEach{ locs ->
                paperArray[locs[1]][locs[0]]++
            }
            foldingCommands.forEachIndexed { index,command ->

                paperArray.forEachIndexed{yloc, row ->
                    row.forEachIndexed { xloc, element ->
                        if(command[0]=="x" && xloc>command[1].toInt() && paperArray[yloc][xloc]==1){
                            paperArray[yloc][2*command[1].toInt()-xloc]=1
                            paperArray[yloc][xloc]=0
                        }
                        if(command[0]=="y" && yloc>command[1].toInt() && paperArray[yloc][xloc]==1){
                            paperArray[2*command[1].toInt()-yloc][xloc]=1
                            paperArray[yloc][xloc]=0
                        }
                    }
                }

            }
            for (line in 0..6) {
                println(paperArray[line].toList().joinToString("").replace("0",".").replace("1","#"))
            }
            return 0
        }


    }
}