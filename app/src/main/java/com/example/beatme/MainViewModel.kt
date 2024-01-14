package com.example.beatme

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {
    private val _state= MutableStateFlow(gamekisaaristate())
    val state=_state.asStateFlow()

    fun updatetextfieldvalue(usernum:String) {
        _state.update {
            it.copy(usernumber = usernum)
        }
    }
     fun resetGame(){
         _state.value= gamekisaaristate()
     }

        fun onUserInput(
            userInput:String,
            context: Context
        ){
            val userInputInInt=try{
                userInput.toInt()
            }catch (e:Exception){
                0
            }
            if(userInputInInt !in 1..99){
                Toast.makeText(
                    context,
                    "Please enter a number between 0 and 100",
                    Toast.LENGTH_SHORT).show()


            }
            val currentValue=_state.value
            val newGuessLeft=currentValue.noOfGuessLeft -1
            val newGameStage=when{

                userInputInInt==currentValue.mysteryNumber ->GameStage.WON
                newGuessLeft==0 -> GameStage.LOST
                else -> GameStage.PLAYING
            }
            val newHintDescription=when{
                userInputInInt >currentValue.mysteryNumber ->{
                    "Hint\n You are Guessing bigger number than the mystery Number"
                }


                userInputInInt < currentValue.mysteryNumber ->{
                    "Hint\n You are Guessing smaller number than the mystery Number"
                }
                else -> ""
            }
            val newGuessedNoList=currentValue.guessedNumberList.plus(userInputInInt)

            _state.update{
                it.copy(
                    noOfGuessLeft = newGuessLeft,
                    guessedNumberList = newGuessedNoList,
                    usernumber = "",
                    hintDescription = newHintDescription,
                    gameStage = newGameStage
                )
            }

        }
    }