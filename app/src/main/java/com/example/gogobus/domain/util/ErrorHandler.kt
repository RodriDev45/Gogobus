package com.example.gogobus.domain.util

import retrofit2.HttpException
import java.io.IOException

object ErrorHandler {

    fun fromException(e: Throwable): AppError {
        return when (e) {
            is IOException -> AppError.Network()

            is HttpException -> {
                val errorBody = e.response()?.errorBody()?.string()
                val backendMessage = errorBody?.let { parseErrorMessage(it) }

                when (e.code()) {
                    401 -> AppError.Unauthorized(backendMessage ?: "Sesión expirada o credenciales inválidas")
                    403 -> AppError.Forbidden(backendMessage ?: "No tienes permisos para acceder a este recurso")
                    404 -> AppError.NotFound(backendMessage ?: "Recurso no encontrado")
                    422 -> AppError.Validation(backendMessage ?: "Error de validación")
                    else -> AppError.Unknown(backendMessage ?: "Error del servidor (${e.code()})")
                }
            }

            else -> AppError.Unknown(e.localizedMessage ?: "Error desconocido")
        }
    }
}
