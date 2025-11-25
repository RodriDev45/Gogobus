package com.example.gogobus.data.remote.api

import com.example.gogobus.data.repository.TripRepositoryImpl
import com.example.gogobus.domain.model.PaginationQuery
import com.example.gogobus.domain.model.PaginationResponse
import com.example.gogobus.domain.model.TripQuery
import com.example.gogobus.domain.model.TripSearch
import com.example.gogobus.domain.util.DateUtils
import com.example.gogobus.domain.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate

class TripApiTest {
    @Test
    fun get_trips_data_is_correct() = runBlocking {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gogobusbackend.onrender.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(TripApi::class.java)
        val repo = TripRepositoryImpl(api)
        val query = TripQuery(
            pagination = PaginationQuery(
                pageSize = 1,
                page = 1,
            ),
            origin = 3,
            destination = 2,
            dateFrom = DateUtils.formatStringToLocalDate("2025-10-20T14:30:00Z"),
        )
        val response = repo.getTrips(query)
        println(response)
        when(response){
            is Resource.Error -> {
                assert(response.message == null)
            }
            Resource.Loading -> TODO()
            is Resource.Success<PaginationResponse<TripSearch>> -> {
                response.data.results.forEach {
                    println("Hora: " + DateUtils.formatHoursToString(it.departureTime))
                }
                assert(response.data != null)
            }
        }

    }
}