package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.Payment

data class PaymentDto(
    val id: Int,
    val payment_id: String,
    val status: String,
    val amount: String,
    val method: String,
    val description: String,
    val user: Int,
    val booking: Int
){
    fun toDomain() = Payment(
        id = id,
        paymentId = payment_id,
        status = status,
        amount = amount,
        method = method,
        description = description,
        user = user,
        booking = booking
    )
}
