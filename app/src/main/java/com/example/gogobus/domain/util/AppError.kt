package com.example.gogobus.domain.util

sealed class AppError(val message: String) {
    data class Network(val cause: String = "Sin conexión a Internet") : AppError(cause)
    data class Unauthorized(val cause: String = "Sesión expirada o inválida") : AppError(cause)
    data class Forbidden(val cause: String = "No tienes permisos") : AppError(cause)
    data class NotFound(val cause: String = "Recurso no encontrado") : AppError(cause)
    data class Validation(val details: String) : AppError(details)
    data class Unknown(val cause: String = "Error inesperado") : AppError(cause)
}
