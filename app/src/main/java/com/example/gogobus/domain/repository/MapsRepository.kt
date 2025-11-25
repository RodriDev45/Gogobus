package com.example.gogobus.domain.repository

import com.example.gogobus.domain.util.Resource
import com.google.android.gms.maps.model.LatLng

interface MapsRepository {

    suspend fun getRoute(origin: LatLng, dest: LatLng, apiKey: String): Resource<List<LatLng>>
}