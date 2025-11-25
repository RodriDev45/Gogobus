package com.example.gogobus.domain.model


data class BusLayout(
    val layoutConfig: Map<String, List<String>>,
    val seats: Map<String, List<Seat>>
)