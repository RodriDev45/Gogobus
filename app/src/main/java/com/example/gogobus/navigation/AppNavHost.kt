package com.example.gogobus.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.*
import com.example.gogobus.ui.components.navigation.BottomNavigationBar
import com.example.gogobus.ui.presentation.auth.AuthViewModel
import com.example.gogobus.ui.presentation.onboarding.OnboardingScreen
import com.example.gogobus.ui.presentation.home.HomeScreen
import com.example.gogobus.ui.presentation.login.LoginScreen
import com.example.gogobus.ui.presentation.register.RegisterScreen

@Composable
fun AppNavHost(
    isAuthenticated: Boolean?,
    onAuthenticated: ()->Unit,
    onFinishOnboarding: ()->Unit,

    startDestinationAuth: String = Destinations.Onboarding.route,
    navController: NavHostController = rememberNavController()
) {

    when (isAuthenticated) {
        null -> {
            // ❌ No mostramos nada aquí, la splash está activa
        }
        true -> {
            val mainNavController = rememberNavController()
            MainNavHost(navController = mainNavController)
        }
        false -> {
            val authNavController = rememberNavController()
            AuthNavHost(
                navController = authNavController,
                onAuthenticated = onAuthenticated,
                startDestination = startDestinationAuth,
                onFinishOnBoarding = onFinishOnboarding
            )
        }
    }
}
