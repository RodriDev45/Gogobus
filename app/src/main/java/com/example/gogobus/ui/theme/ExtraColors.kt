package com.example.gogobus.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ExtraColors(
    val placeholder: Color,
    val successText: Color,
    val successAccent: Color,
    val errorAccent: Color
)

// Valores por defecto
val DefaultExtraColors =
    ExtraColors(
        placeholder = Color.Unspecified,
        successText = Color.Unspecified,
        successAccent = Color.Unspecified,
        errorAccent = Color.Unspecified
    )

val LocalExtraColors = staticCompositionLocalOf { DefaultExtraColors }
