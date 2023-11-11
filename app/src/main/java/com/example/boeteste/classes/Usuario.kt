package com.example.boeteste.classes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.annotations.SerializedName

data class UsuarioRegisterRequest (
    @SerializedName("name") val nome: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val senha: String,
)

data class UsuarioRegisterResponse (
    val mensagem: String,
    @SerializedName("idUsuario") val id: String,
    val status: Int,
)

data class UsuarioLoginRequest (
    @SerializedName("email") val email: String,
    @SerializedName("password") val senha: String,
)

data class UsuarioLoginDataResponse (
    val id: String,
    val nome: String,
    val email: String,
)

data class UsuarioLoginResponse (
    val mensagem: String,
    val status: Int,
    @SerializedName("userData") val dadosUsuario: UsuarioLoginDataResponse,
)

class UsuarioMenuViewModelResponse : ViewModel() {
    val userName: String by mutableStateOf("")
    val registeredCases: Int by mutableStateOf(0)
    val positiveCases: Int by mutableStateOf(0)
}

data class UsuarioUpdateShowDataGet (
    val id: String?,
    @SerializedName("name") val nome: String?,
    val email: String?,
    @SerializedName("password") val senha: String?,
    val status: Int?,
    val message: String?,
)

data class UsuarioUpdateRequest (
    @SerializedName("name") val nome: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val senha: String,
)

data class UsuarioUpdateResponse (
    @SerializedName("message") val mensagem: String,
    val status: Int,
    @SerializedName("description") val descricaoMensagem: String?,
)