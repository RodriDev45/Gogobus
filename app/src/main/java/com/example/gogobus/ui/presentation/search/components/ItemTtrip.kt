package com.example.gogobus.ui.presentation.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.R
import com.example.gogobus.domain.model.TripSearch
import com.example.gogobus.domain.util.DateUtils
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.BluePrimary
import com.example.gogobus.ui.theme.GogobusTheme
import com.example.gogobus.ui.theme.SurfaceLight
import com.example.gogobus.ui.theme.TextDark
import com.example.gogobus.ui.theme.TextPlaceholder

@Composable
fun ItemTrip(
    modifier: Modifier = Modifier,
    onClick: (trip: TripSearch) -> Unit = {},
    trip: TripSearch
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable{
                onClick(trip)
            },
        colors = CardDefaults.cardColors(
            containerColor = SurfaceLight
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = BackgroundLight,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.bus_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .background(
                                color = BluePrimary,
                                shape = RoundedCornerShape(100.dp)
                            )
                            .padding(4.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Placa del Bus",
                            color = TextPlaceholder,
                            style = AppTypography.body12Medium
                        )
                        Text(
                            text = trip.plateBus,
                            color = TextDark,
                            style = AppTypography.body14SemiBold
                        )
                    }
                }
                Row {
                    Text(
                        text = "${trip.origin.name} → ${trip.destination.name}",
                        color = TextPlaceholder,
                        style = AppTypography.body16SemiBold
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = DateUtils.formatHoursToString(trip.departureTime),
                        color = TextDark,
                        style = AppTypography.body16SemiBold
                    )
                    Text(
                        text = "Salida",
                        color = TextPlaceholder,
                        style = AppTypography.body12Medium
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            color = BackgroundLight,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(vertical = 6.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = DateUtils.calculateTripDuration(trip.departureTime, trip.arrivalTime),
                        color = TextPlaceholder,
                        style = AppTypography.body12Bold
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = DateUtils.formatHoursToString(trip.arrivalTime),
                        color = TextDark,
                        style = AppTypography.body16SemiBold
                    )
                    Text(
                        text = "Salida",
                        color = TextPlaceholder,
                        style = AppTypography.body12Medium
                    )
                }
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).padding(start = 10.dp).background(BackgroundLight))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.seat_24),
                        contentDescription = null,
                        tint = TextPlaceholder
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Asientos Disponibles",
                            color = TextPlaceholder,
                            style = AppTypography.body12Medium
                        )
                        Text(
                            text = trip.seatsAvailable.toString(),
                            color = TextDark,
                            style = AppTypography.body14SemiBold
                        )
                    }
                }
                Text(
                    text = "S/. ${trip.price}",
                    color = BluePrimary,
                    style = AppTypography.headline24SemiBold
                )
            }
        }
    }

}
