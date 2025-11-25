package com.example.gogobus.ui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gogobus.domain.model.Location
import com.example.gogobus.ui.components.navigation.BottomNavigationBar
import com.example.gogobus.ui.presentation.home.components.BusTicketForm
import com.example.gogobus.ui.presentation.home.components.BuspointCard
import com.example.gogobus.ui.presentation.home.components.HistorySection
import com.example.gogobus.ui.presentation.home.components.HomeTopBar
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.BluePrimary
import java.time.LocalDate

// 1. Componente "Inteligente" o Ruta: Conectado al ViewModel
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavToSearch: (origin: Location, destination: Location, date: LocalDate) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(BackgroundLight)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .background(BluePrimary)
                )
                Box(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()

            ) {
                HomeTopBar(userName = "Maria")
                BusTicketForm(
                    modifier = Modifier,
                    origin = uiState.origin,
                    destination = uiState.destination,
                    date = uiState.date,
                    onOriginChange = { viewModel.updateOrigin(it) },
                    onDestinationChange = { viewModel.updateDestination(it) },
                    onDateChange = { viewModel.updateDate(it) },
                    onSearchClick = {
                        if(uiState.origin != null && uiState.destination != null){
                            onNavToSearch(uiState.origin!!, uiState.destination!!, uiState.date)
                        }
                    },
                )
                Spacer(modifier = Modifier.height(32.dp))
                BuspointCard(
                    modifier = Modifier
                        .clickable{
                        }
                )
                HistorySection(
                    hasHistory = uiState.hasHistory,
                    historyCount = uiState.historyCount
                )
            }
        }

    }
}
