package com.example.gogobus.navigation

sealed class Destinations(val route: String) {
    object Onboarding: Destinations("onboarding")
    object Home: Destinations("home")
    object Login: Destinations("login")
    object Register: Destinations("register")
    object Search: Destinations("search")
    object DetailTrip: Destinations("detail-trip")
    object SummaryTrip: Destinations("summary-trip")
    object Payment: Destinations("payment")
    object Map: Destinations("map")
    object Profile: Destinations("profile")


    object Detail: Destinations("detail")
}