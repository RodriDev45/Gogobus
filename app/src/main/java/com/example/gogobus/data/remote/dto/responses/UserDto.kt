package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.User

data class UserDto(
    val id: Int,
    val username: String,
    val email: String,
    val role: String
) {
    fun toDomain() = User(id, username, email, role)
}
