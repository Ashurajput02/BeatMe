package com.example.beatme

data class gamekisaaristate(
    val usernumber:String="",
    val noOfGuessLeft: Int=5,
    val guessedNumberList:List<Int> = emptyList(),
    val mysteryNumber:Int=(1..99).random(),
    val hintDescription:String="Guess\n the mystery Number between\n 0 and 100",
    val gameStage:GameStage=GameStage.PLAYING
)

enum class GameStage{
    WON,
    LOST,
    PLAYING
}
