package com.example.gogobus.ui.presentation.login

import com.example.gogobus.domain.model.ApiResponse
import com.example.gogobus.domain.model.AuthResponse

data class LoginUiState (
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val response: ApiResponse<AuthResponse>? = null,
)