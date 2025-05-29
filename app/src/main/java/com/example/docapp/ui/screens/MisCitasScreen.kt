package com.example.docapp.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.docapp.model.Cita
import com.example.docapp.viewmodel.AppointmentViewModel
import com.example.docapp.viewmodel.DocAppviewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisCitasScreen(
    viewModel: AppointmentViewModel = viewModel(),
    docAppViewModel: DocAppviewmodel,
    context: Context = LocalContext.current,
    navController: NavController
) {
    var citas by remember { mutableStateOf<List<Cita>>(emptyList()) }
    val usuarioId = docAppViewModel.usuarioActivoId

    LaunchedEffect(usuarioId) {
        usuarioId?.let {
            viewModel.obtenerCitasDeUsuario(context, it) { citasObtenidas ->
                citas = citasObtenidas
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Citas") },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF3066BE),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(citas) { cita ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Doctor: ${cita.medicoNombre}", fontWeight = FontWeight.Bold)
                        Text("Fecha: ${cita.fecha}")
                        Text("Hora: ${cita.hora}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                viewModel.eliminarCita(context, cita){
                                    viewModel.obtenerCitasDeUsuario(context, usuarioId!!){citasActualizadas ->
                                        citas = citasActualizadas
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Cancelar cita", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}