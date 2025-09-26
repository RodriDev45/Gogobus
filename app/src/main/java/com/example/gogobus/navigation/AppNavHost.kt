package com.example.gogobus.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gogobus.ui.presentation.onboarding.OnboardingScreen
import com.example.gogobus.ui.presentation.splash.SplashScreen
import com.example.gogobus.ui.presentation.home.HomeScreen
import com.example.gogobus.presentation.rewards.RewardsScreen
import com.example.gogobus.presentation.tracking.TrackingScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Splash.route
    ) {
        composable(Destinations.Splash.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Destinations.Onboarding.route) {
                        popUpTo(Destinations.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Destinations.Onboarding.route) {
            OnboardingScreen(
                onGetStartedClick = {
                    navController.navigate(Destinations.Rewards.route) {
                        popUpTo(Destinations.Onboarding.route) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Destinations.Rewards.route)
                }
            )
        }
        composable(Destinations.Rewards.route) {
            RewardsScreen(
                onGetStartedClick = {
                    navController.navigate(Destinations.Tracking.route) {
                        popUpTo(Destinations.Rewards.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Destinations.Tracking.route) {
            TrackingScreen(navController)  // ← CORREGIDO
        }
        composable(Destinations.Home.route) {
            HomeScreen(navController as Modifier)  // ← CORREGIDO
        }
        composable(
            route = Destinations.Detail.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            Box(modifier = Modifier) {
                Text("Detail $id")
            }
        }
    }
}