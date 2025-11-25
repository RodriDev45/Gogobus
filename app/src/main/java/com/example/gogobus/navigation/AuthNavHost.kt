package com.example.gogobus.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gogobus.ui.components.navigation.BottomNavigationBar
import com.example.gogobus.ui.presentation.login.LoginScreen
import com.example.gogobus.ui.presentation.onboarding.OnboardingScreen
import com.example.gogobus.ui.presentation.register.RegisterScreen

@Composable
fun AuthNavHost(
    navController: NavHostController,
    onFinishOnBoarding: ()->Unit,
    startDestination: String,
    onAuthenticated: () -> Unit // callback cuando el usuario entra a Home
) {
    Scaffold(
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Destinations.Onboarding.route) {
                OnboardingScreen(
                    onFinish = {
                        navController.navigate(Destinations.Login.route)
                        onFinishOnBoarding()
                    },
                    onRegister = { navController.navigate(Destinations.Register.route) }
                )
            }

            composable(Destinations.Login.route) {
                LoginScreen(
                    onLoginSuccess = {
                        onAuthenticated() // Llama al callback y cambia al flujo principal
                    },
                    onRegister = { navController.navigate(Destinations.Register.route) }
                )
            }

            composable(Destinations.Register.route) {
                RegisterScreen(
                    onRegisterSuccess = { onAuthenticated() },
                    onLogin = { navController.navigate(Destinations.Login.route) }
                )
            }
        }
    }

}
