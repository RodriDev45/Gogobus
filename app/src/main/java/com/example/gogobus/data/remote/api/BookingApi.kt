package com.example.gogobus.data.remote.api

import com.example.gogobus.data.remote.dto.requests.BookingRequestDto
import com.example.gogobus.data.remote.dto.responses.ApiResponseDto
import com.example.gogobus.data.remote.dto.responses.BookingDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BookingApi {
    @POST("bookings/")
    suspend fun createBooking(
        @Body bookingRequest: BookingRequestDto
    ): ApiResponseDto<BookingDto>

    @GET("bookings/{id}")
    suspend fun getBookingById(
        @Path("id") id: Int
    ): ApiResponseDto<BookingDto>
}