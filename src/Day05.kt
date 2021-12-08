
fun main() {
    fun part1(input: List<String>): Int {
        val inputSegmented = input.map { it.split(","," -> ") }
        val landscape = Array(1000) { IntArray(1000) { 0 } }
        var dangerZones=0

        var xStart=0; var xEnd=0; var yStart=0; var yEnd=0

        inputSegmented.forEach{
            xStart= it[0].toInt(); xEnd= it[2].toInt(); yStart= it[1].toInt(); yEnd= it[3].toInt()
            var line=(0..0)
            var lineType=""
            if(xStart==xEnd){
                lineType="y"
                if (yStart < yEnd) {
                    line = (yStart..yEnd)
                }else{
                    line = (yEnd..yStart)
                }
            } else if(yStart==yEnd){
                lineType="x"
                if (xStart < xEnd) {
                    line = (xStart..xEnd)
                }else{
                    line = (xEnd..xStart)
                }
            }

            line.forEach{element ->
                when(lineType){
                    "x"-> landscape[yStart][element] +=1
                    "y"-> landscape[element][xStart] +=1
                }
            }

        }
        for(row in landscape){
            for(element in row){
                if(element >= 2){
                    dangerZones++}
            }
        }

        return dangerZones
    }



    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 0)

    val input = readInput("Day05")
    println("Part 1 result for Day05 is: ${part1(input)}")
    println("Part 2 result for Day05 is: ${part2(input)}")
}