import org.w3c.dom.ranges.Range
import java.util.stream.IntStream.range

fun main() {
    fun part1(input: List<String>): Int {
        val inputSegmented = input.map { it.split(","," -> ") }.map{ it.map{str -> str.toInt()}}
        val maxvalue=findMaxIn2DList(inputSegmented)
        val landscape = Array(maxvalue) { IntArray(maxvalue) { 0 } }
        var dangerZones=0
        var xStart=0; var xEnd=0; var yStart=0; var yEnd=0


        inputSegmented.forEach{
            xStart= it[0]; xEnd= it[2]; yStart= it[1]; yEnd= it[3]
            var line=(0..0) ;var lineType=""
            if(xStart==xEnd){
                lineType="y"
                line= makeRange(yStart,yEnd)
            } else if(yStart==yEnd){
                lineType="x"
                line= makeRange(xStart,xEnd)
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
    //check(part1(testInput) == 5)
    //check(part2(testInput) == 0)

    val input = readInput("Day05")
    println("Part 1 result for Day05 is: ${part1(input)}")
    println("Part 2 result for Day05 is: ${part2(input)}")
}

private fun findMaxIn2DList(inputArray: List<List<Int>>): Int{
    var maxvalue=0
    for(row in inputArray){
        for(element in row){
            if(element > maxvalue){
                maxvalue=element}
        }
    }
    return maxvalue
}
private fun makeRange(a: Int, b: Int): IntRange{
    return if (a < b) {
        (a..b)
    }else{
        (b..a)
    }
}
