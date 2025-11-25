package com.example.gogobus.ui.presentation.summary.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.SurfaceCard
import com.example.gogobus.ui.theme.SurfaceCardBorder
import com.example.gogobus.ui.theme.SurfaceLight

@Composable
fun ItemSummaryTripLoading(
    modifier: Modifier = Modifier
) {
    // 🔹 Animación shimmer
    val transition = rememberInfiniteTransition(label = "shimmerSummary")
    val alphaAnim = transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(900),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alphaAnimSummary"
    )

    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.5f),
        Color.LightGray.copy(alpha = 0.3f)
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceLight),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {

            // 🔸 Título + duración
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(160.dp)
                        .height(22.dp)
                        .background(
                            brush = Brush.linearGradient(shimmerColors),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .alpha(alphaAnim.value)
                )
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(22.dp)
                        .background(
                            brush = Brush.linearGradient(shimmerColors),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .alpha(alphaAnim.value)
                )
            }

            // Divider
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(BackgroundLight)
            )

            // 🔸 Origen → Destino
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Origen
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(
                        modifier = Modifier
                            .width(90.dp)
                            .height(18.dp)
                            .background(
                                brush = Brush.linearGradient(shimmerColors),
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(14.dp)
                            .background(
                                brush = Brush.linearGradient(shimmerColors),
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                }

                // Icono ruta
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            brush = Brush.linearGradient(shimmerColors),
                            shape = RoundedCornerShape(8.dp)
                        )
                )

                // Destino
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(
                        modifier = Modifier
                            .width(90.dp)
                            .height(18.dp)
                            .background(
                                brush = Brush.linearGradient(shimmerColors),
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(14.dp)
                            .background(
                                brush = Brush.linearGradient(shimmerColors),
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                }
            }

            // 🔸 Fecha + número de pasajeros
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Fecha
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(14.dp)
                            .background(
                                brush = Brush.linearGradient(shimmerColors),
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(18.dp)
                            .background(
                                brush = Brush.linearGradient(shimmerColors),
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                }

                // Num pasajeros
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(
                        modifier = Modifier
                            .width(90.dp)
                            .height(14.dp)
                            .background(
                                brush = Brush.linearGradient(shimmerColors),
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                    Box(
                        modifier = Modifier
                            .width(30.dp)
                            .height(18.dp)
                            .background(
                                brush = Brush.linearGradient(shimmerColors),
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                }
            }

            // 🔸 Bloque del Bus (placa + precio)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 12.dp)
                    .background(
                        color = SurfaceCard,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = SurfaceCardBorder,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Icono bus + datos
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .background(
                                brush = Brush.linearGradient(shimmerColors),
                                shape = RoundedCornerShape(50.dp)
                            )
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Box(
                            modifier = Modifier
                                .width(70.dp)
                                .height(14.dp)
                                .background(
                                    brush = Brush.linearGradient(shimmerColors),
                                    shape = RoundedCornerShape(6.dp)
                                )
                        )
                        Box(
                            modifier = Modifier
                                .width(90.dp)
                                .height(16.dp)
                                .background(
                                    brush = Brush.linearGradient(shimmerColors),
                                    shape = RoundedCornerShape(6.dp)
                                )
                        )
                    }
                }

                // Precio
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(24.dp)
                        .background(
                            brush = Brush.linearGradient(shimmerColors),
                            shape = RoundedCornerShape(8.dp)
                        )
                )
            }
        }
    }
}
