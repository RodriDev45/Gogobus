package com.example.gogobus.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gogobus.ui.presentation.onboarding.OnboardingScreen
import com.example.gogobus.ui.presentation.home.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Onboarding.route // Iniciar en Onboarding
    ) {
        composable(Destinations.Onboarding.route) {
            OnboardingScreen(
                onFinish = {
                    // Navegar a Home y limpiar el backstack (forma explícita)
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(Destinations.Onboarding.route, true)
                        .build()
                    navController.navigate(Destinations.Home.route, navOptions)
                },
                onRegister = { /* TODO: Handle register navigation */ }
            )
        }
        composable(Destinations.Home.route) {
            HomeScreen()
        }
        composable(
            route = "${Destinations.Detail.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            Box(){
                Text("Detail $id")
            }
        }
    }
}
