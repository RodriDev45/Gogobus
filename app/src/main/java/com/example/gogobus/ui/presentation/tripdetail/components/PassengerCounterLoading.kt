package com.example.gogobus.ui.presentation.tripdetail.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.ui.theme.*

@Composable
fun PassengerCounterLoading(
    modifier: Modifier = Modifier
) {
    // 🔹 Animación shimmer
    val transition = rememberInfiniteTransition(label = "shimmerPassengerCounter")
    val alphaAnim = transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alphaAnimPassengerCounter"
    )

    val shimmerColor = listOf(
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.5f),
        Color.LightGray.copy(alpha = 0.3f)
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 🔸 Header: "Número de pasajeros" + contador
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Etiqueta
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(16.dp)
                    .background(
                        brush = Brush.linearGradient(shimmerColor),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .alpha(alphaAnim.value)
            )

            // Caja del contador
            Row(
                modifier = Modifier
                    .background(
                        color = SurfaceCard,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = SurfaceCardBorder,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(horizontal = 6.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(24.dp, 20.dp)
                            .background(
                                brush = Brush.linearGradient(shimmerColor),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .alpha(alphaAnim.value)
                    )
                }
            }
        }

        // 🔸 Botón "Reservar Ticket"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .background(
                    brush = Brush.linearGradient(shimmerColor),
                    shape = RoundedCornerShape(8.dp)
                )
                .alpha(alphaAnim.value)
        )
    }
}

@Preview
@Composable
private fun PassengerCounterLoadingPrev() {
    GogobusTheme {
        Box(
            modifier = Modifier
                .background(BackgroundLight)
                .padding(16.dp)
        ) {
            PassengerCounterLoading()
        }
    }
}
