package com.example.gogobus.ui.presentation.search.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gogobus.ui.components.shimmerEffect

@Composable
fun ScheduleItemLoading() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Placeholder para el texto "Horario Disponible"
        Box(
            modifier = Modifier
                .height(16.dp)
                .width(120.dp)
                .shimmerEffect()
        )

        // Placeholder del card con el número

        Box(
            modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 8.dp)
                .width(16.dp)
                .height(16.dp)
                .shimmerEffect()
        )
    }
}
