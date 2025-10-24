package com.example.gogobus.domain.model

data class Bus(
    val id: Int,
    val plate: String,
    val model: String,
    val capacity: Int,
    val layoutConfig: Map<String, List<String>>,
    val seats: Map<String, List<Seat>>
)
