package com.example.gogobus.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
//import com.example.gogobus.presentation.home.HomeScreen
//import com.example.gogobus.presentation.splash.SplashScreen
import com.example.gogobus.ui.presentation.home.HomeScreen
import com.example.gogobus.ui.presentation.splash.SplashScreen

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
                    navController.navigate(Destinations.Home.route) {
                        popUpTo(Destinations.Splash.route) { inclusive = true }
                    }
                }
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
