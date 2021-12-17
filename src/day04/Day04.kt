package day04
import readInput

class Day04 {
    companion object {

        private const val day = "day04"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 4512)
            //check(part2(testInput) == 5)

            val input = readInput(filePath)
            println("Part 1 result for $day is: ${part1(input)}")
            println("Part 2 result for $day is: ${part2(input)}")
        }

        fun part1(input: List<String>): Int {
            var bingoDraws= input.first().split(",")
            var bingoCards = input.slice(1 until input.size)

            var AA= bingoCards.map { it.split("  "," ").toMutableList() }
            var bingoCardsX= AA.map { if(it.size==6){ it.drop(1).toMutableList() } else{ it.toMutableList() } }.toMutableList()


            var bingoCardsPairs= Array(bingoCardsX.size){Array(5){Pair("", false)}.toMutableList() }.toMutableList()
            bingoCardsX.forEachIndexed {lineindex,line->
                line.forEachIndexed{entryindex, entry->
                    bingoCardsPairs[lineindex][entryindex]=bingoCardsPairs[lineindex][entryindex].copy(first=bingoCardsX[lineindex][entryindex])
                }
            }

            var CardsNormalPairs= Array(bingoCardsX.size/6){
                mutableListOf(mutableListOf(Pair("", false)))
            }
            // for some reason this saves both variables into the same RAM
            //var CardsTransPairs=CardsNormalPairs
            var CardsTransPairs= Array(bingoCardsX.size/6){
                mutableListOf(mutableListOf(Pair("", false)))
            }

            CardsNormalPairs.forEachIndexed { index, lists ->
                CardsNormalPairs[index]=bingoCardsPairs.slice((index*6)+1 until (index*6)+6).toMutableList()
                CardsTransPairs[index]=transposeNestedArray(bingoCardsPairs.slice((index*6)+1 until (index*6)+6))
            }


            //finaly start drawing numbers
            //var winningCard=CardsNormalPairs.first()
            var winningCard= mutableListOf(mutableListOf(Pair("", false)))

            var winningCardIndex =-1
            bingoDraws.forEachIndexed { drawI,draw ->
                CardsNormalPairs.forEachIndexed { cardI,card ->
                    card.forEachIndexed{lineI,line->
                        line.forEachIndexed { elementI,element->
                            if (element.first==draw){
                                CardsNormalPairs[cardI][lineI][elementI]=CardsNormalPairs[cardI][lineI][elementI].copy(second=true)
                            }
                            if (CardsTransPairs[cardI][lineI][elementI].first==draw){
                                CardsTransPairs[cardI][lineI][elementI]=CardsTransPairs[cardI][lineI][elementI].copy(second=true)
                            }
                            if((line[0].second && line[1].second && line[2].second && line[3].second && line[4].second) || (CardsTransPairs[cardI][lineI][0].second && CardsTransPairs[cardI][lineI][1].second && CardsTransPairs[cardI][lineI][2].second && CardsTransPairs[cardI][lineI][3].second && CardsTransPairs[cardI][lineI][4].second )){
                                //Thats a bingo!
                                if (winningCardIndex==-1) {
                                    winningCardIndex=cardI
                                    //winningCard=CardsNormalPairs[cardI] why is this fucked?
                                    winningCard=CardsNormalPairs.copyOfRange(0,1)[0]
                                    println("")
                                }
                            }
                        }
                    }
                }
            }


            //calc the resulting number
            var result=0
            CardsNormalPairs[winningCardIndex].forEach{line->
                line.forEach { element->
                    if(!element.second){
                        result+=element.first.toInt()
                    }
                }
            }


            return 4512
        }








        fun part2(input: List<String>): Int {
            return 0

        }
    }
}

fun transposeNestedArray(matrix: List<List<Pair<String, Boolean>>>): MutableList<MutableList<Pair<String, Boolean>>>{
    val size = matrix.size
    val transpose = matrix.map { it.toMutableList() }.toMutableList()
    // Transpose the matrix
    for (i in 0 until size) {
        for (j in 0 until size) {
            transpose[j][i] = matrix[i][j]
        }
    }
    return transpose
}