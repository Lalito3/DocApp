package com.example.docapp.ui.screens

import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.docapp.R
import com.example.docapp.data.DataDocSource
import com.example.docapp.model.Medico
import com.example.docapp.viewmodel.AppointmentViewModel
import com.example.docapp.viewmodel.DocAppviewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(medicoNombre: String, navController: NavController){
    val context = LocalContext.current
    var diaSeleccionado by remember { mutableStateOf("") }
    var horaSeleccionada by remember { mutableStateOf("") }
    val citaViewModel: AppointmentViewModel = viewModel()
    val userViewModel: DocAppviewmodel = viewModel()
    val usuarioId = userViewModel.usuarioActivoId ?: 0

    val medico: Medico? = DataDocSource().loadDoctors().find { it.nombre_med == medicoNombre }
    Scaffold (
        topBar = {
            SmallTopAppBar(
                title = {
                    Text("Agendar con $medicoNombre")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(painter = painterResource(id = R.drawable.anterior), contentDescription = "Regresar")
                    }
                }
            )
        }
    ){
        inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = medico?.drawableResId ?: R.drawable.logo),
                contentDescription = medicoNombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val calendar = Calendar.getInstance()
                    android.app.DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            diaSeleccionado = "$dayOfMonth/${month + 1}/$year"
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3066BE))
            ) {
                Text("Seleccionar Fecha")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val calendar = Calendar.getInstance()
                    TimePickerDialog(
                        context,
                        {
                            _, hour, minute ->
                            horaSeleccionada = String.format("%02d:%02d", hour, minute)
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    ).show()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3066BE))
            ) {
                Text("Seleccionar Hora")
            }

            Spacer (modifier = Modifier.height(24.dp))

            Text("Fecha seleccionada: $diaSeleccionado")
            Text("Hora seleccionada: $horaSeleccionada")
            Spacer (modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if(diaSeleccionado.isNotEmpty() && horaSeleccionada.isNotEmpty()){
                        citaViewModel.guardarCita(
                            context = context,
                            usuarioId = usuarioId,
                            medicoNombre = medicoNombre,
                            fecha = diaSeleccionado,
                            hora = horaSeleccionada,
                            onSuccess = {
                                navController.navigate("Principal")
                            }
                        )
                    } else {
                        Toast.makeText(context, "Favor de seleccionar fecha y hora", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3066BE))
            ) {
                Text("Confirmar Cita")
            }
        }
    }

}