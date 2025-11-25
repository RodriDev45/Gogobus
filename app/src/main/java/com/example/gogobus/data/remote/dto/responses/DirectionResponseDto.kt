package com.example.gogobus.data.remote.dto.responses

data class DirectionResponseDto(
    val routes: List<RouteDto>
)

data class RouteDto(
    val overview_polyline: OverviewPolylineDto
)

data class OverviewPolylineDto(
    val points: String
)