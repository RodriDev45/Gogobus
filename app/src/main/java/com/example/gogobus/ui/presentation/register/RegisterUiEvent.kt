package com.example.gogobus.ui.presentation.register

sealed class RegisterUiEvent {
    object NavigateToHome: RegisterUiEvent()
    data class ShowError(val message: String): RegisterUiEvent()
}