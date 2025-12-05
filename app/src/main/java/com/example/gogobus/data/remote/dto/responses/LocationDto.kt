package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.Location

data class LocationDto(
    val id: Int,
    val name: String,
    val terminal: String,
    val address: String,
    val region: String,
    val latitude: String,
    val longitude: String
){
    fun toDomain() = Location(
        id = id,
        name = name,
        terminal = terminal,
        address = address,
        region = region,
        latitude = latitude.toDouble(),
        longitude = longitude.toDouble()
    )
}
