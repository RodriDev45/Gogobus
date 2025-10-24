package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.Bus


class BusDto (
    val id: Int,
    val placa: String,
    val modelo: String,
    val capacidad: Int,
    val layout_config: Map<String, List<String>>,
    val seats: Map<String, List<SeatDto>>
){
    fun toDomain(): Bus = Bus(
        id = id,
        plate = placa,
        model = modelo,
        capacity = capacidad,
        layoutConfig = layout_config,
        seats = seats.mapValues { it.value.map { it.toDomain() } }
    )
}