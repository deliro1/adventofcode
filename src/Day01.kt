import java.io.File

fun main() {
    fun part1(input: List<String>): Int {
        var amount=0
        for (k in(1..1999)){
            if (input[k]>input[k-1]){
                amount++
            }
        }
        println("Amount of increases: $amount")
        return amount
    }

    fun part2(input: List<String>): Int {
        var increaseAmount=0
        val numbers= input.map { it.toInt() }

        for (k in (2..1998)) {
            val thisSum = numbers[k - 1] + numbers[k] + numbers[k + 1]
            val lastSum = numbers[k - 2] + numbers[k - 1] + numbers[k]
            if (thisSum > lastSum) {
                increaseAmount++
            }
            val increa= thisSum > lastSum
            //println("$lastSum < $thisSum , $increa , makes $increaseAmount, step $k")
        }
        println("Amount of increases: $increaseAmount")
        return increaseAmount
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day01_test")
    part1(input)
    part2(input)
}
