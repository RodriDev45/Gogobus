package com.example.gogobus.domain.model

data class Location(
    val id: Int,
    val name: String,
    val terminal: String,
    val address: String,
    val region: String,
    val latitude: Double,
    val longitude: Double

)
