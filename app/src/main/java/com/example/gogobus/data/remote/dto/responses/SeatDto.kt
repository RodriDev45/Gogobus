package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.Seat

data class SeatDto(
    val number: Int,
    val column: Int,
    val position: String
){
    fun toDomain(): Seat = Seat(
            number = number,
            column = column,
            position = position
        )
}
