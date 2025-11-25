package com.example.gogobus.data.remote.dto.requests

data class PaginationQueryDto(
    val pageSize: Int,
    val page: Int
){
    fun toQueryMap(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["page_size"] = pageSize
        map["page"] = page
        return map
    }
}
