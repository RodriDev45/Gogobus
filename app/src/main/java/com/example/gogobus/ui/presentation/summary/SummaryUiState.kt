package com.example.gogobus.ui.presentation.summary

import com.example.gogobus.domain.model.Booking
import com.example.gogobus.domain.model.TripDetail

data class SummaryUiState (
    val isLoading: Boolean = false,
    val isLoadingPayment: Boolean = false,
    val trip: TripDetail? = null,
    val booking: Booking? = null
)