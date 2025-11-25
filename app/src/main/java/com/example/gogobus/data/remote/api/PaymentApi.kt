package com.example.gogobus.data.remote.api

import com.example.gogobus.data.remote.dto.requests.PaymentRequestDto
import com.example.gogobus.data.remote.dto.responses.ApiResponseDto
import com.example.gogobus.data.remote.dto.responses.PaymentResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentApi {

    @POST("payments/process/")
    suspend fun createPayment(
        @Body paymentRequest: PaymentRequestDto
    ): ApiResponseDto<PaymentResponseDto>
}