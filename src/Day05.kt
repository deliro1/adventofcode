fun main() {
    fun part1(input: List<String>): Int {
        val inputSegmented = input.map { it.split(","," -> ") }.map{ it.map{str -> str.toInt()}}
        val maxvalue=findMaxIn2DList(inputSegmented)+1
        val landscape = Array(maxvalue) { IntArray(maxvalue) { 0 } }
        var dangerZones=0
        var xStart: Int; var xEnd: Int; var yStart: Int; var yEnd: Int

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
        val inputSegmented = input.map { it.split(","," -> ") }.map{ it.map{str -> str.toInt()}}
        val maxvalue=findMaxIn2DList(inputSegmented)+1
        val landscape = Array(maxvalue) { IntArray(maxvalue) { 0 } }
        var dangerZones=0
        var xStart: Int; var xEnd: Int; var yStart: Int; var yEnd: Int

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

            if(xStart!=xEnd && yStart!=yEnd){
                lineType="d"
                val xPosis= xStart.toward(xEnd).toList()
                val yPosis= yStart.toward(yEnd).toList()
                for (k in 0..xPosis.lastIndex ){
                    landscape[yPosis[k]][xPosis[k]] +=1
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
                    dangerZones++
                }
            }
        }
        return dangerZones
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

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
private infix fun Int.toward(to: Int): IntProgression {
    val step = if (this > to) -1 else 1
    return IntProgression.fromClosedRange(this, to, step)
}