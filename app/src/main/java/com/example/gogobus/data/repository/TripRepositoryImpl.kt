package com.example.gogobus.data.repository

import com.example.gogobus.data.remote.api.TripApi
import com.example.gogobus.data.remote.dto.responses.toDomain
import com.example.gogobus.domain.model.PaginationResponse
import com.example.gogobus.domain.model.TripDetail
import com.example.gogobus.domain.model.TripQuery
import com.example.gogobus.domain.model.TripSearch
import com.example.gogobus.domain.repository.BaseRepository
import com.example.gogobus.domain.repository.TripRepository
import com.example.gogobus.domain.util.DateUtils
import com.example.gogobus.domain.util.Resource
import java.time.LocalDate
import javax.inject.Inject

class TripRepositoryImpl @Inject constructor (
    private val api: TripApi
): BaseRepository(), TripRepository {

    override suspend fun getTrips(
        query: TripQuery
    ): Resource<PaginationResponse<TripSearch>> {
        return safeApiCall(
            apiCall = {
                api.getTrips(query.toEntity().toQueryMap())
                    .toDomain { it.toDomain { it.toDomain() } }
            },
            mapData = { data ->
                data
            }
        )
    }

    override suspend fun getTripById(id: Int): Resource<TripDetail> {
        return safeApiCall(
            apiCall = {
                api.getTripById(id).toDomain { it.toDomain() }
            },
            mapData = { data ->
                data
            }
        )
    }
}