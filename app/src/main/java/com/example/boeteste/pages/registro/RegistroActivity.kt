package com.example.boeteste.pages.registro

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boeteste.R
import com.example.boeteste.components.backButton.BackButton
import com.example.boeteste.components.defaultButton.DefaultButton
import com.example.boeteste.components.labeledInput.LabeledInput
import com.example.boeteste.pages.registro.ui.theme.BoeTesteTheme
import com.example.boeteste.ui.theme.PrimaryBlue

class RegistroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoeTesteTheme {
                // A surface container using the 'background' color from the theme
                Surface{
                    RegistroScreen()
                }
            }
        }
    }
}

@Composable
fun RegistroScreen() {
    Column(
        modifier = Modifier
            .padding(
                vertical = 33.dp,
                horizontal = 33.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 33.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .weight(1f), // Ocupa o máximo de espaço disponível
                contentAlignment = Alignment.CenterStart
            ) {
                BackButton(
                    onClick = { /*TODO*/ },
                    icon = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back icon"
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f), // Ocupa o máximo de espaço disponível
                contentAlignment = Alignment.CenterEnd
            ) {
                Image(
                    painter = painterResource(R.drawable.boe_symbol),
                    contentDescription = "Boe símbolo",
                    modifier = Modifier.size(23.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(vertical = 13.dp)
        ){
                Text(
                    text = "Registre-se",
                    fontSize = 33.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Insira seus dados e crie uma nova conta gratuitamente!",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Light
                )
        }
        Column(
            modifier = Modifier
                .padding(vertical = 23.dp)
        ) {
            //nome
            LabeledInput(
                icon = R.drawable.user_icon,
                label = "Nome",
                value = "",
                onValueChange = {}
            )

            //email
            LabeledInput(
                icon = R.drawable.email_icon,
                label = "Email",
                value = "",
                onValueChange = {}
            )

            //Senha
            LabeledInput(
                icon = R.drawable.key_icon,
                label = "Senha",
                value = "",
                onValueChange = {}
            )

            //Confirmar senha
            LabeledInput(
                label = "Confirmar senha",
                value = "",
                onValueChange = {}
            )
        }

        DefaultButton(
            onClick = { /*TODO*/ },
            text = "Registrar-se",
            backgroundColor = PrimaryBlue
        )
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun RegistroScreenPreview() {
    RegistroScreen()
}