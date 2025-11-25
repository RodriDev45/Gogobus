package com.example.gogobus.domain.usecase

import com.example.gogobus.domain.repository.AuthRepository
import com.example.gogobus.domain.session.SessionManager
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    val repository: AuthRepository,
) {
    suspend operator fun invoke() = repository.logout()
}