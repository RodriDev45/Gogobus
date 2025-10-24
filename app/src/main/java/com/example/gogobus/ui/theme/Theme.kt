package com.example.gogobus.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.gogobus.ui.presentation.home.theme.Typography

private val DarkColorScheme = darkColorScheme(
    primary = BluePrimary,           // Azul marca
    onPrimary = White,               // Texto sobre azul
    secondary = OrangeSecondary,     // Botones, acentos
    onSecondary = White,             // Texto sobre naranja
    tertiary = BlueAccent,           // Extra (ej. íconos, detalles)

    background = BackgroundLight,    // Fondo global
    onBackground = TextDark,         // Texto sobre fondo claro

    surface = SurfaceLight,          // Tarjetas blancas
    onSurface = TextGray,            // Texto sobre surface

    error = ErrorText,               // Texto de error
    onError = White,
)

private val LightColorScheme = lightColorScheme(
    primary = BluePrimary,           // Azul marca
    onPrimary = White,               // Texto sobre azul
    secondary = OrangeSecondary,     // Botones, acentos
    onSecondary = White,             // Texto sobre naranja
    tertiary = BlueAccent,           // Extra (ej. íconos, detalles)

    background = BackgroundLight,    // Fondo global
    onBackground = TextDark,         // Texto sobre fondo claro

    surface = SurfaceLight,          // Tarjetas blancas
    onSurface = TextGray,            // Texto sobre surface

    error = ErrorText,               // Texto de error
    onError = White,
)

@Composable
fun GogobusTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val baseScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val colorScheme = baseScheme.copy(
        primary = BluePrimary,
        onPrimary = White,
        secondary = OrangeSecondary,
        onSecondary = White,
        tertiary = BlueAccent,
        background = if (darkTheme) BluePrimary else BackgroundLight,
        onBackground = if (darkTheme) White else TextDark,
        surface = if (darkTheme) BlueAccent else SurfaceLight,
        onSurface = if (darkTheme) TextPlaceholder else TextGray,
        error = ErrorText,
        onError = White
    )

    val extraColors = DefaultExtraColors.copy(
        placeholder = TextPlaceholder,
        successText = SuccessText,
        successAccent = SuccessAccent,
        errorAccent = ErrorAccent
    )

    // Control de las barras del sistema (Edge-to-Edge)
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()

            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false // Íconos claros para fondo oscuro
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = true // Íconos oscuros para fondo claro
        }
    }

    CompositionLocalProvider(LocalExtraColors provides extraColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }

}