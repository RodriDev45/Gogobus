package com.example.gogobus.ui.presentation.search

import com.example.gogobus.domain.model.PaginationQuery
import com.example.gogobus.domain.model.TripQuery
import com.example.gogobus.domain.model.TripSearch

data class SearchUiState (
    val trips: List<TripSearch> = emptyList(),
    val query: TripQuery = TripQuery(
        pagination = PaginationQuery(
            page = 1,
            pageSize = 10
        ),
        origin = -1,
        destination = -1,
    ),
    val isLoading: Boolean = false,
    val noMatches: Boolean = false
)