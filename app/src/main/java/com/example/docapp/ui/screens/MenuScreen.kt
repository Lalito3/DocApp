package com.example.docapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.docapp.R
import com.example.docapp.model.Medico
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun MenuScreen(navController: NavController, medico: Medico, modifier: Modifier= Modifier){
    Card (
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .background(color= Color.LightGray, shape= RoundedCornerShape(12.dp)),
    )

        {
        Row(
            modifier=Modifier.padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment=Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id=medico.drawableResId),
                contentDescription = "Médico ${medico.nombre_med}",
                modifier = Modifier.size(170.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.Center)
            {
                Text(
                    text = "${medico.nombre_med}",
                    modifier = Modifier.padding(7.dp),
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 17.sp
                )
                Text(
                    text = " ${medico.especialidad} ",
                    modifier = Modifier.padding(7.dp),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "⭐ 4.3",
                    fontSize = 14.sp,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {navController.navigate("Appointment/${medico.nombre_med}")},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7A52FF))
                ) {
                    Text("Agendar Cita", fontSize = 12.sp)
                }
            }
        }

    }
}

@Composable
fun MenuScreenList(navController: NavController,medicos: List<Medico>, modifier: Modifier= Modifier ){
    LazyColumn(modifier = Modifier) {
        items(medicos){
            medico-> MenuScreen(
                navController = navController,
                medico=medico,
                modifier = Modifier.padding(10.dp)
            )}

    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview(){
    val navController = rememberNavController()
    val sampleMedico = Medico(
        stringResourceId = R.string.doctor1,
        nombre_med = "Dra. Ana Pérez",
        especialidad = "cardiología",
        drawableResId = R.drawable.d2
    )
    MenuScreen(navController = navController, medico = sampleMedico, modifier = Modifier)
}