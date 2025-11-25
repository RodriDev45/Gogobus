package com.example.gogobus.domain.repository

import com.example.gogobus.domain.model.ApiResponse
import com.example.gogobus.domain.model.AuthResponse
import com.example.gogobus.domain.model.RegisterRequest
import com.example.gogobus.domain.util.Resource

interface AuthRepository {
    suspend fun login(email: String, password: String): Resource<String>
    suspend fun refreshAccessToken(): Resource<String>
    suspend fun logout(): Resource<String>

    suspend fun register(registerRequest: RegisterRequest): Resource<String>
}