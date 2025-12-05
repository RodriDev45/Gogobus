package com.example.gogobus.ui.presentation.profile

import com.example.gogobus.domain.model.User

data class ProfileUiState (
    val user: User = User(
        id = 0,
        username = "",
        email = "",
        role = ""
    ),
    val isLoading: Boolean = false
)