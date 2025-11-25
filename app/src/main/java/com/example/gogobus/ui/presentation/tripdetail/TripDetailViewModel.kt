package com.example.gogobus.ui.presentation.tripdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gogobus.domain.model.Booking
import com.example.gogobus.domain.model.BookingRequest
import com.example.gogobus.domain.model.PassengerRequest
import com.example.gogobus.domain.model.TripDetail
import com.example.gogobus.domain.usecase.CreateBookingUseCase
import com.example.gogobus.domain.usecase.GetTripDetailUseCase
import com.example.gogobus.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripDetailViewModel @Inject constructor(
    val getTripDetailUseCase: GetTripDetailUseCase,
    val createBookingUseCase: CreateBookingUseCase
): ViewModel() {
    val _tripDetailState = MutableStateFlow(TripDetailUIState())
    val _tripDetailEvent = MutableSharedFlow<TripDetailUiEvent>()
    val tripDetailEvent = _tripDetailEvent.asSharedFlow()

    val tripDetailState = _tripDetailState.asStateFlow()

    fun loadTripDetail(id: Int) {
        viewModelScope.launch {
            _tripDetailState.value = _tripDetailState.value.copy(
                isLoading = true
            )
            val result = getTripDetailUseCase(id)
            Log.d("TripDetailViewModel", "result: $result")
            when(result){
                is Resource.Error -> {
                    _tripDetailState.value = _tripDetailState.value.copy(
                        error = result.message
                    )
                }
                Resource.Loading -> Unit
                is Resource.Success<TripDetail> -> {
                    _tripDetailState.value = _tripDetailState.value.copy(
                        trip = result.data,
                        seatsAvailable = result.data.availableSeats,
                        seatsOccupied = result.data.occupiedSeats
                    )
                }
            }
            _tripDetailState.value = _tripDetailState.value.copy(
                isLoading = false
            )
        }
    }

    fun addPassenger(passengerRequest: PassengerRequest){
        Log.d("TripDetailViewModel", "Passenger: ${passengerRequest}")

        var tempPassengerRequests: List<PassengerRequest> = _tripDetailState.value.passengerRequests
        if(_tripDetailState.value.passengerRequests.find { it.seatNumber == passengerRequest.seatNumber } != null){
            tempPassengerRequests = _tripDetailState.value.passengerRequests.filter { it.seatNumber != passengerRequest.seatNumber }
        }

        _tripDetailState.value = _tripDetailState.value.copy(
            passengerRequests = tempPassengerRequests + passengerRequest,
            seatsSelected = _tripDetailState.value.seatsSelected + passengerRequest.seatNumber,
            seatsAvailable = _tripDetailState.value.seatsAvailable.filter { it != passengerRequest.seatNumber },
        )
        Log.d("TripDetailViewModel", "Available: ${_tripDetailState.value.seatsAvailable}")
        Log.d("TripDetailViewModel", "Select: ${_tripDetailState.value.seatsSelected}")
        Log.d("TripDetailViewModel", "Occupied: ${_tripDetailState.value.seatsOccupied}")

    }

    fun getPassengerSelect(): PassengerRequest{
        return  _tripDetailState.value.passengerRequests
            .find { it.seatNumber == _tripDetailState.value.passengerSelect }
            ?: PassengerRequest(
                fullName = "",
                dni = "",
                seatNumber = _tripDetailState.value.passengerSelect
            )
    }

    fun setPassengerSelect(seatNumber: Int){
        _tripDetailState.value = _tripDetailState.value.copy(
            passengerSelect = seatNumber
        )
    }

    fun deletePassenger(seatNumber: Int) {
        _tripDetailState.value = _tripDetailState.value.copy(
            passengerRequests = _tripDetailState.value.passengerRequests.filter { it.seatNumber != seatNumber },
            seatsSelected = _tripDetailState.value.seatsSelected.filter { it != seatNumber },
            seatsAvailable = _tripDetailState.value.seatsAvailable + seatNumber,

        )
    }

    fun createBooking(){
        viewModelScope.launch {
            _tripDetailState.value = _tripDetailState.value.copy(
                isLoadingCreateBooking = true
            )
            val result = createBookingUseCase(
                bookingRequest = BookingRequest(
                    trip = _tripDetailState.value.trip!!.id,
                    passengers = _tripDetailState.value.passengerRequests,
                )
            )
            when(result) {
                is Resource.Error -> {
                    _tripDetailEvent.emit(TripDetailUiEvent.ShowError(result.message))
                }
                Resource.Loading -> Unit
                is Resource.Success<Booking> -> {
                    _tripDetailEvent.emit(TripDetailUiEvent.OnNavigateToSummary(result.data.id, result.data.trip))
                }
            }
            _tripDetailState.value = _tripDetailState.value.copy(
                isLoadingCreateBooking = false
            )
        }
    }

}