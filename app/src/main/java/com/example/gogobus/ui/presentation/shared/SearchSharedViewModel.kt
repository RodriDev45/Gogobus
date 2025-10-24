package com.example.gogobus.ui.presentation.shared

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gogobus.domain.model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SearchSharedViewModel @Inject constructor() : ViewModel() {
    var origin by mutableStateOf(Location(-1, "", "", "", ""))
    var destination by mutableStateOf(Location(-1, "", "", "", ""))
    var date by mutableStateOf(LocalDate.now())
}
