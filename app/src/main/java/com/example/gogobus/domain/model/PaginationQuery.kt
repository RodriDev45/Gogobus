package com.example.gogobus.domain.model

import com.example.gogobus.data.remote.dto.requests.PaginationQueryDto

data class PaginationQuery(
    val pageSize: Int,
    val page: Int
){
    fun toEntity() = PaginationQueryDto(
        pageSize = pageSize,
        page = page
    )
}
