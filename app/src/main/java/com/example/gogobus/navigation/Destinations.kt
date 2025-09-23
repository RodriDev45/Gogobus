package com.example.gogobus.navigation

sealed class Destinations(val route: String) {
    object Home: Destinations("home")
    object Detail: Destinations("detail")
}