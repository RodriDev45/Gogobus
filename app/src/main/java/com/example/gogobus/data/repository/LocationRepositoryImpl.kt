package com.example.gogobus.data.repository

import android.util.Log
import com.example.gogobus.data.remote.api.LocationApi
import com.example.gogobus.data.remote.dto.responses.toDomain
import com.example.gogobus.domain.model.Location
import com.example.gogobus.domain.model.PaginationResponse
import com.example.gogobus.domain.repository.BaseRepository
import com.example.gogobus.domain.repository.LocationRepository
import com.example.gogobus.domain.util.Resource
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor (
    private val api: LocationApi
): BaseRepository(), LocationRepository {

    override suspend fun getLocations(
        pageSize: Int,
        page: Int
    ): Resource<PaginationResponse<Location>> {
        return safeApiCall(
            apiCall = {
                api.locations(pageSize = pageSize, page = page)
                    .toDomain{ it.toDomain { it.toDomain() } }},
            mapData = { data ->
                Log.d("LocationsViewModel", "Data $data")

                data


            }
        )
    }

}