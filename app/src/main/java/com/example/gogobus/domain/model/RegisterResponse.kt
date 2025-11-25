package com.example.gogobus.domain.model


data class RegisterResponse(
    val user: User,
    val tokens: TokensResponse
)

data class TokensResponse(
    val refresh: String,
    val access: String
)