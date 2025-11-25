package com.example.gogobus.domain.util

sealed class Resource<out T> {
    data class Success<out T>(val data: T, val message: String = "") : Resource<T>()
    data class Error(val message: String, val code: Int? = null) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
