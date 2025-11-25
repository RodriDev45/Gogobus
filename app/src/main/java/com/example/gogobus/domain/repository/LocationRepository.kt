package com.example.gogobus.domain.repository

import com.example.gogobus.domain.model.Location
import com.example.gogobus.domain.model.PaginationResponse
import com.example.gogobus.domain.util.Resource

interface LocationRepository {

    suspend fun getLocations(pageSize: Int, page: Int): Resource<PaginationResponse<Location>>
}