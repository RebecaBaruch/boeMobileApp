package com.example.boeteste.pages.profileAccount

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boeteste.R
import com.example.boeteste.pages.profileAccount.components.ExitButton
import com.example.boeteste.pages.profileAccount.ui.theme.BoeTesteTheme

class ProfileAccountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoeTesteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileAccountScreen()
                }
            }
        }
    }
}

@Composable
fun ProfileAccountScreen() {
    Column(
        modifier = Modifier
            .padding(33.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.user_icon),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(33.dp))
            
            Column {
                Text(
                    text = "Sua conta",
                    fontSize = 33.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text =  "Veja as informações sobre a sua conta e edite as suas informações pessoais e as configurações.",
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(33.dp))

            Row {
                Image(
                    painter = painterResource(R.drawable.edit_icon),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(23.dp))

                Text(
                    text = "Editar perfil",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(33.dp))

            Row {
                Image(
                    painter = painterResource(R.drawable.settings_icon),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(23.dp))

                Text(
                    text = "Configurações",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(33.dp))

            Row {
                Image(
                    painter = painterResource(R.drawable.help_circle_icon),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(23.dp))

                Text(
                    text = "Suporte",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(33.dp))

        ExitButton {
            /**/
        }
    }
}

@Preview(showBackground = true, widthDp = 424)
@Composable
fun ProfileAccountScreenPreview() {
    BoeTesteTheme {
        ProfileAccountScreen()
    }
}