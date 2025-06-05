package edu.ucne.marcosduran_p1_ap2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.marcosduran_p1_ap2.presentation.navigation.TareaNavHost
import edu.ucne.marcosduran_p1_ap2.ui.theme.MarcosDuran_P1_Ap2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarcosDuran_P1_Ap2Theme {
                val navHost = rememberNavController()
                TareaNavHost(navHost)
            }
        }
    }
}

//tarea id, descripcion, tiempo