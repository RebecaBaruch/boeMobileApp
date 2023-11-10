package com.example.boeteste.classes

import com.google.gson.annotations.SerializedName

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