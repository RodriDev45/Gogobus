package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.PaymentResponse

data class PaymentResponseDto(
    val payment: PaymentDto
){
    fun toDomain() = PaymentResponse(
        payment = payment.toDomain()
    )
}
