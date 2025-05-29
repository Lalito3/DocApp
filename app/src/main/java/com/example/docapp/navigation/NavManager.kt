package com.example.docapp.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.docapp.R
import com.example.docapp.data.DataDocSource
import com.example.docapp.ui.InicioScreen
import com.example.docapp.ui.screens.RegistroScreen
import com.example.docapp.ui.screens.MenuScreenList
import com.example.docapp.ui.screens.MedicineScreenList
import com.example.docapp.ui.screens.PrincipalScreen
import com.example.docapp.viewmodel.DocAppviewmodel

import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavManager(viewModel: DocAppviewmodel) {
    val navController = rememberNavController()
    val sesionIniciada by viewModel.sesionIniciada.collectAsState()

    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route
    val showDrawerScaffold = currentRoute in listOf("Menu", "Principal")

    if (showDrawerScaffold) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {


                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "LOGO",
                        modifier = Modifier.size(100.dp,100.dp).align(Alignment.CenterHorizontally), /*Modifica el espacio de la imagen*/
                        alpha = 0.9f,
                        alignment = Alignment.Center)

                    Text("DocApp", modifier = Modifier.padding(16.dp).fillMaxWidth(), textAlign = TextAlign.Center)

                    //HorizontalDivider()

                    NavigationDrawerItem(
                        label = { Text(text = "Principal") },
                        selected = currentRoute == "Principal",
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                navController.navigate("Principal") {
                                    popUpTo("Principal") { inclusive = true }
                                }
                            }
                        }
                    )

                    NavigationDrawerItem(
                        label = { Text(text = "Menu") },
                        selected = currentRoute == "Menu",
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                navController.navigate("Menu") {
                                    popUpTo("Menu") { inclusive = true }
                                }
                            }
                        }
                    )
                }
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(currentRoute ?: "", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavigationContent(navController, viewModel)
                }
            }
        }
    } else {
        NavigationContent(navController, viewModel)
    }
}


@Composable
fun NavigationContent(navController: NavHostController, viewModel: DocAppviewmodel) {
    NavHost(navController = navController, startDestination = "Splash") {
        composable("Splash") {
            val sesionIniciada by viewModel.sesionIniciada.collectAsState()
            LaunchedEffect(sesionIniciada) {
                navController.navigate(if (sesionIniciada) "Inicio" else "Principal") {
                    popUpTo("Splash") { inclusive = true }
                }
            }
        }
        composable("Inicio") {
            InicioScreen(navController = navController, viewModel = viewModel)
        }
        composable("Registro") {
            RegistroScreen(navController = navController)
        }
        composable("Menu") {
            val doctores = DataDocSource().loadDoctors()
            MenuScreenList(medicos = doctores, navController = navController)
        }
        composable("Principal") {
            PrincipalScreen(navController = navController)
        }
        composable("Medicinas"){
            val farmacos= DataMedicineSource().loadMedicines()
            MedicineScreenList(medicinas= farmacos, navController=navController)

        }
        composable ("Appointment/{nombre}"){
            backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: "Desconocido"
            AppointmentScreen(medicoNombre = nombre, navController = navController)
        }
        /*composable("Mis citas"){
            val  appointmentViewModel: AppointViewModel = appointmentModel()
            CitaScreen(navController=navController, viewModel=appointmentViewModel)
        }*/
    }
}
