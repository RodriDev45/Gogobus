package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.TripSearch
import com.example.gogobus.domain.util.DateUtils

data class TripSearchDto(
    val id: Int,
    val bus: BusSampleDto,
    val origin: LocationDto,
    val destination: LocationDto,
    val departure_time: String,
    val arrival_time: String,
    val price: String,
    val available_seats_count: Int
){
    fun toDomain() = TripSearch(
        id = id,
        plateBus = bus.placa,
        origin = origin.toDomain(),
        destination = destination.toDomain(),
        departureTime = DateUtils.formatToLocalDateTime(departure_time),
        arrivalTime = DateUtils.formatToLocalDateTime(arrival_time),
        price = price.toDouble(),
        seatsAvailable = available_seats_count
    )
}
