package com.example.gogobus.ui.presentation.home.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gogobus.R
import com.example.gogobus.domain.model.Location
import com.example.gogobus.ui.components.buttons.ButtonPrimary
import com.example.gogobus.ui.components.inputs.LabeledDatePicker
import com.example.gogobus.ui.components.inputs.LabeledDropdown
import com.example.gogobus.ui.components.inputs.LabeledTextField
import com.example.gogobus.ui.presentation.home.LocationsViewModel
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.GogobusTheme
import com.example.gogobus.ui.theme.OrangeSecondary
import com.example.gogobus.ui.theme.TextPlaceholder
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusTicketForm(
    modifier: Modifier = Modifier,
    origin: Location?,
    destination: Location?,
    date: LocalDate,
    onOriginChange: (Location?) -> Unit,
    onDestinationChange: (Location?) -> Unit,
    onDateChange: (LocalDate) -> Unit,
    onSearchClick: () -> Unit,
    locationViewModel: LocationsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var showSheetDeparture by remember { mutableStateOf(false) }
    var showSheetDestino by remember { mutableStateOf(false) }

    val uiState by locationViewModel.uiState.collectAsState()


    LaunchedEffect(Unit) {
        locationViewModel.loadData()
    }

    SheetListLocations(
        title = "Selecciona el Punto de Salida",
        onDismissRequest = { showSheetDeparture = false },
        itemSelected = origin,
        locations = uiState.locations,
        onSelected = onOriginChange,
        loading = uiState.isLoading,
        onLoadMore = {
            locationViewModel.loadData()
        },
        hasMore = uiState.hasMore,
        showSheet = showSheetDeparture
    )

    SheetListLocations(
        title = "Selecciona el Punto de Destino",
        onDismissRequest = { showSheetDestino = false },
        itemSelected = destination,
        locations = uiState.locations,
        onSelected = onDestinationChange,
        loading = uiState.isLoading,
        onLoadMore = {
            locationViewModel.loadData()
        },
        hasMore = uiState.hasMore,
        showSheet = showSheetDestino
    )

    Card(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .shadow(elevation = 12.dp, shape = RoundedCornerShape(24.dp), ambientColor = Color.Black.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Form Title
            Text(
                text = "Complete the form below to purchase GOGOBUS tickets",
                style = AppTypography.body14Regular,
                color = Color.Gray
            )

            // Departure Point
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd // El botón se coloca al final (derecha)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Salida
                    LabeledTextField(
                        onClick = {
                            showSheetDeparture = true

                        },
                        focused = showSheetDeparture,
                        label = "Salida",
                        value = origin?.name ?: "",
                        readOnly = true,
                        onValueChange = { },
                        placeholder = "Selecciona una salida",
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.gps_fixed_20),
                                contentDescription = null,
                                tint = TextPlaceholder
                            )
                        },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(
                                    R.drawable.arrow_drop_down_24
                                ),
                                contentDescription = null,
                                tint = TextPlaceholder,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    )

                    // Destino
                    LabeledTextField(
                        onClick = {
                            showSheetDestino = true
                        },
                        focused = showSheetDestino,
                        label = "Destino",
                        value = destination?.name ?: "",
                        readOnly = true,
                        onValueChange = { },
                        placeholder = "Selecciona un destino",
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.gps_fixed_20),
                                contentDescription = null,
                                tint = TextPlaceholder
                            )
                        },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(
                                    R.drawable.arrow_drop_down_24
                                ),
                                contentDescription = null,
                                tint = TextPlaceholder,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    )
                }

                // Swap Button sobrepuesto en el medio
                IconButton(
                    onClick = {
                        // Intercambia las ciudades
                        onOriginChange(destination)
                        onDestinationChange(origin)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterEnd) // centrado verticalmente
                        .offset(x = (-8).dp) // ajusta la posición un poco hacia la izquierda si deseas
                        .clip(CircleShape)
                        .background(OrangeSecondary)
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.SwapVert,
                        contentDescription = "Swap",
                        tint = Color.White
                    )
                }
            }
            // Date Picker
            LabeledDatePicker(
                label = "Fecha",
                selectedDate = date,
                onDateSelected = { onDateChange(it) },
            )

            // Search Button
            ButtonPrimary(
                text = "Buscar Viaje",
                onClick = onSearchClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
