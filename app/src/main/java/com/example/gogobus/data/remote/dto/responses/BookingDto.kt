package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.Booking
import com.example.gogobus.domain.model.BookingStatus

data class BookingDto (
    val id: Int,
    val status: String,
    val total_amount: Double,
    val user: Int,
    val trip: Int,
    val passengers: List<PassengerDto>
){
    fun toDomain(): Booking = Booking(
        id = id,
        status = BookingStatus.fromValue(status),
        totalAmount = total_amount,
        user = user,
        trip = trip,
        passengers = passengers.map { it.toDomain() }
    )
}