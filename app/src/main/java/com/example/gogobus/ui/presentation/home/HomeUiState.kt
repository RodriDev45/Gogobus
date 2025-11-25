package com.example.gogobus.ui.presentation.home

import com.example.gogobus.domain.model.Location
import java.time.LocalDate

data class HomeUiState(
    val userName: String = "",
    val locations: List<Location> = emptyList(),
    val origin: Location? = null,
    val destination: Location? = null,
    val date: LocalDate = LocalDate.now(),
    val hasHistory: Boolean = false,
    val historyCount: Int = 0
)
