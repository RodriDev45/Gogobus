package com.example.gogobus.ui.presentation.home

import com.example.gogobus.domain.model.Location

data class LocationsUiState (
    val locations: List<Location>,
    val isLoading: Boolean = false,
    val pageSize: Int = 15,
    val page: Int = 1,
    val totalPages: Int = 1,
    val hasMore: Boolean = true
)