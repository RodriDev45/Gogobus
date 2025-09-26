package com.example.gogobus.ui.theme

import androidx.compose.runtime.Composable

object ExtendedTheme {
    val extraColors: ExtraColors
        @Composable
        get() = LocalExtraColors.current
}