package com.example.gogobus.data.remote.dto

interface EntityMappable<T> {
    fun toEntity(): T
}