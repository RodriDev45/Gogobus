package com.example.gogobus.ui.presentation.home

import com.example.gogobus.domain.model.Post

data class HomeUiState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String? = null
)