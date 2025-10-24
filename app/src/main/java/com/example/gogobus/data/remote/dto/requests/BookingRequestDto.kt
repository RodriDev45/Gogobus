package com.example.gogobus.data.remote.dto.requests

data class BookingRequestDto(
    val trip: Int,
    val passengers: List<PassengerRequestDto>
)
