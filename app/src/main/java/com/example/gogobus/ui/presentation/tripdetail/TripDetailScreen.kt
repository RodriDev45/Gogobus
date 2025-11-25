package com.example.gogobus.ui.presentation.tripdetail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gogobus.R
import com.example.gogobus.domain.model.BusLayout
import com.example.gogobus.ui.components.buttons.ButtonPrimary
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.presentation.tripdetail.components.FormPassenger
import com.example.gogobus.ui.presentation.tripdetail.components.ItemTripDetail
import com.example.gogobus.ui.presentation.tripdetail.components.ItemTripDetailLoading
import com.example.gogobus.ui.presentation.tripdetail.components.PassengerCounterLoading
import com.example.gogobus.ui.presentation.tripdetail.components.PassengersTrips
import com.example.gogobus.ui.presentation.tripdetail.components.PassengersTripsLoading
import com.example.gogobus.ui.presentation.tripdetail.components.SeatsBus
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.BluePrimary
import com.example.gogobus.ui.theme.OrangeSecondary
import com.example.gogobus.ui.theme.SurfaceCard
import com.example.gogobus.ui.theme.SurfaceCardBorder
import com.example.gogobus.ui.theme.TextDark
import com.example.gogobus.ui.theme.TextPlaceholder

@Composable
fun TripDetailScreen(
    id: Int,
    modifier: Modifier = Modifier,
    viewmodel: TripDetailViewModel = hiltViewModel(),
    onNavigateToSummary: (bookingId: Int, tripId: Int) -> Unit
) {
    var showSeatsModal by remember { mutableStateOf(false) }
    var showFormModal by remember { mutableStateOf(false) }
    val uiState by viewmodel.tripDetailState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewmodel.loadTripDetail(id)
        viewmodel.tripDetailEvent.collect { event ->
            when(event){
                is TripDetailUiEvent.OnNavigateToSummary -> {
                    onNavigateToSummary(event.bookingId, event.tripId)
                }
                is TripDetailUiEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()

    ) {
        if(uiState.trip != null){
            FormPassenger(
                showForm = showFormModal,
                onDismissRequest = {
                    showFormModal = false
                },
                onPassengerChange = {
                    viewmodel.addPassenger(it)
                    showFormModal = false
                },
                passengerRequest = viewmodel.getPassengerSelect()
            )
        }

        if(showSeatsModal && uiState.trip != null){
            Dialog(
                onDismissRequest = {
                    showSeatsModal = false
                },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    SeatsBus(
                        onSeatSelect = {
                            viewmodel.setPassengerSelect(it.number)
                            showFormModal = true
                            showSeatsModal = false
                        },
                        busLayout = BusLayout(
                            layoutConfig = uiState.trip!!.bus.layoutConfig,
                            seats = uiState.trip!!.bus.seats
                        ),
                        seatsAvailable = uiState.seatsAvailable,
                        seatsOccupied = uiState.seatsOccupied,
                        seatsSelected = uiState.seatsSelected

                    )

                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.chevron_left_24),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .clickable {

                        }
                        .background(
                            color = OrangeSecondary,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .padding(2.dp)
                )
                Text(
                    text = "Detalle del viaje",
                    style = AppTypography.body14SemiBold,
                    color = TextDark
                )
            }

            Icon(
                painter = painterResource(R.drawable.share_24),
                contentDescription = null,
                tint = OrangeSecondary,
                modifier = Modifier
                    .clickable {

                    }
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = BackgroundLight,
                )
                .padding(horizontal = 24.dp),
        ) {
            item {
                Spacer(Modifier.height(24.dp))
            }
            item {
                if(uiState.isLoading){
                    ItemTripDetailLoading(
                        modifier = Modifier
                    )
                }else{
                    if(uiState.trip != null){
                        ItemTripDetail(
                            modifier = Modifier,
                            tripDetail = uiState.trip!!
                        )
                    }
                }

            }
            item {
                Spacer(Modifier.height(24.dp))
            }
            item {
                if(uiState.isLoading){
                    PassengersTripsLoading(
                        modifier = Modifier
                    )
                }else{
                    if(uiState.trip != null){
                        PassengersTrips(
                            modifier = Modifier,
                            passengerRequests = uiState.passengerRequests,
                            onDeleted = {
                                viewmodel.deletePassenger(it.seatNumber)
                            }
                        )
                    }
                }

            }
            item {
                Spacer(Modifier.height(24.dp))
            }


        }
        if(uiState.isLoading){
            PassengerCounterLoading()
        }else{
            if(uiState.trip != null){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Numero de pasajeros",
                            style = AppTypography.body14Medium,
                            color = TextDark
                        )

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
                                .padding(horizontal = 4.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.indeterminate_check_box_24),
                                contentDescription = null,
                                tint = TextPlaceholder
                            )
                            Text(
                                text = uiState.passengerRequests.size.toString(),
                                style = AppTypography.body16Bold,
                                color = TextDark
                            )
                            Icon(
                                painter = painterResource(R.drawable.add_box_24),
                                contentDescription = null,
                                tint = BluePrimary,
                                modifier = Modifier
                                    .clickable{
                                        showSeatsModal = true
                                    }
                            )
                        }
                    }
                    ButtonPrimary(
                        text = "Reservar Ticket",
                        onClick = {
                            viewmodel.createBooking()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = uiState.passengerRequests.isNotEmpty() && !uiState.isLoadingCreateBooking,
                        loading = uiState.isLoadingCreateBooking
                    )
                }
            }
        }

    }
}

