fun main() {
    fun part1(input: List<String>): Int {
        val directions = input.map { it.split(" ") }
        var horizontalPosition = 0
        var depth = 0
        directions.forEach {
            when (it[0]) {
                "forward" -> horizontalPosition += it[1].toInt()
                "down" -> depth += it[1].toInt()
                "up" -> depth -= it[1].toInt()
                else -> println("not a valid movement direction")
            }
        }
        return depth * horizontalPosition
    }

    fun part2(input: List<String>): Int {
        val directions = input.map { it.split(" ") }
        var horizontalPosition = 0
        var depth = 0
        var aim = 0
        directions.forEach {
            when (it[0]) {
                "forward" -> { horizontalPosition += it[1].toInt(); depth += aim * it[1].toInt() }
                "down" -> aim += it[1].toInt()
                "up" -> aim -= it[1].toInt()
                else -> println("not a valid movement direction")
            }
        }
        return depth * horizontalPosition
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println("Part 1 result for Day02 is: ${part1(input)}")
    println("Part 2 result for Day02 is: ${part2(input)}")
}