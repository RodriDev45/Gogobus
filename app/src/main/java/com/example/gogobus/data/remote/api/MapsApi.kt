package com.example.gogobus.data.remote.api

import com.example.gogobus.data.remote.dto.responses.DirectionResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MapsApi {

    @GET("directions/json")
    suspend fun getRoute(
        @Query("origin") origin: String,        // "lat,lng"
        @Query("destination") destination: String,
        @Query("mode") mode: String = "driving",
        @Query("key") apiKey: String
    ): DirectionResponseDto
}