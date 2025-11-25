package com.example.gogobus.data.remote.dto.requests

data class RegisterRequestDto(
    val username: String,
    val email: String,
    val password: String,
    val password2: String
)
