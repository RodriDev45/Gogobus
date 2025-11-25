package com.example.gogobus.data.remote.dto.requests


data class TripQueryDto(
    val pagination: PaginationQueryDto,
    val origin: Int,
    val destination: Int,
    val dateFrom: String? = null,
    val dateTo: String? = null,
    val date: String? = null
) {
    fun toQueryMap(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        map.putAll(pagination.toQueryMap())
        map["origin"] = origin
        map["destination"] = destination
        dateFrom?.let { map["date_from"] = it }
        dateTo?.let { map["date_to"] = it }
        date?.let { map["date"] = it }
        println(map)
        return map
    }
}