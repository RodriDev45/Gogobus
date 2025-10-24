package com.example.gogobus.ui.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gogobus.domain.model.Location
import com.example.gogobus.domain.model.PaginationResponse
import com.example.gogobus.domain.model.TripQuery
import com.example.gogobus.domain.model.TripSearch
import com.example.gogobus.domain.usecase.GetTripsSearchUseCase
import com.example.gogobus.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val getTripsUseCase: GetTripsSearchUseCase
): ViewModel() {
    val _searchState = MutableStateFlow(SearchUiState())
    val searchState = _searchState.asStateFlow()

    fun loadTrips(origin: Location, destination: Location, date: LocalDate) {
        viewModelScope.launch {
            _searchState.value = _searchState.value.copy(
                isLoading = true,
                query = _searchState.value.query.copy(
                    origin = origin.id,
                    destination = destination.id,
                    date = date
                )
            )

            val result = getTripsUseCase(_searchState.value.query)

            when(result){
                is Resource.Error -> {
                    _searchState.value = _searchState.value.copy(
                        noMatches = true
                    )
                }
                Resource.Loading -> Unit
                is Resource.Success<PaginationResponse<TripSearch>> -> {
                    _searchState.value = _searchState.value.copy(
                        trips = result.data.results
                    )
                }
            }

            _searchState.value = _searchState.value.copy(
                isLoading = false
            )


        }
    }
}