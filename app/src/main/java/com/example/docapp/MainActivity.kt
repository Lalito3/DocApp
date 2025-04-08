package com.example.docapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.docapp.navigation.NavManager
import com.example.docapp.ui.InicioScreen
import com.example.docapp.ui.theme.DocAppTheme
import com.example.docapp.viewmodel.DocAppviewmodel

class MainActivity : ComponentActivity() {
    private val viewModel: DocAppviewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DocAppTheme {
                NavManager(viewModel)
            }
        }
    }
}


