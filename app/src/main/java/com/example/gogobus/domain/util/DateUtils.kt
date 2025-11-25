package com.example.gogobus.domain.util

import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtils {

    fun formatLocalDate(date: LocalDate, pattern: String = "dd/MM/yyyy"): String {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return date.format(formatter)
    }

    fun formatStringToLocalDate(dateString: String): LocalDate {
        return Instant.parse(dateString)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }

    fun formatToLocalDateTime(dateString: String): LocalDateTime {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        return LocalDateTime.parse(dateString, formatter)
    }

    fun formatHoursToString(localDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
        return localDateTime.format(formatter)
    }

    fun calculateTripDuration(start: LocalDateTime, end: LocalDateTime): String {
        val duration = Duration.between(start, end)

        val dias = duration.toDays()
        val horas = duration.minusDays(dias).toHours()
        val minutos = duration.minusDays(dias).minusHours(horas).toMinutes()

        return when {
            dias > 0 && horas > 0 -> "${dias}d ${horas}h ${if (minutos > 0) "${minutos}m" else ""}".trim()
            dias > 0 -> if (dias == 1L) "1 día" else "$dias días"
            horas > 0 && minutos > 0 -> "${horas}h ${minutos}m"
            horas > 0 -> if (horas == 1L) "1 hora" else "${horas} horas"
            minutos > 0 -> if (minutos == 1L) "1 minuto" else "${minutos} minutos"
            else -> "Menos de un minuto"
        }
    }
}