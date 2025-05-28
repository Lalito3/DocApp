package com.example.docapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.docapp.model.Cita
import com.example.docapp.room.DocAppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppointmentViewModel: ViewModel() {
    fun guardarCita(context: Context, usuarioId: Int, medicoNombre: String, fecha: String, hora: String, onSuccess: () -> Unit ){
        viewModelScope.launch(Dispatchers.IO) {
            val db = DocAppDataBase.getDataBAse(context)
            val cita = Cita(
                usuarioId = usuarioId,
                medicoNombre = medicoNombre,
                fecha = fecha,
                hora = hora
            )
            db.citaDao().insertCita(cita)
            launch(Dispatchers.Main){
                Toast.makeText(context, "Cita guardada con éxito", Toast.LENGTH_SHORT).show()
                onSuccess()
            }
        }
    }
}