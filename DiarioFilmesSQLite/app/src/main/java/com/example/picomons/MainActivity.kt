package com.example.picomons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.picomons.ui.FilmeScreen
import com.example.picomons.viewmodel.FilmeViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val viewModel: FilmeViewModel = viewModel()
            FilmeScreen(viewModel = viewModel)
        }
    }
}
