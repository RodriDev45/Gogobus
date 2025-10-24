package com.example.gogobus.data.remote.dto.responses

import com.example.gogobus.domain.model.ApiResponse
import com.example.gogobus.domain.model.PaginationResponse

data class PaginationResponseDto<T>(
    val count: Int,
    val total_pages: Int,
    val current_page: Int,
    val results: List<T>
)

fun <T, R> PaginationResponseDto<T>.toDomain(mapper: (T) -> R): PaginationResponse<R> {
    return PaginationResponse(
        count = count,
        totalPages = total_pages,
        currentPage = current_page,
        results = results.map{
            it?.let(mapper) as R
        }
    )
}