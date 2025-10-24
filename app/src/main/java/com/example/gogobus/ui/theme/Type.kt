package com.example.gogobus.ui.presentation.home.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.gogobus.R

val Inter = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold)
)

object AppTypography {

    // Base styles (solo cambian tamaño y lineHeight)
    private val base12 = TextStyle(fontFamily = Inter, fontSize = 12.sp, lineHeight = 16.sp)
    private val base14 = TextStyle(fontFamily = Inter, fontSize = 14.sp, lineHeight = 20.sp)
    private val base16 = TextStyle(fontFamily = Inter, fontSize = 16.sp, lineHeight = 24.sp)
    private val base18 = TextStyle(fontFamily = Inter, fontSize = 18.sp, lineHeight = 24.sp)
    private val base24 = TextStyle(fontFamily = Inter, fontSize = 24.sp, lineHeight = 32.sp)
    private val base32 = TextStyle(fontFamily = Inter, fontSize = 32.sp, lineHeight = 40.sp)

    // Variantes 12sp
    val body12Light = base12.copy(fontWeight = FontWeight.Light)
    val body12Regular = base12.copy(fontWeight = FontWeight.Normal)
    val body12Medium = base12.copy(fontWeight = FontWeight.Medium)
    val body12SemiBold = base12.copy(fontWeight = FontWeight.SemiBold)
    val body12Bold = base12.copy(fontWeight = FontWeight.Bold)

    // Variantes 14sp
    val body14Light = base14.copy(fontWeight = FontWeight.Light)
    val body14Regular = base14.copy(fontWeight = FontWeight.Normal)
    val body14Medium = base14.copy(fontWeight = FontWeight.Medium)
    val body14SemiBold = base14.copy(fontWeight = FontWeight.SemiBold)
    val body14Bold = base14.copy(fontWeight = FontWeight.Bold)

    // Variantes 16sp
    val body16Light = base16.copy(fontWeight = FontWeight.Light)
    val body16Regular = base16.copy(fontWeight = FontWeight.Normal)
    val body16Medium = base16.copy(fontWeight = FontWeight.Medium)
    val body16SemiBold = base16.copy(fontWeight = FontWeight.SemiBold)
    val body16Bold = base16.copy(fontWeight = FontWeight.Bold)

    // Variantes 18sp
    val body18Light = base18.copy(fontWeight = FontWeight.Light)
    val body18Regular = base18.copy(fontWeight = FontWeight.Normal)
    val body18Medium = base18.copy(fontWeight = FontWeight.Medium)
    val body18SemiBold = base18.copy(fontWeight = FontWeight.SemiBold)
    val body18Bold = base18.copy(fontWeight = FontWeight.Bold)

    // Variantes 24sp
    val headline24Light = base24.copy(fontWeight = FontWeight.Light)
    val headline24Regular = base24.copy(fontWeight = FontWeight.Normal)
    val headline24Medium = base24.copy(fontWeight = FontWeight.Medium)
    val headline24SemiBold = base24.copy(fontWeight = FontWeight.SemiBold)
    val headline24Bold = base24.copy(fontWeight = FontWeight.Bold)

    // Variantes 32sp
    val headline32Light = base32.copy(fontWeight = FontWeight.Light)
    val headline32Regular = base32.copy(fontWeight = FontWeight.Normal)
    val headline32Medium = base32.copy(fontWeight = FontWeight.Medium)
    val headline32SemiBold = base32.copy(fontWeight = FontWeight.SemiBold)
    val headline32Bold = base32.copy(fontWeight = FontWeight.Bold)
}

val Typography = Typography(

    headlineSmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),

    titleMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),

    labelMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    labelLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    displaySmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),

    titleLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)