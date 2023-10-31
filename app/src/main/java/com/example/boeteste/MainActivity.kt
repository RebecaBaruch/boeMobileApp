package com.example.boeteste

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boeteste.pages.HomeScreen
import com.example.boeteste.pages.login.LoginScreen
import com.example.boeteste.pages.registro.RegistroScreen
import com.example.boeteste.ui.theme.PatternGray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(R.drawable.boe_logo),
            contentDescription = "Boe logo",
            modifier = Modifier
                .aspectRatio(0.2f) // Mantém a proporção original da imagem
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter) // Alinha na parte inferior e no centro
                .padding(73.dp) // Adiciona um afastamento da borda
        ) {
            Text(
                text = "By Oberon",
                color = PatternGray,
                fontWeight = FontWeight.Light,
                fontSize = 20.sp
            )
        }
    }
}