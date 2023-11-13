package com.example.boeteste.utils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GlobalViewModel: ViewModel() {
    private var dadosCompartilhados: String = ""

    fun getDadosCompartilhados(): String {
        return dadosCompartilhados
    }

    fun setDadosCompartilhados(dado: String) {
        this.dadosCompartilhados = dado
    }
}

object GlobalState {
    val dadosCompartilhados = mutableStateOf<String>("")
}