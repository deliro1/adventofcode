fun main() {
    fun part1(input: List<String>): Int {
        val listSize= input.size
        val incidence  = mutableListOf<Int>(0,0,0,0,0,0,0,0,0,0,0,0) //Find out how to init a zero list not like this trash
        val gamma  = mutableListOf<Int>(0,0,0,0,0,0,0,0,0,0,0,0)
        val epsilon  = mutableListOf<Int>(1,1,1,1,1,1,1,1,1,1,1,1)
        input.forEach(){
            it.forEachIndexed { index, c ->
                incidence[index] += c.toString().toInt()
            }
        }
        println("incidence: $incidence")
        incidence.forEachIndexed { index, i ->
            if (i>listSize/2){
                gamma[index]=1
                epsilon[index]=0
            } else if(i==listSize/2){
                println("Error: same amount of 0 and 1")
            }
        }
        println(gamma)
        println(epsilon)
        val gammaInt= gamma.joinToString("")
        val epsilonInt= epsilon.joinToString("")
        println(gammaInt)
        println(epsilonInt)


        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day03_test")
    //check(part1(testInput) == 0)
    //check(part2(testInput) == 0)

    val input = readInput("Day03")
    println("Part 1 result for Day03 is: ${part1(input)}")
    println("Part 2 result for Day03 is: ${part2(input)}")
}