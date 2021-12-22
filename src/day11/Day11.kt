package day11
import readInput

class Day11 {
    companion object {

        private const val day = "day11"
        private val filePath = "$day/${day.capitalize()}"

        var amountFlashes=0
        lateinit var octoMap: List<OctoClass>



        fun runDay() {
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 1656)
            check(part2(testInput) == 195)

            val input = readInput(filePath)
            println("Part 1 result for $day is: ${part1(input)}")
            println("Part 2 result for $day is: ${part2(input)}")
        }

        class OctoClass(var energy: Int, val xloc: Int, val yloc: Int) {
            var hasFlashed= false

            fun flashing() {
                amountFlashes++
                hasFlashed = true
                val surroundingOctos= octoMap.filter { it.xloc in xloc - 1..xloc + 1 && it.yloc in yloc - 1..yloc + 1 }
                surroundingOctos.forEach { surrOcto ->
                    if (!surrOcto.hasFlashed) {surrOcto.energy++}
                    if(surrOcto.energy>9 && !surrOcto.hasFlashed){
                        surrOcto.flashing()
                    }
                }
                energy=0
            }
        }


        fun part1(input: List<String>): Int {
            amountFlashes=0
            octoMap = input.flatMapIndexed { yloc, line -> line.mapIndexed { xloc, c ->  OctoClass(c.digitToInt(),xloc,yloc) } }

            for(steps in 0..99){
                octoMap.forEach{octo ->
                    octo.hasFlashed=false
                    octo.energy++
                }

                octoMap.forEach(){ octo ->
                    if(octo.energy>9 && !octo.hasFlashed){
                        octo.flashing()
                    }
                }
                //println(amountFlashes)
            }
            return amountFlashes
        }



        fun part2(input: List<String>): Int {
            octoMap = input.flatMapIndexed { yloc, line -> line.mapIndexed { xloc, c ->  OctoClass(c.digitToInt(),xloc,yloc) } }
            var syncAchieved=false
            var step=0
            while(!syncAchieved){
                step++
                octoMap.forEach{octo ->
                    octo.hasFlashed=false
                    octo.energy++
                }
                octoMap.forEach(){ octo ->
                    if(octo.energy>9 && !octo.hasFlashed){
                        octo.flashing()
                    }
                }
                syncAchieved=true
                octoMap.forEach { octo ->
                    if (octo.energy!= octoMap[0].energy) syncAchieved=false
                }
                //println(amountFlashes)
            }
            return step
        }


    }
}

