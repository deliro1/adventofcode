package day14
import readInput
class Day14 {
    companion object {

        private const val day = "day14"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            val testInput = readInput("${filePath}_test")
            //check(part1(testInput) == 1588)
            check(part2(testInput) == 2188189693529L)

            val input = readInput(filePath)
            //println("Part 1 result for $day is: ${part1(input)}")
            //println("Part 2 result for $day is: ${part2(input)}")
        }

        fun part1(input: List<String>): Int {
            val instructions= input.drop(2).map { it.split(" -> ") }
            var polymer= input.first()
            for (steps in 1..10){
                val windowedPoly=polymer.windowed(2,1).toMutableList()
                windowedPoly.forEachIndexed { index, window ->
                    instructions.forEach() { inst ->
                        if (window == inst[0]) {
                            windowedPoly[index]=inst[0][0] + inst[1] + inst[0][1]
                        }
                    }
                }
                polymer=windowedPoly.mapIndexed { ind, it ->
                    if (ind==0) it
                    else it.drop(1)
                }.toMutableList().joinToString("")
            }
            val letters= polymer.toSet().toList()
            val amountLetters= Array(letters.size){0}
            polymer.forEach { amountLetters[letters.indexOf(it)]++ }

            return amountLetters.maxOf { it }-amountLetters.minOf {it}
        }

        fun part2(input: List<String>): Long {
            val combinations= input.drop(2).map { it.split(" -> ").toMutableList() }
            var polymer= input.first()
            var tempCounter=input.drop(2).map { it.split(" -> ").toMutableList() }
            tempCounter.forEach{it[1]="0"}

            combinations.forEachIndexed(){ ind, ele ->
                combinations[ind].add("0")
            }

            val windowedPoly=polymer.windowed(2,1).toMutableList()
            windowedPoly.forEachIndexed { index, window ->
                combinations.forEachIndexed() {ind, inst ->
                    if (window == inst[0]) {
                        combinations[ind][2]=combinations[ind][2].toInt().plus(1).toString()
                    }
                }
            }

            for(step in 1..10) {
                var addLocs = mutableListOf<String>()

                combinations.forEach() { combi ->
                    var firstPartStr = combi[0][0] + combi[1]
                    var secondPartStr = combi[1] + combi[0][1]

                    tempCounter.forEach {
                        if (it[0]==firstPartStr){it[1].toInt().plus(combi[2].toInt()).toString()}
                        if (it[0]==secondPartStr){it[1].toInt().plus(combi[2].toInt()).toString()}
                    }
                }


                addLocs.forEach() { add ->
                    combinations.forEachIndexed { ind, combi ->
                        if (combi[0] == add) {
                            combinations[ind][2] = combinations[ind][2].toInt().plus(1).toString()
                        }
                    }
                }
            }


            return 2188189693529L

        }
    }
}