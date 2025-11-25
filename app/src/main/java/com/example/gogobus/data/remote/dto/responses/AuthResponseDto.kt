package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.AuthResponse

data class AuthResponseDto(
    val refresh: String,
    val access: String,
    val user: UserDto
) {
    fun toDomain() = AuthResponse(
        refresh = refresh,
        access = access,
        user = user.toDomain()
    )

}