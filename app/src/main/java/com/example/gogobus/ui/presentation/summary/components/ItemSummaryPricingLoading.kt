package com.example.gogobus.ui.presentation.summary.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ItemSummaryPricingLoading(
    modifier: Modifier = Modifier
) {
    // 🔹 Animación shimmer
    val transition = rememberInfiniteTransition(label = "shimmerPricing")
    val alphaAnim = transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(900),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alphaAnimPricing"
    )

    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.5f),
        Color.LightGray.copy(alpha = 0.3f)
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // 🔸 Línea "Ticket (1x)"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(18.dp)
                    .background(
                        brush = Brush.linearGradient(shimmerColors),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .alpha(alphaAnim.value)
            )
            Box(
                modifier = Modifier
                    .width(70.dp)
                    .height(18.dp)
                    .background(
                        brush = Brush.linearGradient(shimmerColors),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .alpha(alphaAnim.value)
            )
        }

        // 🔸 Línea "Tarifa de servicio"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .height(18.dp)
                    .background(
                        brush = Brush.linearGradient(shimmerColors),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .alpha(alphaAnim.value)
            )
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(18.dp)
                    .background(
                        brush = Brush.linearGradient(shimmerColors),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .alpha(alphaAnim.value)
            )
        }

        // 🔸 Línea "Total a pagar"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(20.dp)
                    .background(
                        brush = Brush.linearGradient(shimmerColors),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .alpha(alphaAnim.value)
            )
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(20.dp)
                    .background(
                        brush = Brush.linearGradient(shimmerColors),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .alpha(alphaAnim.value)
            )
        }

        // 🔸 Botón "Pagar Ahora"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(
                    brush = Brush.linearGradient(shimmerColors),
                    shape = RoundedCornerShape(10.dp)
                )
                .alpha(alphaAnim.value)
        )
    }
}
