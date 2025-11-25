package com.example.gogobus.ui.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gogobus.domain.model.Location
import com.example.gogobus.domain.model.PaginationResponse
import com.example.gogobus.domain.repository.LocationRepository
import com.example.gogobus.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val repository: LocationRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(LocationsUiState(locations = emptyList()))
    val uiState = _uiState.asStateFlow()


    fun loadData(){
        // ✅ Prevenir carga infinita
        if (_uiState.value.isLoading || !_uiState.value.hasMore) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val page = _uiState.value.page

            Log.d("LocationsViewModel", "Loading page $page")

            val result = repository.getLocations(_uiState.value.pageSize, page)

            when (result) {
                is Resource.Error -> {
                    // No más datos
                    _uiState.value = _uiState.value.copy(hasMore = false)
                }

                Resource.Loading -> Unit

                is Resource.Success<PaginationResponse<Location>> -> {
                    val newItems = result.data.results

                    _uiState.value = _uiState.value.copy(
                        locations = _uiState.value.locations + newItems,
                        totalPages = result.data.totalPages,
                        page = result.data.currentPage + 1
                    )

                    Log.d("LocationsViewModel", "Loaded ${newItems.size} items")

                    // ✅ Si no trae resultados → no hay más
                    if (newItems.isEmpty() || _uiState.value.page > _uiState.value.totalPages) {
                        _uiState.value = _uiState.value.copy(hasMore = false)
                    }
                }
            }

            _uiState.value = _uiState.value.copy(isLoading = false)
        }

    }

    fun changePage(page: Int){
        _uiState.value = _uiState.value.copy(page = page)
    }
}