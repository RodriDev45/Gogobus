package com.example.gogobus.ui.presentation.tripdetail

import com.example.gogobus.ui.presentation.login.LoginUiEvent

sealed class TripDetailUiEvent {
    data class ShowError(val message: String) : TripDetailUiEvent()
    data class OnNavigateToSummary(val bookingId: Int, val tripId: Int) : TripDetailUiEvent()

}