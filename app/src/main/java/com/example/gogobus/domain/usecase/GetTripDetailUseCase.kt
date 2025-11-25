package com.example.gogobus.domain.usecase

import com.example.gogobus.domain.repository.TripRepository
import javax.inject.Inject

class GetTripDetailUseCase @Inject constructor(
    val repository: TripRepository
){
    suspend operator fun invoke(
        id: Int
    ) = repository.getTripById(id = id)

}
