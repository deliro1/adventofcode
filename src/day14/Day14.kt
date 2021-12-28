package day14
import readInput
class Day14 {
    companion object {

        private const val day = "day14"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 1588)
            check(part2(testInput) == 2188189693529L)

            val input = readInput(filePath)
            println("Part 1 result for $day is: ${part1(input)}")
            println("Part 2 result for $day is: ${part2(input)}")
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
            val startPolymer= input.first()
            val inputInstructs= input.drop(2).map { it.split(" -> ").toMutableList() }

            var instructions= mutableListOf<List<String>>()
            inputInstructs.forEachIndexed() { ind, str ->
                var addList= emptyList<String>().toMutableList()
                addList.add(str[0])
                addList.add(str[0][0] + str[1])
                addList.add(str[1] + str[0][1])
                instructions.add(addList)
            }

            var addingMap = emptyMap<String, Long>().toMutableMap()
            inputInstructs.forEach(){ addingMap[it[0]] = 0 }

            var letterCounter = emptyMap<String, Long>().toMutableMap()
            inputInstructs.forEach(){ letterCounter[it[1]] = 0L }
            startPolymer.windowed(1,1).forEach { window -> letterCounter.merge(window, 1L, Long::plus) }

            var combiCounter = emptyMap<String, Long>().toMutableMap()
            inputInstructs.forEach(){ combiCounter[it[0]] = 0 }
            startPolymer.windowed(2,1).forEach { window -> combiCounter.merge(window, 1, Long::plus) }


            for (steps in 1..40) {
                combiCounter.forEach { combi ->
                    instructions.forEach { instruct ->
                        if (instruct[0] == combi.key) {
                            addingMap.merge(instruct[1], combi.value, Long::plus)
                            addingMap.merge(instruct[2], combi.value, Long::plus)
                            addingMap.merge(instruct[0], -combi.value, Long::plus)
                            letterCounter.merge(instruct[1][1].toString(), combi.value.toLong(), Long::plus)
                        }
                    }
                }
                addingMap.forEach {
                    combiCounter.merge(it.key, it.value, Long::plus)
                    addingMap[it.key]=0
                }
            }

            var AAA=letterCounter.maxOf { it.value }-letterCounter.minOf { it.value }
            return letterCounter.maxOf { it.value }-letterCounter.minOf { it.value }

        }
    }
}