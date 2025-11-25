package com.example.gogobus.domain.usecase

import com.example.gogobus.domain.model.BookingRequest
import com.example.gogobus.domain.repository.BookingRepository
import javax.inject.Inject

class CreateBookingUseCase @Inject constructor(
    private val repository: BookingRepository
) {
    suspend operator fun invoke(bookingRequest: BookingRequest) = repository.createBooking(bookingRequest)
}