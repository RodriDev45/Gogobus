package com.example.gogobus.ui.presentation.login

sealed class LoginUiEvent {
    data class ShowError(val message: String) : LoginUiEvent()
    object NavigateToHome : LoginUiEvent()
}