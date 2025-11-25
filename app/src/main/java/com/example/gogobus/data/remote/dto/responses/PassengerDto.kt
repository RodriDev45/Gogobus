package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.Passenger

data class PassengerDto(
    val id: Int,
    val full_name: String,
    val dni: String,
    val seat_number: Int,
){
    fun toDomain(): Passenger = Passenger(
        id = id,
        fullName = full_name,
        dni = dni,
        seatNumber = seat_number
    )
}
