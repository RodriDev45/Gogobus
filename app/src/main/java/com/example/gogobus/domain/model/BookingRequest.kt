package com.example.gogobus.domain.model

import com.example.gogobus.data.remote.dto.requests.BookingRequestDto

data class BookingRequest(
    val trip: Int,
    val passengers: List<PassengerRequest>
){
    fun toEntity(): BookingRequestDto = BookingRequestDto(
        trip = trip,
        passengers = passengers.map { it.toEntity() }
    )
}
