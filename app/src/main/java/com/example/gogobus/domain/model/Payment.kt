package com.example.gogobus.domain.model

data class Payment(
    val id: Int,
    val paymentId: String,
    val status: String,
    val amount: String,
    val method: String,
    val description: String,
    val user: Int,
    val booking: Int
)
