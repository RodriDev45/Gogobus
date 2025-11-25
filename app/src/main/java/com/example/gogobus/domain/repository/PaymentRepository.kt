package com.example.gogobus.domain.repository

import com.example.gogobus.domain.model.Payment
import com.example.gogobus.domain.model.PaymentRequest
import com.example.gogobus.domain.util.Resource

interface PaymentRepository {
    suspend fun createPayment(paymentRequest: PaymentRequest): Resource<Payment>
}