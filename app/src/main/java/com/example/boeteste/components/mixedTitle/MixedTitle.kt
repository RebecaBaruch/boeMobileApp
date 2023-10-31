package com.example.boeteste.components.mixedTitle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boeteste.ui.theme.PatternBlack
import com.example.boeteste.ui.theme.PatternGray

@Composable
fun MixedTitle(
    parteNegrito: String,
    parteLeve: String,
    fontSize: Int, // Agora estamos usando um Int para representar o tamanho da fonte
    quebrarTexto: Boolean,
    boldFirst: Boolean
) {
    val textStyleBold = TextStyle(
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    val textStyleLight = TextStyle(
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Light,
        color = Color.Black
    )

    if(quebrarTexto){
        Column(
            modifier = Modifier
                .padding(vertical = 3.dp, horizontal = 13.dp)
        ) {
            if (boldFirst) {
                Text(
                    text = parteNegrito,
                    style = textStyleBold,
                    modifier = Modifier.padding(bottom = if (quebrarTexto) 8.dp else 0.dp)
                )
                Text(
                    text = parteLeve,
                    style = textStyleLight
                )
            } else {
                Text(
                    text = parteLeve,
                    style = textStyleLight
                )
                Text(
                    text = parteNegrito,
                    style = textStyleBold,
                    modifier = Modifier.padding(bottom = if (quebrarTexto) 8.dp else 0.dp)
                )
            }
        }
    } else {
        Row(
            modifier = Modifier
                .padding(horizontal = 3.dp)
        ) {
            if (boldFirst) {
                Text(
                    text = parteNegrito,
                    style = textStyleBold,
                    modifier = Modifier.padding(bottom = if (quebrarTexto) 8.dp else 0.dp)
                )
                
                Spacer(modifier = Modifier.width(7.dp))
                
                Text(
                    text = parteLeve,
                    style = textStyleLight
                )
            } else {
                Text(
                    text = parteLeve,
                    style = textStyleLight
                )
                Text(
                    text = parteNegrito,
                    style = textStyleBold,
                    modifier = Modifier.padding(bottom = if (quebrarTexto) 8.dp else 0.dp)
                )
            }
        }
    }


}