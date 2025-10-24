package com.example.gogobus.ui.presentation.search.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

@Composable
fun ItemTripLoading(
    modifier: Modifier = Modifier
) {
    // 🔹 Animación shimmer
    val transition = rememberInfiniteTransition(label = "shimmer")
    val alphaAnim = transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alphaAnim"
    )

    val shimmerColor = listOf(
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.5f),
        Color.LightGray.copy(alpha = 0.3f)
    )

    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceLight),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(2.dp)
        ) {
            // 🟦 Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = BackgroundLight,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(BluePrimary, RoundedCornerShape(100.dp))
                            .alpha(alphaAnim.value)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
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

                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(14.dp)
                        .background(
                            brush = Brush.linearGradient(shimmerColor),
                            shape = RoundedCornerShape(6.dp)
                        )
                )
            }

            // 🕒 Horarios
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(3) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(14.dp)
                                .background(
                                    brush = Brush.linearGradient(shimmerColor),
                                    shape = RoundedCornerShape(6.dp)
                                )
                        )
                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .height(10.dp)
                                .background(
                                    brush = Brush.linearGradient(shimmerColor),
                                    shape = RoundedCornerShape(6.dp)
                                )
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(BackgroundLight)
            )

            // 💺 Asientos y Precio
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
                        .width(60.dp)
                        .height(20.dp)
                        .background(
                            brush = Brush.linearGradient(shimmerColor),
                            shape = RoundedCornerShape(8.dp)
                        )
                )
            }
        }
    }
}