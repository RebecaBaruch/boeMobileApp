package com.example.boeteste.pages.editProfile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.boeteste.NetworkUtils
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class UsuarioDataEditResponse (
    val id: String?,
    var name: String?,
    var email: String?,
    var password: String?,
    val status: Int?,
    val message: String?,
)

class EditProfileViewModel : ViewModel() {
    val id: String? by mutableStateOf("")
    var name: String? by mutableStateOf("")
    var email: String? by mutableStateOf("")
    var password: String? by mutableStateOf("")
    val status: Int? by mutableStateOf(0)
    val message: String? by mutableStateOf("")
}

class EditResponse {
    val apiService = NetworkUtils.getApiService()
    fun exibirAtualizarDadosUsuario(id: String, onResponse: (UsuarioDataEditResponse?, Throwable?) -> Unit) {
        apiService.exibirDadosUsuarioAtualizar(id).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val receivedMessage = response.body()?.string()
                    val resBody = Gson().fromJson(receivedMessage, UsuarioDataEditResponse::class.java)

                    onResponse(resBody, null)
                } else {
                    val errorMessage = response.errorBody()?.string()

                    onResponse(null, RuntimeException(errorMessage))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                onResponse(null, t)
            }
        })
    }
}