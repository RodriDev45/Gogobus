package com.example.gogobus.data.remote.api

import com.example.gogobus.data.remote.dto.responses.ApiResponseDto
import com.example.gogobus.data.remote.dto.responses.PaginationResponseDto
import com.example.gogobus.data.remote.dto.responses.TripDetailDto
import com.example.gogobus.data.remote.dto.responses.TripSearchDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface TripApi {

    @GET("trips/")
    suspend fun getTrips(
        @QueryMap queries: Map<String, @JvmSuppressWildcards Any>
    ): ApiResponseDto<PaginationResponseDto<TripSearchDto>>

    @GET("trips/{id}")
    suspend fun getTripById(
        @Path("id") id: Int
    ): ApiResponseDto<TripDetailDto>

}