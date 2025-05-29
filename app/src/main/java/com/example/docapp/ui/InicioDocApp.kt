package com.example.docapp.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
//import java.lang.reflect.Modifier

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.docapp.viewmodel.DocAppviewmodel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.docapp.ui.theme.DocAppTheme
import com.example.docapp.R
import com.example.docapp.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(viewModel: DocAppviewmodel = viewModel(), navController: NavController) {
    val habilitarPantalla by viewModel.habilitarPantalla.collectAsState()
    val userViewModel: UserViewModel = viewModel()
    val context = LocalContext.current
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.im2),
            contentDescription = "portada",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            alpha = 0.5f
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "LOGO",
            modifier = Modifier.size(150.dp,150.dp), /*Modifica el espacio de la imagen*/
            alpha = 0.5f)

        Text(
            text = "Bienvenidos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = correo,
            onValueChange = {correo = it},
            label = { Text("Correo electrónico") },
            //enabled = habilitarPantalla,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        PasswordTextField(contrasena = contrasena, onPasswordChange = {contrasena = it})
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                userViewModel.validarCredenciales(correo, contrasena){ usuario ->
                    if (usuario != null){
                        viewModel.usuarioActivoId = usuario.id
                        viewModel.usuarioActivoNombre = usuario.nombre
                        navController.navigate("Principal")
                    } else {
                        Toast.makeText(
                            context,
                            "Credenciales incorrectas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3066BE), // azul personalizado
                contentColor = Color.White          // texto blanco
            )
        ) {
            Text("Siguiente")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text= "¿No tienes cuenta? Regístrate aquí",
            color = Color(0xFF3F51B5),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable { navController.navigate("Registro") }
        )
    }
}

@Composable
fun PasswordTextField(contrasena: String, onPasswordChange: (String) -> Unit){
    var contrasenaVisible by remember { mutableStateOf(false) }
    TextField(
        value = contrasena,
        onValueChange = onPasswordChange,
        label = { Text("Contraseña")},
        singleLine = true,
        visualTransformation = if (contrasenaVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if(contrasenaVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            IconButton(onClick = {contrasenaVisible = !contrasenaVisible}) {
                Icon(imageVector = image, contentDescription = if(contrasenaVisible) "Ocultar" else "Mostrar")
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        modifier = Modifier.fillMaxWidth()
    )
}