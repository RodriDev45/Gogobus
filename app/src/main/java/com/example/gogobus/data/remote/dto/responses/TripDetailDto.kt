package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.TripDetail
import com.example.gogobus.domain.util.DateUtils
import java.time.LocalDateTime

data class TripDetailDto(
    val id: Int,
    val origin: LocationDto,
    val destination: LocationDto,
    val departure_time: String,
    val arrival_time: String,
    val price: String,
    val bus: BusDto,
    val occupied_seats: List<Int>,
    val available_seats: List<Int>
){
    fun toDomain(): TripDetail = TripDetail(
        id = id,
        origin = origin.toDomain(),
        destination = destination.toDomain(),
        departureTime = DateUtils.formatToLocalDateTime(departure_time),
        arrivalTime = DateUtils.formatToLocalDateTime(arrival_time),
        price = price.toDouble(),
        bus = bus.toDomain(),
        occupiedSeats = occupied_seats,
        availableSeats = available_seats
    )
}
