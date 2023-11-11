package com.example.boeteste.pages.home

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

data class UsuarioDataMenuResponse (
    val userName: String,
    val registeredCases: Int,
    val positiveCases: Int,
)

class MenuViewModel : ViewModel() {
    var userName: String by mutableStateOf("")
    var registeredCases: Int by mutableStateOf(0)
    var positiveCases: Int by mutableStateOf(0)
}

class MenuResponse {
    val apiService = NetworkUtils.getApiService()
    fun exibirMenuDadosUsuario(id: String, onResponse: (UsuarioDataMenuResponse?, Throwable?) -> Unit) {
        apiService.exibirDadosUsuarioMenu(id).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val receivedMessage = response.body()?.string()
                    val resBody = Gson().fromJson(receivedMessage, UsuarioDataMenuResponse::class.java)

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