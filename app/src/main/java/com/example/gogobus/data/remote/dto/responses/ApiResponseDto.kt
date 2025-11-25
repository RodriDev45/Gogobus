package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.ApiResponse

data class ApiResponseDto<T>(
    val success: Boolean,
    val message: String,
    val data: T?
)

fun <T, R> ApiResponseDto<T>.toDomain(mapper: (T) -> R): ApiResponse<R> {
    return ApiResponse(
        success = success,
        message = message,
        data = data?.let(mapper)
    )
}

