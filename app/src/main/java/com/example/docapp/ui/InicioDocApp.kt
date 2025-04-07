package com.example.docapp.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.docapp.ui.theme.DocAppTheme
import com.example.docapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(viewModel: DocAppviewmodel = viewModel()) {
    val habilitarPantalla by viewModel.habilitarPantalla.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.im1),
            contentDescription = "LOGO",
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
            value = "",
            onValueChange = {},
            label = { Text("Correo electrónico") },
            enabled = habilitarPantalla,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Contraseña") },
            enabled = habilitarPantalla,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.habilitarRegistro() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Siguiente")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.PaswordFor() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Olvidaste tu contraseña")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroScreenPreview() {
    DocAppTheme {
        InicioScreen()
    }
}
