package com.example.gogobus.domain.repository

import com.example.gogobus.domain.model.PaginationResponse
import com.example.gogobus.domain.model.TripDetail
import com.example.gogobus.domain.model.TripQuery
import com.example.gogobus.domain.model.TripSearch
import com.example.gogobus.domain.util.Resource
import java.time.LocalDate

interface TripRepository {

    suspend fun getTrips(
        query: TripQuery
    ): Resource<PaginationResponse<TripSearch>>

    suspend fun getTripById(
        id: Int,
    ): Resource<TripDetail>

}