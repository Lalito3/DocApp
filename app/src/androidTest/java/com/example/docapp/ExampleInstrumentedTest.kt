package com.example.docapp

import androidx.activity.viewModels
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.ViewModel
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.docapp.navigation.NavManager
import com.example.docapp.ui.InicioScreen
import com.example.docapp.ui.theme.DocAppTheme
import com.example.docapp.viewmodel.DocAppviewmodel
import okhttp3.internal.wait

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.text.NumberFormat

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class CalcularMontoUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calcularMonto_20L_24_95(){
        composeTestRule.setContent {
            val viewModel: DocAppviewmodel = DocAppviewmodel()
            DocAppTheme {
                NavManager(viewModel)
            }
        }

        composeTestRule.onNodeWithText("¿No tienes cuenta? Regístrate aquí").performClick()
        composeTestRule.waitUntil(1_000){
            composeTestRule.onNodeWithText("Nombre completo").isDisplayed()
            composeTestRule.onNodeWithText("Edad").isDisplayed()
            composeTestRule.onNodeWithText("Estatura", substring = true).isDisplayed()
            composeTestRule.onNodeWithText("Peso", substring = true).isDisplayed()
            composeTestRule.onNodeWithText("Alergias", substring = true).isDisplayed()
            composeTestRule.onNodeWithText("Correo electrónico").isDisplayed()
            composeTestRule.onNodeWithText("Contraseña").isDisplayed()
        }
        composeTestRule.onNodeWithText("Nombre completo").performTextInput("Angel Molina")
        composeTestRule.onNodeWithText("Edad").performTextInput("25")
        composeTestRule.onNodeWithText("Estatura", substring = true).performTextInput("170")
        composeTestRule.onNodeWithText("Peso", substring = true).performTextInput("85")
        composeTestRule.onNodeWithText("Alergias", substring = true).performTextInput("Ninguna")
        composeTestRule.onNodeWithText("Correo electrónico").performTextInput("angmolgo@gmail.com")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("123")

        composeTestRule.onNodeWithText("Registrarse").performClick()
        composeTestRule.waitUntil(5000_000){
            composeTestRule.onNodeWithText("REGISTRO").isNotDisplayed()
            composeTestRule.onNodeWithText("Bienvenidos").isDisplayed()
        }
        composeTestRule.waitUntil(5000_000){
            composeTestRule.onNodeWithText("Correo electrónico").isDisplayed()
            composeTestRule.onNodeWithText("Contraseña").isDisplayed()
            composeTestRule.onNodeWithText("Siguiente").isDisplayed()
        }
        composeTestRule.onNodeWithText("Correo electrónico").performTextInput("angmolgo@gmail.com")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("123")
        composeTestRule.onNodeWithText("Siguiente").performClick()

        //composeTestRule.onNodeWithText("Ingresa precio por litro de Gasolina")
        //    .performTextInput("24.95")


        //val montoEsperado = NumberFormat.getCurrencyInstance().format(499.0)

        //composeTestRule.onNodeWithText("Monto Total: $montoEsperado")
        //    .assertExists("No se encontró nungún nodo (TextField) con el texto ingresado")
    }



}