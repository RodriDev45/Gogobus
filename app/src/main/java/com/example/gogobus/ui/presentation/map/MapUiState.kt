package com.example.gogobus.ui.presentation.map

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerState

data class MapUiState(
    val isLoading: Boolean = false,
    val route: List<LatLng> = emptyList(),
    val origin: MarkerState? = null,
    val destination: MarkerState? = null
)
