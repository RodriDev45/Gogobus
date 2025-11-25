package com.example.gogobus.domain.repository

import com.example.gogobus.domain.model.Booking
import com.example.gogobus.domain.model.BookingRequest
import com.example.gogobus.domain.util.Resource

interface BookingRepository {
    suspend fun getBookingById(id: Int): Resource<Booking>
    suspend fun createBooking(bookingRequest: BookingRequest): Resource<Booking>
}