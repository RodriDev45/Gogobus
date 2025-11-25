package com.example.gogobus.domain.model

data class PaginationResponse<T>(
    val count: Int,
    val totalPages: Int,
    val currentPage: Int,
    val results: List<T>
)
