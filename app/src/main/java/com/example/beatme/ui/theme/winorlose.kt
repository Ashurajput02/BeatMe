package com.example.beatme.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.beatme.R


@Composable
fun Winorlose(
    text:String,
    buttonText:String,
    mysterynumber:Int,
    image: Painter,
    reset:()->Unit
) {
    Dialog(onDismissRequest = reset) {
        Column(
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Color.Yellow),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "My Number was $mysterynumber",
                fontSize = 26.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = image,
                contentDescription = "winining icon",
                modifier = Modifier.size(80.dp)

            )
            Button(onClick = reset) {

                Text(
                    text = buttonText,
                    fontSize = 18.sp
                )
            }
        }
    }
}


@Preview
@Composable
fun Prevwin(){
    Winorlose(text="CONGO MATE\n YOU WON",
        buttonText="PLAY AGAIN",
        mysterynumber=32,
        image= painterResource(R.drawable.ic_win),
        reset={})
}

@Preview
@Composable
fun Prevlose(){
    Winorlose(text="YOU LOSE BRO",
        buttonText="WANNA TRY AGAIN",
        mysterynumber=32,
        image= painterResource(R.drawable.ic_row),
        reset={})
}