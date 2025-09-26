package com.example.gogobus.ui.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    // Nota: Aunque el estado se usa aquí, lo mantendremos simple para la UI.
    val state by viewModel.uiState.collectAsState()

    // 1. Usar Scaffold como Contenedor Principal
    Scaffold(
        // ✅ Aplica el color de fondo de la aplicación (BackgroundLight) de tu tema
        containerColor = MaterialTheme.colorScheme.background,

        // 2. Define la Barra de Navegación Inferior (Debes crear este componente después)
        bottomBar = { GogobusBottomBar() }

    ) { paddingValues ->

        // 3. Contenido Principal Desplazable
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues) // IMPORTANTE: Usa el padding del Scaffold para no ocultar contenido
        ) {

            // Item 1: El Header (Hola, Rodrigo Mauricio 👋)
            item {
                HomeHeader()
            }

            // Item 2: La Tarjeta de Búsqueda
            item {
                SearchFormCard()
            }

            // Item 3: La Sección de Beneficios/Puntos (la pequeña tarjeta azul)
            item {
                Spacer(modifier = Modifier.height(16.dp))
                // Tendrás que crear un componente como BenefitCard()
                // Por ahora, usaremos un simple Spacer para separar.
                // Aquí iría tu código de la tarjeta de puntos.
            }

            // Item 4: La Sección de Historial
            item {
                Spacer(modifier = Modifier.height(24.dp))
                HistorySection()
            }

            // Nota: Mantenemos tu lista original por si la necesitas para datos del ViewModel
            items(state.posts){ post ->
                Text(text = post.userId.toString())
            }
        }
    }
}

// 🛑 Componente Placeholder: Debes definirlo en HomeComponents.kt o en otro archivo.
@Composable
fun HistorySection() {
    // Implementación mínima para evitar el error de "unresolved reference"
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Historial",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        // ... aquí iría el resto del diseño del historial
    }
}

// 🛑 Componente Placeholder: Debes definirlo en un archivo de navegación global.
@Composable
fun GogobusBottomBar() {
    // Implementación mínima para evitar el error de "unresolved reference"
    // Usualmente se define en el paquete ui/navigation
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        content = { /* Iconos de navegación */ }
    )
}