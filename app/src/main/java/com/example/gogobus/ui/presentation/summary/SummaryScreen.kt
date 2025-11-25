package com.example.gogobus.ui.presentation.summary

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gogobus.R
import com.example.gogobus.ui.components.buttons.ButtonPrimary
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.presentation.summary.components.ItemSummaryPassengers
import com.example.gogobus.ui.presentation.summary.components.ItemSummaryPassengersLoading
import com.example.gogobus.ui.presentation.summary.components.ItemSummaryPricingLoading
import com.example.gogobus.ui.presentation.summary.components.ItemSummaryTrip
import com.example.gogobus.ui.presentation.summary.components.ItemSummaryTripLoading
import com.example.gogobus.ui.presentation.tripdetail.components.PassengerCounterLoading
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.BluePrimary
import com.example.gogobus.ui.theme.OrangeSecondary
import com.example.gogobus.ui.theme.SurfaceCard
import com.example.gogobus.ui.theme.SurfaceCardBorder
import com.example.gogobus.ui.theme.TextDark
import com.example.gogobus.ui.theme.TextGray

@Composable
fun SummaryScreen(
    bookingId: Int,
    tripId: Int,
    onNavToPayment: (bookingId: Int, totalAmount: Double) -> Unit,
    modifier: Modifier = Modifier,
    viewmodel: SummaryViewModel = hiltViewModel(),
) {
    val uiState by viewmodel.summaryState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewmodel.loadSummary(bookingId, tripId)
        viewmodel.summaryEvent.collect { event ->
            when(event){
                is SummaryUiEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()

    ) {
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
                    text = "Resumen del viaje",
                    style = AppTypography.body14SemiBold,
                    color = TextDark
                )
            }
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
                    ItemSummaryTripLoading(
                        modifier = Modifier
                    )
                }else{
                    if(uiState.trip != null){
                        ItemSummaryTrip(
                            modifier = Modifier,
                            tripDetail = uiState.trip!!,
                            numberPassengers = uiState.booking!!.passengers.size
                        )
                    }
                }

            }
            item {
                Spacer(Modifier.height(24.dp))
            }
            item {
                if(uiState.isLoading){
                    ItemSummaryPassengersLoading(
                        modifier = Modifier
                    )
                }else{
                    if(uiState.booking != null){
                        ItemSummaryPassengers(
                            modifier = Modifier,
                            passengers = uiState.booking!!.passengers
                        )
                    }
                }

            }
            item {
                Spacer(Modifier.height(24.dp))
            }
        }

        if(uiState.isLoading){
            ItemSummaryPricingLoading()
        }else{
            if(uiState.booking != null){
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
                            text = "Ticket (${uiState.booking!!.passengers.size}x)",
                            style = AppTypography.body14Medium,
                            color = TextDark
                        )

                        Text(
                            text = "S/. ${uiState.booking!!.totalAmount}",
                            style = AppTypography.body14Medium,
                            color = TextGray
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Tarifa de servicio",
                            style = AppTypography.body14Medium,
                            color = TextDark
                        )

                        Text(
                            text = "S/. 0.0",
                            style = AppTypography.body14Medium,
                            color = TextGray
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total a pagar",
                            style = AppTypography.body16Medium,
                            color = TextDark
                        )

                        Text(
                            text = "S/. ${uiState.booking!!.totalAmount}",
                            style = AppTypography.body18SemiBold,
                            color = BluePrimary
                        )
                    }
                    ButtonPrimary(
                        text = "Pagar Ahora",
                        onClick = {
                            onNavToPayment(bookingId, uiState.booking!!.totalAmount)
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}