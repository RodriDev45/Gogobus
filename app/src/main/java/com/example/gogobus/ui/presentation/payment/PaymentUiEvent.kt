package com.example.gogobus.ui.presentation.payment

import com.example.gogobus.domain.model.Payment

sealed class PaymentUiEvent {
    data class ShowError(val message: String) : PaymentUiEvent()
    data class OnSuccessPayment(val payment: Payment) : PaymentUiEvent()

}