package com.example.boeteste.components.defaultButton

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boeteste.ui.theme.PrimaryBlue

@Composable
fun DefaultButton(
    onClick: () -> Unit,
    text: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    FilledIconButton(
        onClick = onClick,
        colors = IconButtonDefaults.filledIconButtonColors(containerColor = backgroundColor),
        modifier = modifier
            .height(72.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)  // Centraliza o botão horizontalmente
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)  // Borda com raio de 8 pixels
    ) {
        Text(
            text = text,
            color = Color.White,  // Cor do texto
            textAlign = TextAlign.Center,  // Texto alinhado ao centro
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()  // Ocupa toda a largura do botão
        )
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun DefaultButtonPreview(){
    DefaultButton(
        onClick = { /*TODO*/ },
        text = "Log in",
        backgroundColor = PrimaryBlue
    )
}
