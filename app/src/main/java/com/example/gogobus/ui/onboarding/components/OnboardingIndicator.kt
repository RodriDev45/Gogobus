package com.example.gogobus.ui.onboarding.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// components/OnboardingIndicator.kt
@Composable
fun OnboardingIndicator(currentPage: Int, totalPages: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(totalPages) { index ->
            val width by animateDpAsState(
                targetValue = if (index == currentPage) 32.dp else 8.dp,
                animationSpec = tween(durationMillis = 300),
                label = "indicatorWidth"
            )

            val color = if (index == currentPage) Color.White else Color.White.copy(alpha= 0.5f)
                    Box(
                modifier = Modifier
                    .padding(4.dp)
                    .height(8.dp)
                    .width(width)
                    .clip(RoundedCornerShape(50))
                    .background(color)
            )
        }
    }
}
