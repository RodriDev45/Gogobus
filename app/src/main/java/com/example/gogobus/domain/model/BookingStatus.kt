package com.example.gogobus.domain.model

sealed class BookingStatus(val value: String, val label: String) {

    data object Pending : BookingStatus("PENDING", "Pendiente")
    data object Paid : BookingStatus("PAID", "Pagado")
    data object Cancelled : BookingStatus("CANCELLED", "Cancelado")

    companion object {
        fun fromValue(value: String): BookingStatus =
            when (value) {
                Pending.value -> Pending
                Paid.value -> Paid
                Cancelled.value -> Cancelled
                else -> throw IllegalArgumentException("Estado desconocido: $value")
            }
    }
}
