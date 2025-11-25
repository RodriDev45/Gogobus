package com.example.gogobus.data.remote.api

import com.example.gogobus.data.remote.dto.requests.LoginRequestDto
import com.example.gogobus.data.remote.dto.requests.LogoutRequestDto
import com.example.gogobus.data.remote.dto.requests.RegisterRequestDto
import com.example.gogobus.data.remote.dto.responses.ApiResponseDto
import com.example.gogobus.data.remote.dto.responses.AuthResponseDto
import com.example.gogobus.data.remote.dto.responses.RegisterResponseDto
import com.example.gogobus.domain.model.ApiResponse
import com.example.gogobus.domain.model.AuthResponse
import com.example.gogobus.domain.model.LoginRequest
import com.example.gogobus.domain.model.RefreshResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("users/login/")
    suspend fun login(@Body request: LoginRequestDto): ApiResponseDto<AuthResponseDto>

    @POST("users/register/")
    suspend fun register(@Body request: RegisterRequestDto): ApiResponseDto<RegisterResponseDto>

    @POST("users/logout/")
    suspend fun logout(@Body body: LogoutRequestDto): Response<ApiResponseDto<String?>>

    @POST("users/token/refresh/")
    suspend fun refreshToken(@Body body: Map<String, String>): RefreshResponse
}