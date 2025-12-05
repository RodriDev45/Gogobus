package com.example.gogobus.ui.presentation.profile

import com.example.gogobus.ui.presentation.login.LoginUiEvent

sealed class ProfileUiEvent {
    data class ShowError(val message: String) : ProfileUiEvent()
}