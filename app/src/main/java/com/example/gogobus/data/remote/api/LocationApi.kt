package com.example.gogobus.data.remote.api

import com.example.gogobus.data.remote.dto.responses.ApiResponseDto
import com.example.gogobus.data.remote.dto.responses.LocationDto
import com.example.gogobus.data.remote.dto.responses.PaginationResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET("trips/locations/")
    suspend fun locations(
        @Query("page_size") pageSize: Int,
        @Query("page") page: Int
    ): ApiResponseDto<PaginationResponseDto<LocationDto>>
}