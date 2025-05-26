package com.example.docapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.docapp.data.DataDocSource
import com.example.docapp.model.Medico
import com.example.docapp.ui.InicioScreen
import com.example.docapp.ui.screens.RegistroScreen
import com.example.docapp.ui.screens.MenuScreen
import com.example.docapp.ui.screens.MenuScreenList
import com.example.docapp.ui.screens.PrincipalScreen
import com.example.docapp.viewmodel.DocAppviewmodel
import com.example.docapp.viewmodel.UserViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import java.lang.reflect.Modifier

@Composable
fun NavManager(docviewModel: DocAppviewmodel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Inicio"){
        composable("Inicio"){
            InicioScreen(navController = navController, viewModel = docviewModel)
        }
        composable("Registro"){
            val userViewModel: UserViewModel = viewModel()
            RegistroScreen(navController = navController, userViewModel = userViewModel)
        }
        composable("Menu"){
            val doctores= DataDocSource().loadDoctors()
            MenuScreenList(medicos = doctores, navController = navController)
        }
        composable("Principal"){
            PrincipalScreen(navController = navController)
        }
    }
}
