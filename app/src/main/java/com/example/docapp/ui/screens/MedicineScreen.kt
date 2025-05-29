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
import com.example.docapp.model.Medicina
import androidx.compose.foundation.lazy.items
import androidx.navigation.compose.rememberNavController

@Composable
fun MedicineScreen(navController: NavController, medicina: Medicina, modifier: Modifier= Modifier){
    Card (modifier = Modifier
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
                painter = painterResource(id=medicina.drawableResId),
                contentDescription = "Médico ${medicina.nombre_med}",
                modifier = Modifier.size(170.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.Center)
            {
                Text(
                    text = "${medicina.nombre_med}",
                    modifier = Modifier.padding(7.dp),
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 17.sp
                )
                Text(
                    text = " ${medicina.costo} ",
                    modifier = Modifier.padding(7.dp),
                    fontSize = 16.sp
                )
            }
        }

    }
}

@Composable
fun MedicineScreenList(navController: NavController,medicinas: List<Medicina>, modifier: Modifier= Modifier ){
    LazyColumn(modifier = Modifier) {
        items(medicinas){
                medicina-> MedicineScreen(
            navController = navController,
            medicina=medicina,
            modifier = Modifier.padding(10.dp)
        )}

    }
}