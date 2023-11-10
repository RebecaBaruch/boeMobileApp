package com.example.boeteste.pages.editProfile

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.boeteste.ApiService
import com.example.boeteste.NetworkUtils
import retrofit2.Callback
import com.example.boeteste.R
import com.example.boeteste.classes.UsuarioUpdateRequest
import com.example.boeteste.classes.UsuarioUpdateResponse
import com.example.boeteste.components.backButton.BackButton
import com.example.boeteste.components.labeledInput.LabeledInput
import com.example.boeteste.pages.editProfile.components.ExcludeAccountButton
import com.example.boeteste.pages.editProfile.ui.theme.BoeTesteTheme
import com.example.boeteste.ui.theme.PatternGray
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

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
                    //EditProfileScreen()
                }
            }
        }
    }
}

fun onEditClick(coroutine: CoroutineScope, name: String, email: String, password: String, context: Context, onResponse: (UsuarioUpdateResponse?, Throwable?) -> Unit) {
    val apiService = NetworkUtils.getRetrofitInstance().create(ApiService::class.java)

    val usuarioUpdatedData = UsuarioUpdateRequest(name, email, password)
    val body = Gson().toJson(usuarioUpdatedData)
        .toRequestBody("application/json; charset=UTF-8".toMediaType())

    coroutine.launch {
        val call = apiService.atualizarDadosUsuario("654b6c384e37cb2cd3604b4a", body)

        try {
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        val receivedMessage = response.body()?.string()
                        val resBody = Gson().fromJson(receivedMessage, UsuarioUpdateResponse::class.java)

                        onResponse(resBody, null)

                        Toast.makeText(context, "Dados atualizados!", Toast.LENGTH_SHORT).show()
                    } else {
                        val errorMessage = response.errorBody()?.string()
                        onResponse(null, RuntimeException(errorMessage))

                        Toast.makeText(context, "Não foi possível atualizar seus dados.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    onResponse(null, t)
                }
            })
        } catch (e: Exception) {
            Log.e("CatchError", e.message.toString())
        }
    }
}

@Composable
fun EditProfileScreen(
    navController: NavHostController,
    onLogout: () -> Unit
) {
    var nome by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val handler = remember { Handler(Looper.getMainLooper()) }
    val thisContext = LocalContext.current

    val onApiCall: (String) -> Unit = {input ->
        coroutineScope.launch {
            onEditClick(coroutineScope, nome, email, password, thisContext) {response, error ->
                if (response !== null) {
                    Log.d("BoeEdit", response.mensagem)
                } else {
                    Log.d("BoeEditError", error?.message.toString())
                }
            }
        }
    }

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
                onClick = {
                    navController.popBackStack()
                },
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
                value = nome,
                onValueChange = {
                    nome = it

                    handler.removeCallbacksAndMessages(null)
                    handler.postDelayed({onApiCall(it)}, 5000)
                }
            )

            //email
            LabeledInput(
                icon = R.drawable.email_icon,
                label = "Email",
                value = email,
                onValueChange = {
                    email = it

                    handler.removeCallbacksAndMessages(null)
                    handler.postDelayed({onApiCall(it)}, 5000)
                }
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

        val visualPassword = buildAnnotatedString {
            repeat(password.length) {
                append("•")
            }
        }

        TextField(
            value = password,
            onValueChange = {
                            password = it

                handler.removeCallbacksAndMessages(null)
                handler.postDelayed({onApiCall(it)}, 5000)
            },
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

        Spacer(modifier = Modifier.height(53.dp))

        Divider(
            color = PatternGray,
            thickness = 0.5.dp,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(53.dp))

        ExcludeAccountButton {
            onLogout()
        }
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun EditProfileScreenPreview() {
    //EditProfileScreen()
}