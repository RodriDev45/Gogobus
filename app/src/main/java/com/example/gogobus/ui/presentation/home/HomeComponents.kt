// Archivo: com.example.gogobus.ui.presentation.home/HomeComponents.kt

package com.example.gogobus.ui.presentation.home

// --- Imports Limpios y Necesarios ---
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Iconos
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector


// ---------------------------------------------------------------------
// 1. Componente de Encabezado (HomeHeader) - (SIN CAMBIOS, YA ERA CORRECTO)
// ---------------------------------------------------------------------
@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    userName: String = "Rodrigo Mauricio"
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        // Usa titleMedium y onBackground
        Text(
            text = "Hola, $userName 👋",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Usa headlineSmall y onBackground
        Text(
            text = "Viaje seguro y cómodo a tu destino",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

// ---------------------------------------------------------------------
// 2. Componente de Botón Principal (SearchButton) - (SIN CAMBIOS, YA ERA CORRECTO)
// ---------------------------------------------------------------------
@Composable
fun SearchButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(56.dp),
        shape = RoundedCornerShape(8.dp),
        // Usa el rol PRIMARY (OrangeGogobus)
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        // Usa el estilo labelLarge y onPrimary (White)
        Text(
            "Buscar Boleto",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

// ---------------------------------------------------------------------
// 3. Componente de Tarjeta de Formulario de Búsqueda (SearchFormCard) - (CORREGIDO)
// ---------------------------------------------------------------------
@Composable
fun SearchFormCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        // Usa el rol SURFACE (White)
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Completa el formulario para comprar tus boletos",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo Partida
            LocationField(
                label = "Partida",
                placeholder = "Selecciona el punto de partida",
                icon = Icons.Default.LocationOn
            )
            Spacer(modifier = Modifier.height(8.dp)) // ✅ Corrección en el Spacer

            // Campo Llegada y Botón de Intercambio
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Campo Llegada
                Box(modifier = Modifier.weight(0.75f)) { // ✅ Corrección: sin "weight ="
                    LocationField(
                        label = "Llegada",
                        placeholder = "Selecciona el destino",
                        icon = Icons.Default.LocationOn
                    )
                }
                // Botón de Intercambio
                Box(modifier = Modifier.weight(0.25f), contentAlignment = Alignment.Center) {
                    IconButton(onClick = { /* Lógica de intercambio */ }) {
                        Icon(
                            imageVector = Icons.Default.SwapVert,
                            contentDescription = "Intercambiar",
                            tint = MaterialTheme.colorScheme.primary // OrangeGogobus
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp)) // ✅ Corrección en el Spacer

            // Campo de Fecha
            LocationField(
                label = "Fecha",
                placeholder = "dd/mm/yyyy",
                icon = Icons.Default.CalendarToday
            )

            Spacer(modifier = Modifier.height(16.dp)) // ✅ Corrección en el Spacer

            // Botón Principal
            SearchButton(onClick = {})
        }
    }
}

// ---------------------------------------------------------------------
// 4. Componente de Campo de Ubicación (LocationField) - (CORREGIDO)
// ---------------------------------------------------------------------
@Composable
fun LocationField(label: String, placeholder: String, icon: ImageVector) {
    Column {
        // Usa el estilo labelMedium
        Text(text = label, style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                // Usa el color BackgroundLight
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                // Usa onSurfaceVariant (PlaceholderGray o similar)
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = placeholder,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}