package com.example.gogobus.data.repository

import com.example.gogobus.data.remote.api.MapsApi
import com.example.gogobus.domain.repository.MapsRepository
import com.example.gogobus.domain.util.Resource
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil
import javax.inject.Inject

class MapsRepositoryImpl @Inject constructor(
    private val api: MapsApi
) : MapsRepository {

    override suspend fun getRoute(
        origin: LatLng,
        dest: LatLng,
        apiKey: String
    ): Resource<List<LatLng>> {
        return try {
            val response = api.getRoute(
                origin = "${origin.latitude},${origin.longitude}",
                destination = "${dest.latitude},${dest.longitude}",
                apiKey = apiKey
            )
            val points = PolyUtil.decode(response.routes[0].overview_polyline.points)
            Resource.Success(points)
        }
        catch (e: Exception) {
            Resource.Error(e.message ?: "Error desconocido")
        }

    }
}