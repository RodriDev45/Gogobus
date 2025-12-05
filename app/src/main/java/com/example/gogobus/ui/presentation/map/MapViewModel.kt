package com.example.gogobus.ui.presentation.map

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gogobus.domain.model.Location
import com.example.gogobus.domain.usecase.GetTravelRouteUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.gogobus.BuildConfig
import com.example.gogobus.domain.util.Resource
import com.google.maps.android.compose.MarkerState


@HiltViewModel
class MapViewModel @Inject constructor(
    private val getTravelRouteUseCase: GetTravelRouteUseCase
): ViewModel() {
    private val _mapUiState = MutableStateFlow(MapUiState())
    val mapUiState: StateFlow<MapUiState> = _mapUiState.asStateFlow()

    fun getTravelRoute(origin: Location, destination: Location) {
        viewModelScope.launch {
            _mapUiState.value = _mapUiState.value.copy(isLoading = true)
            val result = getTravelRouteUseCase(
                origin = LatLng(origin.latitude, origin.longitude),
                dest = LatLng(destination.latitude, destination.longitude),
                apiKey = BuildConfig.MAPS_API_KEY

            )
            when(result){
                is Resource.Error -> {
                    Log.d("MapViewModel", "Error: ${result.message}")
                }
                Resource.Loading -> Unit
                is Resource.Success<List<LatLng>> -> {
                    _mapUiState.value = _mapUiState.value.copy(
                        route = result.data,
                        origin = MarkerState(position = LatLng(origin.latitude, origin.longitude)),
                        destination = MarkerState(position = LatLng(destination.latitude, destination.longitude))
                    )

                }
            }
            _mapUiState.value = _mapUiState.value.copy(isLoading = false)

        }
    }

}