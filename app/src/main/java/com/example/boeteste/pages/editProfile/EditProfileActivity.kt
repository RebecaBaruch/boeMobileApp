package com.example.boeteste.pages.editProfile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boeteste.R
import com.example.boeteste.components.backButton.BackButton
import com.example.boeteste.components.labeledInput.LabeledInput
import com.example.boeteste.pages.editProfile.ui.theme.BoeTesteTheme

class EditProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoeTesteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EditProfileScreen()
                }
            }
        }
    }
}

@Composable
fun EditProfileScreen() {
    Column(
        modifier = Modifier
            .padding(
                vertical = 33.dp,
                horizontal = 33.dp
            )
            .fillMaxHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 33.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(
                onClick = { /*TODO*/ },
                icon = Icons.Default.ArrowBack,
                contentDescription = "Arrow back icon"
            )
        }

        Spacer(modifier = Modifier.height(33.dp))

        Column {
            Text(
                text = "Editar perfil",
                fontSize = 33.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text =  "Edite as informações pessoais da sua conta.",
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(13.dp))

        Column(
            modifier = Modifier
                .padding(vertical = 23.dp)
        ) {
            //nome
            LabeledInput(
                icon = R.drawable.user_gray_icon,
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

        }

        Spacer(modifier = Modifier.height(13.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Alterar a senha",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(13.dp))

            Image(
                painter = painterResource(R.drawable.key_black_icon),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(13.dp))

        var password by rememberSaveable { mutableStateOf("") }

        val visualPassword = buildAnnotatedString {
            repeat(password.length) {
                append("•")
            }
        }

        TextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            placeholder = { Text(visualPassword) },
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFF282FD9), RoundedCornerShape(13.dp))
                .padding(vertical = 0.dp, horizontal = 37.dp)
                .background(color = Color.Transparent)
        )

    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen()
}