package com.example.docapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.docapp.R
import com.example.docapp.viewmodel.UserViewModel
import com.example.docapp.model.User

@Composable
fun RegistroScreen(navController: NavController, userViewModel: UserViewModel = viewModel()){
    val scrollState = rememberScrollState()

    var nombre by remember {
        mutableStateOf("")
    }
    var edad by remember {
        mutableStateOf("")
    }
    var estatura by remember {
        mutableStateOf("")
    }
    var peso by remember {
        mutableStateOf("")
    }
    var alergias by remember {
        mutableStateOf("")
    }
    var correo by remember {
        mutableStateOf("")
    }
    var contrasena by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.anterior),
            contentDescription = "Regresar",
            modifier = Modifier
                .align(Alignment.Start)
                .size(40.dp)
                .clickable {
                    navController.navigate("Inicio")
                },
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.registro),
            contentDescription = "REGISTRO",
            modifier = Modifier.size(150.dp,150.dp), /*Modifica el espacio de la imagen*/
            alpha = 0.5f)

        Text(
            text = "Registro de Usuario",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TextField(
            value = nombre,
            onValueChange = {nombre = it},
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = edad,
            onValueChange = {edad = it},
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = estatura,
            onValueChange = {estatura = it},
            label = { Text("Estatura (cm)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = peso,
            onValueChange = {peso = it},
            label = { Text("Peso (kg)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = alergias,
            onValueChange = {alergias = it},
            label = { Text("Alergias") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = correo,
            onValueChange = {correo = it},
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = contrasena,
            onValueChange = {contrasena = it},
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                //navController.navigate("Inicio")
                val user = User (
                    nombre = nombre,
                    edad = edad.toIntOrNull()?: 0,
                    estatura = estatura.toFloatOrNull()?: 0f,
                    peso = peso.toFloatOrNull()?: 0f,
                    alergias = alergias,
                    correo = correo,
                    contrasena = contrasena
                )
                userViewModel.mostrarToast("Registro Exitoso")
                userViewModel.insertarUsuario(user){
                    navController.navigate("Inicio")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3066BE), // azul personalizado
                contentColor = Color.White          // texto blanco
            )
        ) {
            Text("Registrarse")
        }
    }
}