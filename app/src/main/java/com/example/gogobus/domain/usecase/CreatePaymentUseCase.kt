package com.example.gogobus.domain.usecase

import com.example.gogobus.domain.model.PaymentRequest
import com.example.gogobus.domain.repository.PaymentRepository
import javax.inject.Inject

class CreatePaymentUseCase @Inject constructor(
    val repository: PaymentRepository
){
    suspend operator fun invoke(paymentRequest: PaymentRequest) = repository.createPayment(paymentRequest)
}