package com.example.gogobus.domain.repository

import com.example.gogobus.domain.model.ApiResponse
import com.example.gogobus.domain.util.ErrorHandler
import com.example.gogobus.domain.util.Resource

abstract class BaseRepository {

    /**
     * Ejecuta una llamada a la API de forma segura.
     * - Captura errores HTTP, de red o inesperados.
     * - Permite mapear el tipo de dato de respuesta (T -> R).
     */
    protected suspend fun <T, R> safeApiCall(
        apiCall: suspend () -> ApiResponse<T>,
        mapData: suspend (T) -> R
    ): Resource<R> {
        return try {
            val response = apiCall()
            if (response.success && response.data != null) {
                val mappedData = mapData(response.data)
                Resource.Success(mappedData, response.message)
            } else {
                Resource.Error(response.message)
            }

        } catch (e: Exception) {
            val appError = ErrorHandler.fromException(e)
            Resource.Error(appError.message)
        }
    }
}
