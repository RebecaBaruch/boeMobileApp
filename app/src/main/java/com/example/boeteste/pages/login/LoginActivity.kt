package com.example.boeteste.pages.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.boeteste.ApiService
import com.example.boeteste.NetworkUtils
import com.example.boeteste.R
import com.example.boeteste.classes.UsuarioLoginRequest
import com.example.boeteste.classes.UsuarioLoginResponse
import com.example.boeteste.components.defaultButton.DefaultButton
import com.example.boeteste.components.labeledInput.LabeledInput
import com.example.boeteste.components.linkText.LinkText
import com.example.boeteste.components.mixedTitle.MixedTitle
import com.example.boeteste.ui.theme.PatternGray
import com.example.boeteste.ui.theme.PrimaryBlue
import com.example.boeteste.utils.GlobalState
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    //LoginScreen()
                }
            }
        }
    }
}

fun onLoginClick(
    coroutine: CoroutineScope,
    email: String,
    senha: String,
    context: Context,
    onLoginSuccess: () -> Unit,
    onResponse: (UsuarioLoginResponse?, Throwable?) -> Unit) {
    val apiService = NetworkUtils.getRetrofitInstance().create(ApiService::class.java)

    val usuarioLoginRequest = UsuarioLoginRequest(email, senha)
    val body = Gson().toJson(usuarioLoginRequest)
        .toRequestBody("application/json; charset=UTF-8".toMediaType())

    coroutine.launch {
        val call = apiService.logarUsuario(body)

        try {
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val receivedMessage = response.body()?.string()
                        val resBody = Gson().fromJson(receivedMessage, UsuarioLoginResponse::class.java)

                        GlobalState.dadosCompartilhados.value = resBody.dadosUsuario.id
                        onResponse(resBody, null)
                        onLoginSuccess()

                        Toast.makeText(context, resBody.mensagem, Toast.LENGTH_SHORT).show()
                    } else {
                        val errorMessage = response.errorBody()?.string()

                        onResponse(null, RuntimeException(errorMessage))

                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    onResponse(null, t)

                    Toast.makeText(context, "Não foi possível conectar-se... Aguarde!", Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
            Log.d("CatchError", e.message.toString())
        }
    }
}

@Composable
fun LoginScreen(
    navController: NavHostController,
    onLoginSuccess: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val thisContext = LocalContext.current

    Column(
        modifier = Modifier
            .padding(33.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {

        Column(modifier = Modifier
            .padding(vertical = 23.dp)
            .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(R.drawable.boe_symbol),
                    contentDescription = "Boe Símbolo",
                    tint = PrimaryBlue,
                    modifier = Modifier
                        .size(43.dp)
                )
            }

            Spacer(modifier = Modifier.height(23.dp))

            MixedTitle(
                parteNegrito = "Bem-vindo",
                parteLeve = "de volta",
                boldFirst = true,
                fontSize = 43,
                quebrarTexto = true,
                modifier = Modifier
            )
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            LabeledInput(
                label = "Email",
                value = email,
                onValueChange = {email = it}
            )

            LabeledInput(
                label = "Senha",
                value = password,
                onValueChange = {password = it}
            )

            Spacer(modifier = Modifier.height(13.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                LinkText(
                    linkText = "Esqueci minha senha",
                    modifier = Modifier
                )
            }
        }

        Spacer(modifier = Modifier.height(23.dp))

        DefaultButton(
            onClick = {
                onLoginClick(
                    coroutineScope,
                    email,
                    password,
                    thisContext,
                    onLoginSuccess
                ) {res, error ->
                    if (res !== null) {
                        Log.d("BoeLogin", res.mensagem)
                    } else {
                        Log.d("BoeLoginError", error?.message.toString())
                    }
                }
            },
            text = "Log in",
            backgroundColor = PrimaryBlue
        )

        Spacer(modifier = Modifier.height(23.dp))

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

            LinkText(
                linkText = "Registre-se",
                modifier = Modifier
                    .clickable {
                        navController.navigate("register")
                    }
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun LoginPreview(){
    //LoginScreen()
}