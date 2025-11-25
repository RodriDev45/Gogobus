package com.example.gogobus.domain.model

import com.example.gogobus.data.remote.dto.EntityMappable
import com.example.gogobus.data.remote.dto.requests.LoginRequestDto

data class AuthResponse(
    val refresh: String,
    val access: String,
    val user: User
)

data class LoginRequest(
    val email: String,
    val password: String
): EntityMappable<LoginRequestDto> {
    override fun toEntity() = LoginRequestDto(email, password)
}


data class RefreshResponse(
    val access: String,
)