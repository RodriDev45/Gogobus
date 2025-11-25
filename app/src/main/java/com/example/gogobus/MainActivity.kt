package com.example.gogobus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.gogobus.data.local.OnboardingDataStore
import com.example.gogobus.domain.session.SessionManager
import com.example.gogobus.navigation.AppNavHost
import com.example.gogobus.navigation.Destinations
import com.example.gogobus.ui.theme.GogobusTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var sessionManager: SessionManager
    @Inject lateinit var onboardingDataStore: OnboardingDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1️⃣ Instalar Splash
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2️⃣ Control de Splash
        var keepSplash = true
        splashScreen.setKeepOnScreenCondition { keepSplash }

        setContent {
            GogobusTheme {
                val isAuthenticated by sessionManager.isAuthenticated.collectAsState()
                val hasCompletedOnboarding by produceState<Boolean?>(initialValue = null) {
                    value = onboardingDataStore.hasCompletedOnboarding()
                }

                // 🔹 Ocultamos el splash solo cuando ambos estados ya fueron determinados
                if (isAuthenticated != null && hasCompletedOnboarding != null) {
                    keepSplash = false
                }

                // 🔹 Si aún no sabemos el estado del onboarding, no renderizamos nada
                if (hasCompletedOnboarding == null) {
                    // Muestra un fondo del tema (evita el flash negro)
                    Scaffold(
                    ) { innerPadding ->
                        androidx.compose.foundation.layout.Box(modifier = androidx.compose.ui.Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(androidx.compose.material3.MaterialTheme.colorScheme.background))
                    }

                    return@GogobusTheme
                }

                AppNavHost(
                    isAuthenticated = isAuthenticated,
                    onAuthenticated = {
                        sessionManager.checkAuthentication()
                    },
                    onFinishOnboarding = {
                        lifecycleScope.launch {
                            onboardingDataStore.setOnboardingCompleted(true)
                        }
                    },
                    startDestinationAuth = if (hasCompletedOnboarding == true)
                        Destinations.Login.route
                    else
                        Destinations.Onboarding.route
                )
            }
        }
    }
}
