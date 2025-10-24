package com.example.gogobus.ui.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.GogobusTheme
import com.example.gogobus.ui.theme.OrangeSecondary

@Composable
fun HistorySection(
    modifier: Modifier = Modifier,
    hasHistory: Boolean,
    historyCount: Int
) {
    Column(modifier = modifier.padding(horizontal = 24.dp, vertical = 24.dp)) {
        // Encabezado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(text = "History", style = AppTypography.body18SemiBold.copy(fontWeight = FontWeight.ExtraBold), color = Color.Black)
                if (historyCount > 0) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.08f))
                            .padding(horizontal = 9.dp, vertical = 3.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("$historyCount", style = AppTypography.body12SemiBold, color = Color.DarkGray.copy(alpha = 0.8f))
                    }
                }
            }
            TextButton(onClick = { /* View All */ }) {
                Text(text = "View All", style = AppTypography.body14SemiBold, color = OrangeSecondary)
                Spacer(modifier = Modifier.padding(start = 4.dp))
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null, modifier = Modifier.size(14.dp), tint = OrangeSecondary)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Contenido (Estado vacío o lista de historial)
        if (!hasHistory) {
            EmptyHistoryView()
        } else {
            // Aquí iría la lista del historial de viajes
        }
    }
}

@Composable
private fun EmptyHistoryView() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Icono de estado vacío (aproximación)
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.Outlined.Folder,
                contentDescription = null,
                modifier = Modifier.size(90.dp),
                tint = Color(0xFFDCEBFF) // Azul muy claro para la carpeta
            )
            Text("x x", style = AppTypography.body14Bold, modifier = Modifier.offset(y = (-8).dp))
            Text("~", style = AppTypography.headline24Bold, color = Color.Gray.copy(alpha = 0.5f), modifier = Modifier.offset(y = 8.dp))
            Icon(
                imageVector = Icons.Outlined.QuestionMark,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 10.dp, y = (-10).dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFD180))
                    .padding(2.dp)
                    .size(18.dp),
                tint = Color.White
            )
        }
        
        Text(text = "There is nothing here.", style = AppTypography.body18SemiBold, color = Color.Black)
        Text(
            text = "You do not have any trip history yet, please fill out the form above to start purchasing bus tickets.",
            style = AppTypography.body14Regular,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF4F4F4)
@Composable
fun HistorySectionPreview() {
    GogobusTheme {
        HistorySection(hasHistory = false, historyCount = 0)
    }
}
