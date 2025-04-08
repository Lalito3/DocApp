package com.example.docapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.docapp.ui.InicioScreen
import com.example.docapp.ui.screens.RegistroScreen
import com.example.docapp.viewmodel.DocAppviewmodel

@Composable
fun NavManager(viewModel: DocAppviewmodel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Inicio"){
        composable("Inicio"){
            InicioScreen(navController = navController, viewModel = viewModel)
        }
        composable("Registro"){
            RegistroScreen(navController = navController)
        }
    }
}