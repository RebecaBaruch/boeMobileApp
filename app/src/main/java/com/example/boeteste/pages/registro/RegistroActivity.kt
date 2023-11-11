package com.example.boeteste.pages.registro

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Space
import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.boeteste.ApiService
import com.example.boeteste.NetworkUtils
import com.example.boeteste.R
import com.example.boeteste.classes.UsuarioRegisterRequest
import com.example.boeteste.classes.UsuarioRegisterResponse
import com.example.boeteste.components.backButton.BackButton
import com.example.boeteste.components.defaultButton.DefaultButton
import com.example.boeteste.components.labeledInput.LabeledInput
import com.example.boeteste.pages.registro.ui.theme.BoeTesteTheme
import com.example.boeteste.ui.theme.PrimaryBlue
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoeTesteTheme {
                Surface{
                    //RegistroScreen()
                }
            }
        }
    }
}

fun onRegisterClick(
    coroutine: CoroutineScope,
    name: String,
    email: String,
    password: String,
    context: Context,
    onResponse: (UsuarioRegisterResponse?, Throwable?) -> Unit
) {
    val apiService = NetworkUtils.getRetrofitInstance().create(ApiService::class.java)

    val usuarioRegisterData = UsuarioRegisterRequest(name, email, password)
    val body = Gson().toJson(usuarioRegisterData)
        .toRequestBody("application/json; charset=UTF-8".toMediaType())

    coroutine.launch {
        val call = apiService.cadastrarUsuario(body)

        try {
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val receivedMessage = response.body()?.string()
                        val resBody = Gson().fromJson(receivedMessage, UsuarioRegisterResponse::class.java)

                        onResponse(resBody, null)

                        Toast.makeText(context, "Seu cadastro foi efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                    } else {
                        val errorMessage = response.errorBody()?.string()

                        onResponse(null, RuntimeException(errorMessage))

                        Toast.makeText(context, "Não foi possível cadastrar usuário.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    onResponse(null, t)
                }
            })
        } catch (e: Exception) {
            Log.d("CatchError", e.message.toString())
        }
    }
}

@Composable
fun RegistroScreen(navController: NavHostController) {
    var nome by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var confirmarSenha by rememberSaveable { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val thisContext = LocalContext.current

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
                    onClick = {
                        navController.popBackStack()
                    },
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
                icon = R.drawable.user_gray_icon,
                label = "Nome",
                value = nome,
                onValueChange = { nome = it }
            )

            //email
            LabeledInput(
                icon = R.drawable.email_icon,
                label = "Email",
                value = email,
                onValueChange = { email = it }
            )

            //Senha
            LabeledInput(
                icon = R.drawable.key_icon,
                label = "Senha",
                value = senha,
                onValueChange = { senha = it }
            )

            //Confirmar senha
            LabeledInput(
                label = "Confirmar senha",
                value = confirmarSenha,
                onValueChange = { confirmarSenha = it }
            )
        }

        DefaultButton(
            onClick = {
                      if (senha.equals(confirmarSenha)) {
                          onRegisterClick(
                              coroutineScope,
                              nome,
                              email,
                              senha,
                              thisContext
                          ) {res, error ->
                              if (res !== null) {
                                  Log.d("BoeRegister", res.mensagem)
                              } else {
                                  Log.d("BoeRegisterError", error?.message.toString())
                              }
                          }
                      } else {
                          Toast.makeText(thisContext, "As senhas estão diferentes.", Toast.LENGTH_SHORT).show()
                      }
            },
            text = "Registrar-se",
            backgroundColor = PrimaryBlue
        )
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun RegistroScreenPreview() {
    //RegistroScreen()
}