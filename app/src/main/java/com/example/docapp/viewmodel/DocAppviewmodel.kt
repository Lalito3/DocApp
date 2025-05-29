package com.example.docapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class DocAppviewmodel : ViewModel() {
    private val _habilitarPantalla = MutableStateFlow(false)
    private val _contrasenaolvidada = MutableStateFlow(false)

    private val _sesionIniciada = MutableStateFlow(true)
    val sesionIniciada: StateFlow<Boolean> get() = _sesionIniciada

    val habilitarPantalla: StateFlow<Boolean> = _habilitarPantalla.asStateFlow()
    val contrasenaolvidada: StateFlow<Boolean> = _contrasenaolvidada.asStateFlow()
    var usuarioActivoId by mutableStateOf<Int?>(null)



    fun habilitarRegistro() {
        _habilitarPantalla.value = true
    }
    fun PaswordFor(){
        _contrasenaolvidada.value=false
    }
}