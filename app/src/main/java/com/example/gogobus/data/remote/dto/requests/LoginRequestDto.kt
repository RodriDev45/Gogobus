package com.example.gogobus.data.remote.dto.requests

import com.example.gogobus.data.remote.dto.EntityMappable
import com.example.gogobus.domain.model.LoginRequest

data class LoginRequestDto(
    val email: String,
    val password: String
)

