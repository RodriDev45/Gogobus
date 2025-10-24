package com.example.gogobus.ui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gogobus.ui.presentation.home.components.BottomNavigationBar
import com.example.gogobus.ui.presentation.home.components.BusTicketForm
import com.example.gogobus.ui.presentation.home.components.BuspointCard
import com.example.gogobus.ui.presentation.home.components.HistorySection
import com.example.gogobus.ui.presentation.home.components.HomeTopBar
import com.example.gogobus.ui.theme.BluePrimary
import com.example.gogobus.ui.theme.GogobusTheme


// 1. Componente "Inteligente" o Ruta: Conectado al ViewModel
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreenContent(
        uiState = uiState,
        onOriginChange = viewModel::updateOrigin,
        onDestinationChange = viewModel::updateDestination,
        onDateChange = viewModel::updateDate,
        onSearchClick = viewModel::searchBuses
    )
}

// 2. Componente "Tonto" o de UI: Solo recibe estado y eventos
@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onOriginChange: (String) -> Unit,
    onDestinationChange: (String) -> Unit,
    onDateChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        },
        containerColor = Color(0xFFF4F4F4) // Use gray as the base background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            contentPadding = paddingValues
        ) {
            // Blue header part
            item {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(BluePrimary)) {
                    HomeTopBar(userName = uiState.userName)
                    Spacer(Modifier.height(130.dp)) // This space will be covered by the top of the form
                }
            }

            // The form, which is pulled up
            item {
                BusTicketForm(
                    modifier = Modifier.offset(y = (-130).dp),
                    origin = uiState.origin,
                    destination = uiState.destination,
                    date = uiState.date,
                    onOriginChange = onOriginChange,
                    onDestinationChange = onDestinationChange,
                    onDateChange = onDateChange,
                    onSearchClick = onSearchClick
                )
            }

            item {
                // Compensate for the form's offset to remove the extra space
                Spacer(modifier = Modifier.height((-130).dp))
                Spacer(modifier = Modifier.height(24.dp)) // The desired space
                BuspointCard()
            }

            item {
                HistorySection(
                    hasHistory = uiState.hasHistory,
                    historyCount = uiState.historyCount
                )
            }
        }
    }
}

// 3. Preview Corregido: Llama al componente de UI con datos falsos
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    GogobusTheme {
        val previewState = HomeUiState(
            userName = "Aruna Dahlia",
            origin = "",
            destination = "",
            date = "",
            hasHistory = false,
            historyCount = 0
        )
        HomeScreenContent(
            uiState = previewState,
            onOriginChange = {},
            onDestinationChange = {},
            onDateChange = {},
            onSearchClick = {}
        )
    }
}
