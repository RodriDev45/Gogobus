package com.example.gogobus.ui.presentation.tripdetail

import com.example.gogobus.domain.model.PassengerRequest
import com.example.gogobus.domain.model.TripDetail

data class TripDetailUIState (
    val isLoading: Boolean = false,
    val isLoadingCreateBooking: Boolean = false,
    val trip: TripDetail? = null,
    val error: String? = null,
    val passengerRequests: List<PassengerRequest> = emptyList(),
    val passengerSelect: Int = -1,
    val seatsAvailable: List<Int> = emptyList(),
    val seatsOccupied: List<Int> = emptyList(),
    val seatsSelected: List<Int> = emptyList()
)
