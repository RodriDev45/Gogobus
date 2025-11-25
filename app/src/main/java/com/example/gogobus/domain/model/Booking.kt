package com.example.gogobus.domain.model

data class Booking(
    val id: Int,
    val status: BookingStatus,
    val totalAmount: Double,
    val user: Int,
    val trip: Int,
    val passengers: List<Passenger>
)