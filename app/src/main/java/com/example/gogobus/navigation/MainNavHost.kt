package com.example.gogobus.navigation

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.gogobus.domain.model.Location
import com.example.gogobus.ui.components.navigation.BottomNavigationBar
import com.example.gogobus.ui.presentation.home.HomeScreen
import com.example.gogobus.ui.presentation.payment.PaymentScreen
import com.example.gogobus.ui.presentation.profile.ProfileScreen
import com.example.gogobus.ui.presentation.search.SearchScreen
import com.example.gogobus.ui.presentation.shared.SearchSharedViewModel
import com.example.gogobus.ui.presentation.summary.SummaryScreen
import com.example.gogobus.ui.presentation.tripdetail.TripDetailScreen
import com.google.gson.Gson
import java.time.LocalDate

@Composable
fun MainNavHost(
    navController: NavHostController
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    // Solo mostrar el BottomNavigation en ciertas rutas
    val bottomNavRoutes = listOf(
        Destinations.Home.route,
        Destinations.Profile.route

    )

    Scaffold(
        bottomBar = {
            if (currentDestination in bottomNavRoutes) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinations.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Destinations.Home.route) {
                HomeScreen(
                    onNavToSearch = {origin, destination, date ->
                        val gson = Gson()
                        val originJson = Uri.encode(gson.toJson(origin))
                        val destinationJson = Uri.encode(gson.toJson(destination))
                        val dateString = date.toString()

                        navController.navigate(
                            "${Destinations.Search.route}/$originJson/$destinationJson/$dateString"
                        )
                    }
                )
            }

            composable(Destinations.Profile.route) {
                ProfileScreen(onNavToHome = {
                    navController.navigate(Destinations.Home.route)
                })
            }

            composable(
                route = "${Destinations.Payment.route}/{bookingId}/{totalAmount}",
                arguments = listOf(
                    navArgument("bookingId") { type = NavType.IntType },
                    navArgument("totalAmount") { type = NavType.FloatType },

                    )
            ) { backStackEntry ->
                val bookingId = backStackEntry.arguments?.getInt("bookingId")
                val totalAmount = backStackEntry.arguments?.getFloat("totalAmount")

                if (bookingId != null && totalAmount != null) {
                    PaymentScreen(
                        bookingId = bookingId,
                        totalAmount = totalAmount.toDouble()
                    )
                }

            }

            composable(
                route = "${Destinations.DetailTrip.route}/{id}",
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id")
                if (id != null) {
                    TripDetailScreen(
                        id = id,
                        onNavigateToSummary = { bookingId, tripId ->

                            navController.navigate(
                                "${Destinations.SummaryTrip.route}/$bookingId/$tripId"
                            )
                        }
                    )
                }
            }

            composable(
                route = "${Destinations.SummaryTrip.route}/{bookingId}/{tripId}",
                arguments = listOf(
                    navArgument("bookingId") { type = NavType.IntType },
                    navArgument("tripId") { type = NavType.IntType },

                )
            ) { backStackEntry ->
                val bookingId = backStackEntry.arguments?.getInt("bookingId")
                val tripId = backStackEntry.arguments?.getInt("tripId")

                if (bookingId != null && tripId != null) {
                    SummaryScreen(
                        bookingId = bookingId,
                        tripId = tripId,
                        onNavToPayment = { bookingId, totalAmount ->
                            navController.navigate(
                                "${Destinations.Payment.route}/$bookingId/$totalAmount"
                            )
                        }
                    )
                }
            }

            composable(
                route = "${Destinations.Search.route}/{origin}/{destination}/{date}"
            ) { backStackEntry ->
                val gson = Gson()
                val originJson = backStackEntry.arguments?.getString("origin")
                val destinationJson = backStackEntry.arguments?.getString("destination")
                val dateString = backStackEntry.arguments?.getString("date")

                val origin = gson.fromJson(Uri.decode(originJson), Location::class.java)
                val destination = gson.fromJson(Uri.decode(destinationJson), Location::class.java)
                val date = LocalDate.parse(dateString)

                SearchScreen(
                    origin = origin,
                    destination = destination,
                    date = date,
                    onBack = {
                        navController.popBackStack()
                    },
                    onNavToTripDetail = {trip ->
                        Log.d("MainNavHost", "trip: $trip")
                        navController.navigate(
                            "${Destinations.DetailTrip.route}/${trip.id}"
                        )
                    }
                )
            }

            composable(
                route = "${Destinations.Detail.route}/{id}",
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id") ?: ""
            }
        }
    }
}
