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
import com.example.gogobus.ui.theme.SurfaceLight


@Composable
fun ItemSummaryPassengersLoading(
    modifier: Modifier = Modifier,
    placeholderCount: Int = 3
) {
    // 🔹 Animación shimmer
    val transition = rememberInfiniteTransition(label = "shimmerPassengers")
    val alphaAnim = transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(900),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alphaAnimPassengers"
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

            // 🔸 Título
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(180.dp)
                        .height(20.dp)
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

            // 🔸 Contenido (lista de pasajeros en modo carga)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(placeholderCount) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // Izquierda: icono + nombre + dni
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            // Icono persona
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(
                                        brush = Brush.linearGradient(shimmerColors),
                                        shape = RoundedCornerShape(100.dp)
                                    )
                            )

                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {

                                // Nombre
                                Box(
                                    modifier = Modifier
                                        .width(120.dp)
                                        .height(16.dp)
                                        .background(
                                            brush = Brush.linearGradient(shimmerColors),
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                )

                                // DNI
                                Box(
                                    modifier = Modifier
                                        .width(90.dp)
                                        .height(14.dp)
                                        .background(
                                            brush = Brush.linearGradient(shimmerColors),
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                )
                            }
                        }

                        // Derecha: Asiento
                        Box(
                            modifier = Modifier
                                .width(80.dp)
                                .height(16.dp)
                                .background(
                                    brush = Brush.linearGradient(shimmerColors),
                                    shape = RoundedCornerShape(6.dp)
                                )
                        )
                    }
                }
            }
        }
    }
}
