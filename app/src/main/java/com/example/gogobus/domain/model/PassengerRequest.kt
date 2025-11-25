package com.example.gogobus.domain.model

import com.example.gogobus.data.remote.dto.requests.PassengerRequestDto

data class PassengerRequest(
    val fullName: String,
    val dni: String,
    val seatNumber: Int
){
    fun toEntity(): PassengerRequestDto = PassengerRequestDto(
        full_name = fullName,
        dni = dni,
        seat_number = seatNumber
    )
}
