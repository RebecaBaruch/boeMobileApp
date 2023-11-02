package com.example.boeteste.pages.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boeteste.R
import com.example.boeteste.components.defaultButton.DefaultButton
import com.example.boeteste.components.labeledInput.LabeledInput
import com.example.boeteste.components.linkText.LinkText
import com.example.boeteste.components.mixedTitle.MixedTitle
import com.example.boeteste.ui.theme.PatternGray
import com.example.boeteste.ui.theme.PrimaryBlue

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .padding(
                vertical = 73.dp,
                horizontal = 33.dp
            ) // Adicionando um espaçamento geral de 33 pixels
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {

        Column(modifier = Modifier
            .padding(vertical = 33.dp)
            .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(R.drawable.boe_symbol),
                    contentDescription = "Boe Símbolo"
                )
            }

            MixedTitle(
                parteNegrito = "Bem-vindo",
                parteLeve = "de volta",
                boldFirst = true,
                fontSize = 43,
                quebrarTexto = true,
                modifier = null
            )
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            LabeledInput(
                label = "Email",
                value = "",
                onValueChange = {}
            )

            LabeledInput(
                label = "Senha",
                value = "",
                onValueChange = {}
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                LinkText(
                    linkText = "Equeci minha senha"
                )
            }
        }

        DefaultButton(
            onClick = { /*TODO*/ },
            text = "Log in",
            backgroundColor = PrimaryBlue
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Não possui uma conta ainda?",
                fontSize = 16.sp,
                style = TextStyle(
                    color = PatternGray,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
            )

            LinkText(linkText = "Registre-se")
        }
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun LoginPreview(){
    LoginScreen()
}