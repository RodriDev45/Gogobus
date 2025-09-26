package com.example.gogobus.navigation

sealed class Destinations(val route: String) {
    object Splash: Destinations("splash")
    object Home: Destinations("home")
    object Detail: Destinations("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}
