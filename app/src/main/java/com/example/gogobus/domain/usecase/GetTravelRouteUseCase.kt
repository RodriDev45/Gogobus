package com.example.gogobus.domain.usecase

import com.example.gogobus.domain.repository.MapsRepository
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class GetTravelRouteUseCase @Inject constructor(
    private val repository: MapsRepository
) {
    suspend operator fun invoke(origin: LatLng, dest: LatLng, apiKey: String)
    = repository.getRoute(origin, dest, apiKey)
}