package com.example.gogobus.ui.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
) : ViewModel() {

    private val _isSplashTimeOut = MutableStateFlow(false)
    val isSplashTimeOut = _isSplashTimeOut.asStateFlow()

    init {
        // Lanza una corrutina para simular el tiempo de espera de la pantalla de bienvenida
        viewModelScope.launch {
            delay(3000L) // Espera 3 segundos
            _isSplashTimeOut.value = true
        }
    }
}
