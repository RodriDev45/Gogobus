package com.example.gogobus.data.remote.api

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class LocationApiTest {

    @Test
    fun get_location_data_is_correct() = runBlocking {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gogobusbackend.onrender.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(LocationApi::class.java)
        val response = api.locations(10, 1)

        if(response.success){
            println(response.data)
            assert(response.data?.results?.isNotEmpty() == true)
        }

        if(!response.success){
            assert(response.data == null)
        }

    }

    @Test
    fun map_time_is_correct() {
        val dateString = "2025-10-20T14:00:00Z"
        val localDate: LocalDate = Instant.parse(dateString)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        println(localDate)
        assertEquals("2025-10-20", localDate.toString())
    }
}