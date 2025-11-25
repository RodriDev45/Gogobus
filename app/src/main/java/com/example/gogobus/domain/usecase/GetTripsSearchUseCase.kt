package com.example.gogobus.domain.usecase

import com.example.gogobus.domain.model.TripQuery
import com.example.gogobus.domain.repository.TripRepository
import javax.inject.Inject

class GetTripsSearchUseCase @Inject constructor(
    private val repository: TripRepository

){
    suspend operator fun invoke(
        query: TripQuery
    ) = repository.getTrips(query = query)

}