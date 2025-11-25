package com.example.gogobus.ui.presentation.tripdetail.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.SurfaceLight
import com.example.gogobus.ui.theme.GogobusTheme

@Composable
fun PassengersTripsLoading(
    modifier: Modifier = Modifier
) {
    // 🔹 Animación shimmer
    val transition = rememberInfiniteTransition(label = "shimmerPassengers")
    val alphaAnim = transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alphaAnimPassengers"
    )

    val shimmerColor = listOf(
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
            // 🟦 Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(18.dp)
                        .background(
                            brush = Brush.linearGradient(shimmerColor),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .alpha(alphaAnim.value)
                )

                Box(
                    modifier = Modifier
                        .width(90.dp)
                        .height(20.dp)
                        .background(
                            brush = Brush.linearGradient(shimmerColor),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .alpha(alphaAnim.value)
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(BackgroundLight)
            )

            // 👥 Lista de pasajeros (simulada)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(4) { // simulamos 4 pasajeros
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Icono circular
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    brush = Brush.linearGradient(shimmerColor),
                                    shape = RoundedCornerShape(100.dp)
                                )
                                .alpha(alphaAnim.value)
                        )

                        // Textos (DNI y nombre)
                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(90.dp)
                                    .height(10.dp)
                                    .background(
                                        brush = Brush.linearGradient(shimmerColor),
                                        shape = RoundedCornerShape(6.dp)
                                    )
                            )
                            Box(
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(14.dp)
                                    .background(
                                        brush = Brush.linearGradient(shimmerColor),
                                        shape = RoundedCornerShape(6.dp)
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PassengersTripsLoadingPrev() {
    GogobusTheme {
        Box(
            modifier = Modifier
                .background(BackgroundLight)
                .padding(16.dp)
        ) {
            PassengersTripsLoading()
        }
    }
}
