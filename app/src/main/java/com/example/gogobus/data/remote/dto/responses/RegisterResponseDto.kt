package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.RegisterResponse
import com.example.gogobus.domain.model.TokensResponse

data class RegisterResponseDto(
    val user: UserDto,
    val tokens: TokensResponseDto
){
    fun toDomain() = RegisterResponse(
        user = user.toDomain(),
        tokens = tokens.toDomain()
    )
}

data class TokensResponseDto(
    val refresh: String,
    val access: String
){
    fun toDomain() = TokensResponse(
        refresh = refresh,
        access = access
    )
}