package com.example.gogobus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.gogobus.navigation.AppNavHost
import com.example.gogobus.ui.theme.GogobusTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        // 1. Habilitar Edge-to-Edge
        enableEdgeToEdge()

        setContent {
            GogobusTheme {
                // 2. Llamar directamente al NavHost. El Scaffold se gestiona en cada pantalla.
                AppNavHost()
            }
        }
    }
}
