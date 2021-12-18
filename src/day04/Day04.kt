package day04
import readInput

class Day04 {
    companion object {

        private const val day = "day04"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 4512)
            check(part2(testInput) == 1924)

            val input = readInput(filePath)
            println("Part 1 result for $day is: ${part1(input)}")
            println("Part 2 result for $day is: ${part2(input)}")
        }

        fun part1(input: List<String>): Int {
            val bingoDraws= input.first().split(",")
            val bingoCards = input.slice(1 until input.size)

            val AA= bingoCards.map { it.split("  "," ").toMutableList() }
            val bingoCardsX= AA.map { if(it.size==6){ it.drop(1).toMutableList() } else{ it.toMutableList() } }.toMutableList()

            val bingoCardsPairs= Array(bingoCardsX.size){Array(5){Pair("", false)}.toMutableList() }.toMutableList()
            bingoCardsX.forEachIndexed {lineindex,line->
                line.forEachIndexed{entryindex, entry->
                    bingoCardsPairs[lineindex][entryindex]=bingoCardsPairs[lineindex][entryindex].copy(first=bingoCardsX[lineindex][entryindex])
                }
            }

            val CardsNormalPairs= Array(bingoCardsX.size/6){
                mutableListOf(mutableListOf(Pair("", false)))
            }
            val CardsTransPairs= Array(bingoCardsX.size/6){
                mutableListOf(mutableListOf(Pair("", false)))
            }

            CardsNormalPairs.forEachIndexed { index, lists ->
                CardsNormalPairs[index]=bingoCardsPairs.slice((index*6)+1 until (index*6)+6).toMutableList()
                CardsTransPairs[index]=transposeNestedArray(bingoCardsPairs.slice((index*6)+1 until (index*6)+6))
            }


            //finaly start drawing numbers
            var result=0

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
                            if((line[0].second && line[1].second && line[2].second && line[3].second && line[4].second)){
                                //Thats a bingo!
                                if (winningCardIndex==-1) {
                                    winningCardIndex=cardI
                                    result=calcResult(CardsNormalPairs[cardI])*draw.toInt()
                                }
                            }
                            if((CardsTransPairs[cardI][lineI][0].second && CardsTransPairs[cardI][lineI][1].second && CardsTransPairs[cardI][lineI][2].second && CardsTransPairs[cardI][lineI][3].second && CardsTransPairs[cardI][lineI][4].second )){
                                //Thats a bingo!
                                if (winningCardIndex==-1) {
                                    winningCardIndex=cardI
                                    result=calcResult(CardsTransPairs[cardI])*draw.toInt()
                                }
                            }
                        }
                    }
                }
            }
            return result
        }

        fun part2(input: List<String>): Int {
            val bingoDraws= input.first().split(",")
            val bingoCards = input.slice(1 until input.size)

            val AA= bingoCards.map { it.split("  "," ").toMutableList() }
            val bingoCardsX= AA.map { if(it.size==6){ it.drop(1).toMutableList() } else{ it.toMutableList() } }.toMutableList()


            val bingoCardsPairs= Array(bingoCardsX.size){Array(5){Pair("", false)}.toMutableList() }.toMutableList()
            bingoCardsX.forEachIndexed {lineindex,line->
                line.forEachIndexed{entryindex, entry->
                    bingoCardsPairs[lineindex][entryindex]=bingoCardsPairs[lineindex][entryindex].copy(first=bingoCardsX[lineindex][entryindex])
                }
            }

            val CardsNormalPairs= Array(bingoCardsX.size/6){
                mutableListOf(mutableListOf(Pair("", false)))
            }
            val CardsTransPairs= Array(bingoCardsX.size/6){
                mutableListOf(mutableListOf(Pair("", false)))
            }

            CardsNormalPairs.forEachIndexed { index, lists ->
                CardsNormalPairs[index]=bingoCardsPairs.slice((index*6)+1 until (index*6)+6).toMutableList()
                CardsTransPairs[index]=transposeNestedArray(bingoCardsPairs.slice((index*6)+1 until (index*6)+6))
            }


            //finaly start drawing numbers
            var result=0
            val winningCards= mutableListOf<Int>()

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
                            if((line[0].second && line[1].second && line[2].second && line[3].second && line[4].second)){
                                //Thats a bingo!
                                    winningCardIndex=cardI
                                    if(!winningCards.contains(cardI)) {
                                        winningCards.add(cardI)
                                        result = calcResult(CardsNormalPairs[cardI]) * draw.toInt()
                                    }
                            }
                            if((CardsTransPairs[cardI][lineI][0].second && CardsTransPairs[cardI][lineI][1].second && CardsTransPairs[cardI][lineI][2].second && CardsTransPairs[cardI][lineI][3].second && CardsTransPairs[cardI][lineI][4].second )){
                                //Thats a bingo!
                                    winningCardIndex=cardI
                                    if(!winningCards.contains(cardI)) {
                                        winningCards.add(cardI)
                                        result = calcResult(CardsTransPairs[cardI]) * draw.toInt()
                                    }
                            }
                        }
                    }
                }
            }
            return result
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

fun calcResult(card: MutableList<MutableList<Pair<String, Boolean>>>):Int{
    var result=0
    card.forEach{line ->
        line.forEach { element->
            if(!element.second){
                result+=element.first.toInt()
            }
        }
    }
    return result
}
