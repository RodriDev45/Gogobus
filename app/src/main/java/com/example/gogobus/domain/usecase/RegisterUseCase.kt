package com.example.gogobus.domain.usecase

import com.example.gogobus.domain.model.RegisterRequest
import com.example.gogobus.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(registerRequest: RegisterRequest) = repository.register(registerRequest)

}