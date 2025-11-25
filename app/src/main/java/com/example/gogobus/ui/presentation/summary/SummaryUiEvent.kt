package com.example.gogobus.ui.presentation.summary

sealed class SummaryUiEvent {
    data class ShowError(val message: String) : SummaryUiEvent()
}