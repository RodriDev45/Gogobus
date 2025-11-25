package com.example.gogobus.ui.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material.icons.outlined.Redeem
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.GogobusTheme
import com.example.gogobus.ui.theme.OrangeSecondary

@Composable
fun BuspointCard(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(28.dp), // Radio de esquina corregido
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color(0xFFE3F2FD))
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.WorkspacePremium,
                        contentDescription = "Buspoint",
                        tint = Color(0xFF42A5F5), // Azul claro
                        modifier = Modifier.size(24.dp)
                    )
                }
                Column {
                    Text(text = "Buspoint", style = AppTypography.body12Regular, color = Color.Gray)
                    Text(text = "0 pt", style = AppTypography.body16SemiBold)
                }
            }
            OutlinedButton(
                onClick = { /* Redimir */ },
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
            ) {
                Icon(
                    Icons.Outlined.Redeem,
                    contentDescription = null, // decorative
                    modifier = Modifier.size(20.dp),
                    tint = OrangeSecondary
                )
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Text(text = "Redeem", color = OrangeSecondary, style = AppTypography.body14SemiBold)
            }
        }
    }
}

@Preview
@Composable
fun BuspointCardPreview() {
    GogobusTheme {
        BuspointCard(modifier = Modifier.padding(16.dp))
    }
}
