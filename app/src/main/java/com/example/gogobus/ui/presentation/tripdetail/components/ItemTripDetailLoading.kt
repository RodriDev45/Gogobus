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
import com.example.gogobus.ui.theme.BluePrimary
import com.example.gogobus.ui.theme.SurfaceLight
import com.example.gogobus.ui.theme.AlertAccent
import com.example.gogobus.ui.theme.AlertText
import com.example.gogobus.ui.theme.GogobusTheme

@Composable
fun ItemTripDetailLoading(
    modifier: Modifier = Modifier
) {
    // 🔹 Animación shimmer
    val transition = rememberInfiniteTransition(label = "shimmerDetail")
    val alphaAnim = transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alphaAnimDetail"
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
        Column(modifier = Modifier.padding(2.dp)) {
            // 🔸 Fecha y duración
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(2) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(20.dp)
                            .background(
                                brush = Brush.linearGradient(shimmerColor),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .alpha(alphaAnim.value)
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(BackgroundLight)
            )

            // 🔸 Vehículo y Placa
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(2) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Box(
                            modifier = Modifier
                                .width(80.dp)
                                .height(12.dp)
                                .background(
                                    brush = Brush.linearGradient(shimmerColor),
                                    shape = RoundedCornerShape(6.dp)
                                )
                        )
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(14.dp)
                                .background(
                                    brush = Brush.linearGradient(shimmerColor),
                                    shape = RoundedCornerShape(6.dp)
                                )
                        )
                    }
                }
            }

            // 🔸 Ruta
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(3) { // origen, ícono, destino
                    Box(
                        modifier = Modifier
                            .width(if (it == 1) 32.dp else 80.dp)
                            .height(if (it == 1) 32.dp else 20.dp)
                            .background(
                                brush = Brush.linearGradient(shimmerColor),
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                }
            }

            // 🔸 Advertencia
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 12.dp)
                    .background(AlertAccent, RoundedCornerShape(6.dp))
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(AlertText, RoundedCornerShape(6.dp))
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .background(
                            brush = Brush.linearGradient(shimmerColor),
                            shape = RoundedCornerShape(6.dp)
                        )
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(BackgroundLight)
            )

            // 🔸 Asientos y Precio
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                brush = Brush.linearGradient(shimmerColor),
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
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
                                .width(40.dp)
                                .height(14.dp)
                                .background(
                                    brush = Brush.linearGradient(shimmerColor),
                                    shape = RoundedCornerShape(6.dp)
                                )
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(24.dp)
                        .background(
                            brush = Brush.linearGradient(shimmerColor),
                            shape = RoundedCornerShape(8.dp)
                        )
                )
            }
        }
    }
}

@Preview
@Composable
private fun ItemTripDetailLoadingPrev() {
    GogobusTheme {
        Box(
            modifier = Modifier
                .background(BackgroundLight)
                .padding(16.dp)
        ) {
            ItemTripDetailLoading()
        }
    }
}
