package com.example.gogobus.domain.model

data class LogEntry(
    val type: LogType,
    val message: String,
    val response: String,
    val timestamp: String,
)