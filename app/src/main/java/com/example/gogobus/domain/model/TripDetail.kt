package com.example.gogobus.domain.model

import java.time.LocalDateTime

data class TripDetail(
    val id: Int,
    val origin: Location,
    val destination: Location,
    val departureTime: LocalDateTime,
    val arrivalTime: LocalDateTime,
    val price: Double,
    val bus: Bus,
    val occupiedSeats: List<Int>,
    val availableSeats: List<Int>
)
