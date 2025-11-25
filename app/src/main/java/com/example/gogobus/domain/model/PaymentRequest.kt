package com.example.gogobus.domain.model

import com.example.gogobus.data.remote.dto.requests.PaymentRequestDto

data class PaymentRequest(
    val transactionAmount: Double,
    val token: String,
    val description: String,
    val installments: Int,
    val paymentMethodId: String,
    val email: String,
    val type: String,
    val number: String,
    val bookingId: Int,
){
    fun toEntity() = PaymentRequestDto(
        transaction_amount = transactionAmount,
        token = token,
        description = description,
        installments = installments,
        payment_method_id = paymentMethodId,
        email = email,
        type = type,
        number = number,
        booking_id = bookingId
    )

}
