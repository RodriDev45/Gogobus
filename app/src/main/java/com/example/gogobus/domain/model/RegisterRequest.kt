package com.example.gogobus.domain.model

import com.example.gogobus.data.remote.dto.requests.RegisterRequestDto

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val password2: String
){
    fun toEntity() = RegisterRequestDto(
        username = username,
        email = email,
        password = password,
        password2 = password2
    )
}
