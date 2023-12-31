package com.example.boeteste.classes

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