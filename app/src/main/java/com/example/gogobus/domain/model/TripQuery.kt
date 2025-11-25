package com.example.gogobus.domain.model

import com.example.gogobus.data.remote.dto.requests.TripQueryDto
import com.example.gogobus.domain.util.DateUtils
import java.time.LocalDate


data class TripQuery(
    val pagination: PaginationQuery,
    val origin: Int,
    val destination: Int,
    val dateFrom: LocalDate? = null,
    val dateTo: LocalDate? = null,
    val date: LocalDate? = null
){
    fun toEntity() = TripQueryDto(
        pagination = pagination.toEntity(),
        origin = origin,
        destination = destination,
        dateFrom = if(dateFrom != null) DateUtils.formatLocalDate(dateFrom, "yyyy-MM-dd") else null,
        dateTo = if(dateTo != null) DateUtils.formatLocalDate(dateTo, "yyyy-MM-dd") else null,
        date = if(date != null) DateUtils.formatLocalDate(date, "yyyy-MM-dd") else null,
    )
}
