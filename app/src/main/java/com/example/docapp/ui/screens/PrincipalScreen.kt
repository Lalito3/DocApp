package com.example.docapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.docapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(navController : NavController, modifier: Modifier = Modifier){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = { TopAppTitle(scrollBehavior = scrollBehavior) }, // Barra horizontal para el título
    ){
        innerPadding -> // ← Este "innerPadding" es importante para
        // Se agrega un Surface para dejar un lienzo/canva/surface disponible para albergar las vistas de la app.
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Como la vista de HomeScreen tiene objetos dinámicos se necesita un ViewModel para gestionar los cambios de datos y así.
            Column {
                // Portada por encima de la pantalla principal
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Text(
                        text = "¡Bienvenido!",
                        fontSize = 30.sp, // Tamaño grande
                        fontWeight = FontWeight.Bold, // Opcional: que se vea más fuerte
                        modifier = Modifier.padding(vertical = 25.dp).padding(top = 10.dp)
                            )
                    }
                    item {
                        Row {
                            CardMenu(
                                text = "Doctores",
                                imageId = R.drawable.d2,
                                onClick = { navController.navigate("Menu") },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(10.dp)
                            )
                            CardMenu(
                                text = "Mis citas",
                                imageId = R.drawable.logo,
                                onClick = { println("Mis citas")},
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(10.dp)
                            )
                        }
                    }

                    item {
                        Row {
                            CardMenu(
                                text = "Resultados",
                                imageId = R.drawable.m3,
                                onClick = { println("Resultados") },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(10.dp)
                            )
                            CardMenu(
                                text = "Medicinas",
                                imageId = R.drawable.im1,
                                onClick = { println("Medicinas")},
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(10.dp)
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun CardMenu(
    text : String,
    imageId : Int,
    onClick: () -> Unit,
    modifier: Modifier
){
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier.height(150.dp),
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxSize()){

            Image(
                painter = painterResource(id = imageId),
                contentDescription = "portada",
                contentScale = ContentScale.Crop,
                alpha = 0.5f,
                modifier = Modifier
                    .fillMaxSize()
                )

            Text(
                text = text,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}


// # # # ESTRUCTURA COMPOSABLE PARA EL TÍTULO DE LA APP # # #
// Se puede modificar para
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppTitle(scrollBehavior: TopAppBarScrollBehavior, modifier:Modifier = Modifier){
    Image(
        painter = painterResource(id = R.drawable.im2),
        contentDescription = "portada",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        //.aspectRatio(16f / 9f),
        alpha = 0.5f,

    )
}

@Preview(showBackground = true)
@Composable
fun PrincipalScreenPreview(){
    val navController = rememberNavController()
    PrincipalScreen(navController = navController, modifier = Modifier)
}