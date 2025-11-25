package com.example.gogobus.data.repository

import com.example.gogobus.data.remote.api.PaymentApi
import com.example.gogobus.data.remote.dto.responses.toDomain
import com.example.gogobus.domain.model.Payment
import com.example.gogobus.domain.model.PaymentRequest
import com.example.gogobus.domain.repository.BaseRepository
import com.example.gogobus.domain.repository.PaymentRepository
import com.example.gogobus.domain.util.Resource
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val api: PaymentApi
) : BaseRepository(), PaymentRepository {
    override suspend fun createPayment(paymentRequest: PaymentRequest): Resource<Payment> {
        return safeApiCall(
            apiCall = {
                api.createPayment(paymentRequest.toEntity()).toDomain { it.toDomain() }
            },
            mapData = { data ->
                data.payment
            }
        )
    }
}