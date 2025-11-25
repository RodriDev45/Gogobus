package com.example.gogobus.data.remote.dto.requests

data class PaymentRequestDto(
    val transaction_amount: Double,
    val token: String,
    val description: String,
    val installments: Int,
    val payment_method_id: String,
    val email: String,
    val type: String,
    val number: String,
    val booking_id: Int,
)
