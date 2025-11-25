package com.example.gogobus.domain.usecase

import com.example.gogobus.domain.repository.BookingRepository
import javax.inject.Inject

class GetBookingDetailUseCase @Inject constructor(
    private val repository: BookingRepository
) {
    suspend operator fun invoke(id: Int) = repository.getBookingById(id)
}