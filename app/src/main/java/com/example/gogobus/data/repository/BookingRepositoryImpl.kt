package com.example.gogobus.data.repository

import com.example.gogobus.data.remote.api.BookingApi
import com.example.gogobus.data.remote.dto.responses.toDomain
import com.example.gogobus.domain.model.Booking
import com.example.gogobus.domain.model.BookingRequest
import com.example.gogobus.domain.repository.BaseRepository
import com.example.gogobus.domain.repository.BookingRepository
import com.example.gogobus.domain.util.Resource
import javax.inject.Inject

class BookingRepositoryImpl @Inject constructor (
    private val api: BookingApi
): BaseRepository(), BookingRepository {
    override suspend fun getBookingById(id: Int): Resource<Booking> {
        return safeApiCall(
            apiCall = {
                api.getBookingById(id).toDomain { it.toDomain() }
            },
            mapData = { data ->
                data
            }
        )
    }

    override suspend fun createBooking(bookingRequest: BookingRequest): Resource<Booking> {
        return safeApiCall(
            apiCall = {
                api.createBooking(bookingRequest.toEntity()).toDomain { it.toDomain() }
            },
            mapData = { data ->
                data
            }
        )
    }
}