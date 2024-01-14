package com.example.beatme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beatme.ui.theme.Winorlose
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    viewModel:MainViewModel
){
    val state by viewModel.state.collectAsState()
    val context= LocalContext.current

    when(state.gameStage){
        GameStage.PLAYING -> {
            ScreenContent(state = state,
                onValueChange = {
                    viewModel.updatetextfieldvalue(usernum = it)},
                 enterButtonClicked = {
                    viewModel.onUserInput(
                        userInput = state.usernumber,
                        context=context)
                })
        }

        GameStage.WON -> {
            Winorlose(text="CONGO MATE\n YOU WON",
                buttonText="PLAY AGAIN",
                mysterynumber=state.mysteryNumber,
                image= painterResource(R.drawable.ic_win),
                reset={viewModel.resetGame()})
        }
        GameStage.LOST -> {
            Winorlose(text="YOU LOSE BRO",
                buttonText="WANNA TRY AGAIN",
                mysterynumber=state.mysteryNumber,
                image= painterResource(R.drawable.ic_row),
                reset={viewModel.resetGame()})
        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(
    state:gamekisaaristate,
    onValueChange:(String) -> Unit,
    enterButtonClicked:() -> Unit)
{
    val focusRequester=remember{
        FocusRequester() }
    LaunchedEffect(key1 = Unit ){
        delay(500)
        focusRequester.requestFocus()
    }
    Column(
        modifier= Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(20.dp)
    ){
        Text(
            text = buildAnnotatedString {
                append("GUESSES LEFT:")
                withStyle(style= SpanStyle(color=Color.White)){
                    append("${state.noOfGuessLeft}")
                }
            },
            color=Color.Yellow,
            fontSize = 18.sp
        )
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(10.dp)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            state.guessedNumberList.forEach { number->
                Text(
                    text = "$number",
                    color=Color.Yellow,
                    fontSize = 42.sp,modifier=Modifier
                        .padding(end=20.dp)
                )
            }
        }
        Text(
            text=state.hintDescription,
            color=Color.White,
            fontSize=22.sp,
            textAlign= TextAlign.Center,
            fontStyle= FontStyle.Italic,
            lineHeight=30.sp,
            modifier=Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            modifier= Modifier
                .padding(horizontal = 40.dp)
                .focusRequester(focusRequester),
            value =state.usernumber ,
            onValueChange=onValueChange,
            colors= TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor=Color.Transparent

            ),
            textStyle = TextStyle(
                textAlign=TextAlign.Center,
                fontSize=48.sp
            ),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(10.dp))


        Button(modifier= Modifier
            .align(Alignment.End)
            .padding(end = 40.dp),
            onClick = {
                enterButtonClicked()

            }) {
            Text(text = "ENTER"
                , fontSize = 18.sp)

        }
    }
}

