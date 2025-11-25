package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.RefreshResponse

data class RefreshResponseDto(
    val access: String
) {
    fun toDomain() = RefreshResponse(access)
}