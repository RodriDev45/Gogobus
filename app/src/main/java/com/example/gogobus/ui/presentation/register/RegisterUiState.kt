package com.example.gogobus.ui.presentation.register

import com.example.gogobus.domain.model.RegisterRequest
import com.example.gogobus.domain.util.Resource

data class RegisterUiState (
    val isLoading: Boolean = false,
    val registerRequest: RegisterRequest = RegisterRequest("", "", "", ""),
    val resource: Resource<String> = Resource.Loading,
    val areTermsAccepted: Boolean = false,
    val isFormFilled: Boolean = false
)