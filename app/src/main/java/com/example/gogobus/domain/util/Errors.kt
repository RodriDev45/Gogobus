package com.example.gogobus.domain.util

fun parseErrorMessage(errorBody: String?): String? {
    return try {
        val json = org.json.JSONObject(errorBody ?: return null)
        json.optString("message", "Error desconocido")
    } catch (e: Exception) {
        null
    }
}
