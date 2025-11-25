package com.example.gogobus.data.remote.dto

interface DomainMappable<T> {
    fun toDomain(): T
}