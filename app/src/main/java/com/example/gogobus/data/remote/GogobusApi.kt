package com.example.gogobus.data.remote

import com.example.gogobus.domain.model.ApiResponse
import com.example.gogobus.domain.model.AuthResponse
import com.example.gogobus.domain.model.LoginRequest
import com.example.gogobus.domain.model.Post
import com.example.gogobus.domain.model.RefreshResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GogobusApi {
    @POST("users/login/")
    suspend fun login(
        @Body request: LoginRequest
    ): ApiResponse<AuthResponse>

    @POST("users/logout/")
    suspend fun logout(
        @Body body: Map<String, String>
    ):  Response<ApiResponse<String?>>

    @POST("users/token/refresh/")
    suspend fun refreshToken(
        @Body body: Map<String, String> // {"refresh": "token"}
    ): RefreshResponse // solo devuelve {"access": "nuevo_token"}
}