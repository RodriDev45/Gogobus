package com.example.gogobus.ui.presentation.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gogobus.domain.model.Booking
import com.example.gogobus.domain.model.TripDetail
import com.example.gogobus.domain.usecase.GetBookingDetailUseCase
import com.example.gogobus.domain.usecase.GetTripDetailUseCase
import com.example.gogobus.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val getTripDetailUseCase: GetTripDetailUseCase,
    private val getBookingDetailUseCase: GetBookingDetailUseCase
) : ViewModel() {

    private val _summaryState = MutableStateFlow(SummaryUiState())
    val summaryState = _summaryState.asStateFlow()

    private val _summaryEvent = MutableSharedFlow<SummaryUiEvent>()
    val summaryEvent = _summaryEvent.asSharedFlow()



    fun loadSummary(bookingId: Int, tripId: Int) {
        viewModelScope.launch {

            _summaryState.value = _summaryState.value.copy(
                isLoading = true
            )

            // Ejecutar en paralelo
            val bookingDeferred = async { getBookingDetailUseCase(bookingId) }
            val tripDeferred = async { getTripDetailUseCase(tripId) }

            // Esperar ambos resultados
            val bookingResult = bookingDeferred.await()
            val tripResult = tripDeferred.await()

            // Procesar booking
            when(bookingResult){
                is Resource.Error -> {
                    _summaryEvent.emit(SummaryUiEvent.ShowError(bookingResult.message))
                }
                Resource.Loading -> Unit
                is Resource.Success<Booking> -> {
                    _summaryState.value = _summaryState.value.copy(
                        booking = bookingResult.data
                    )
                }
            }

            when(tripResult){
                is Resource.Error -> {
                    _summaryEvent.emit(SummaryUiEvent.ShowError(tripResult.message))
                }
                Resource.Loading -> Unit
                is Resource.Success<TripDetail> -> {
                    _summaryState.value = _summaryState.value.copy(
                        trip = tripResult.data
                    )
                }
            }

            // Finaliza la carga
            _summaryState.value = _summaryState.value.copy(
                isLoading = false
            )
        }
    }


}