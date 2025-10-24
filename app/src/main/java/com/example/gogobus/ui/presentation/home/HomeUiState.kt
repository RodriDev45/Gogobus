package com.example.gogobus.ui.presentation.home

data class HomeUiState(
    val userName: String = "",
    val origin: String = "",
    val destination: String = "",
    val date: String = "",
    val hasHistory: Boolean = false,
    val historyCount: Int = 0
)
