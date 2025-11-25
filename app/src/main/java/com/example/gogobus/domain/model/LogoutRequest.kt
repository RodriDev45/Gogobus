package com.example.gogobus.domain.model

import com.example.gogobus.data.remote.dto.EntityMappable
import com.example.gogobus.data.remote.dto.requests.LogoutRequestDto

data class LogoutRequest(
    val refresh: String
): EntityMappable<LogoutRequestDto>{
    override fun toEntity() = LogoutRequestDto(
        refresh = refresh
    )

}
