package com.example.gogobus.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
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
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    // 1) obtiene esquema base (dinámico si está activado en Android 12+)
    val baseScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // 2) aseguramos que *siempre* se usen tus colores de marca (override),
    //    aunque el usuario tenga dynamic color activado
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

    // 3) extra colors (mismos valores para ambos modos aquí; puedes variar si quieres)
    val extraColors = DefaultExtraColors.copy(
        placeholder = TextPlaceholder,
        successText = SuccessText,
        successAccent = SuccessAccent,
        errorAccent = ErrorAccent
    )

    // 4) proveer extras y luego MaterialTheme (que estará dentro del CompositionLocal)
    CompositionLocalProvider(LocalExtraColors provides extraColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }

}