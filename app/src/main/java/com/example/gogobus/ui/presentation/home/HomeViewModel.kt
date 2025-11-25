package com.example.gogobus.ui.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gogobus.domain.model.Location
import com.example.gogobus.domain.session.SessionManager
import com.example.gogobus.domain.usecase.LogoutUseCase
import com.example.gogobus.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

// NO DEBE HABER NINGUNA DATA CLASS AQUÍ. Se importa la de HomeUiState.kt
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {
    // Usar HomeUiState (con 'i' minúscula) para que coincida con la definición global
    private val _uiState = MutableStateFlow(HomeUiState(userName = "Aruna Dahlia"))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun updateOrigin(origin: Location?) {
        _uiState.value = _uiState.value.copy(origin = origin)
    }

    fun updateDestination(destination: Location?) {
        _uiState.value = _uiState.value.copy(destination = destination)
    }

    fun updateDate(date: LocalDate) {
        _uiState.value = _uiState.value.copy(date = date)
    }

    fun searchBuses() {
        viewModelScope.launch {
            val result = logoutUseCase()
            when(result){
                is Resource.Error -> Log.e("Error", result.message)
                Resource.Loading -> {}
                is Resource.Success<*> -> {
                    sessionManager.finishSession()
                }
            }
        }
    }
}
