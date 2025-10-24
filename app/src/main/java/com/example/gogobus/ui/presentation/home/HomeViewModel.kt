package com.example.gogobus.ui.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// NO DEBE HABER NINGUNA DATA CLASS AQUÍ. Se importa la de HomeUiState.kt

class HomeViewModel : ViewModel() {
    // Usar HomeUiState (con 'i' minúscula) para que coincida con la definición global
    private val _uiState = MutableStateFlow(HomeUiState(userName = "Aruna Dahlia"))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun updateOrigin(origin: String) {
        _uiState.value = _uiState.value.copy(origin = origin)
    }

    fun updateDestination(destination: String) {
        _uiState.value = _uiState.value.copy(destination = destination)
    }

    fun updateDate(date: String) {
        _uiState.value = _uiState.value.copy(date = date)
    }

    fun searchBuses() {
        // Lógica de búsqueda de buses
        println("Buscando buses de ${_uiState.value.origin} a ${_uiState.value.destination}")
    }
}
