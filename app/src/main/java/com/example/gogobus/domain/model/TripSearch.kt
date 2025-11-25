package com.example.gogobus.domain.model

import java.time.LocalDateTime

data class TripSearch(
    val id: Int,
    val plateBus: String,
    val origin: Location,
    val destination: Location,
    val departureTime: LocalDateTime,
    val arrivalTime: LocalDateTime,
    val price: Double,
    val seatsAvailable: Int
)
