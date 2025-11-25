package com.example.gogobus.domain.usecase

import com.example.gogobus.domain.repository.AuthRepository
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.refreshAccessToken()
}