package com.example.gogobus.ui.presentation.tripdetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.R
import com.example.gogobus.domain.model.TripDetail
import com.example.gogobus.domain.util.DateUtils
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.AlertAccent
import com.example.gogobus.ui.theme.AlertText
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.BluePrimary
import com.example.gogobus.ui.theme.GogobusTheme
import com.example.gogobus.ui.theme.OrangeSecondary
import com.example.gogobus.ui.theme.SurfaceLight
import com.example.gogobus.ui.theme.TextDark
import com.example.gogobus.ui.theme.TextPlaceholder

@Composable
fun ItemTripDetail(
    modifier: Modifier = Modifier,
    tripDetail: TripDetail
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceLight
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(2.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = TextPlaceholder,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(vertical = 4.dp, horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.calendar_month_24),
                        contentDescription = null,
                        tint = TextPlaceholder,
                    )

                    Text(
                        text = DateUtils.formatLocalDate(tripDetail.departureTime.toLocalDate()),
                        color = TextDark,
                        style = AppTypography.body14SemiBold
                    )
                }

                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = TextPlaceholder,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(vertical = 4.dp, horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.access_time_24),
                        contentDescription = null,
                        tint = TextPlaceholder,
                    )

                    Text(
                        text = DateUtils.calculateTripDuration(tripDetail.departureTime, tripDetail.arrivalTime),
                        color = TextDark,
                        style = AppTypography.body14SemiBold
                    )
                }
            }
            Spacer(modifier.fillMaxWidth().height(2.dp).padding(horizontal = 10.dp).background(BackgroundLight))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Vehículo",
                        color = TextPlaceholder,
                        style = AppTypography.body12Medium
                    )
                    Text(
                        text = tripDetail.bus.model,
                        color = TextDark,
                        style = AppTypography.body14SemiBold
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Placa del Bus",
                        color = TextPlaceholder,
                        style = AppTypography.body12Medium
                    )
                    Text(
                        text = tripDetail.bus.plate,
                        color = TextDark,
                        style = AppTypography.body14SemiBold,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = tripDetail.origin.name,
                        color = TextDark,
                        style = AppTypography.body16SemiBold
                    )
                    Text(
                        text = DateUtils.formatHoursToString(tripDetail.departureTime),
                        color = TextPlaceholder,
                        style = AppTypography.body12Medium
                    )
                }
                Icon(
                    painter = painterResource(R.drawable.route_24),
                    contentDescription = null,
                    tint = BluePrimary
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = tripDetail.destination.name,
                        color = TextDark,
                        style = AppTypography.body16SemiBold
                    )
                    Text(
                        text = DateUtils.formatHoursToString(tripDetail.arrivalTime),
                        color = TextPlaceholder,
                        style = AppTypography.body12Medium
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 10.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(AlertAccent)
                    .drawBehind {
                        val strokeWidth = 4.dp.toPx()
                        drawRect(
                            color = AlertText,
                            topLeft = Offset(0f, 0f),
                            size = Size(strokeWidth, size.height)
                        )
                    }
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.report_24),
                    contentDescription = null,
                    tint = AlertText,
                    modifier = Modifier.padding(start = 6.dp)
                )
                Text(
                    text = "La duración del viaje es un tiempo estimado que puede variar según el tráfico.",
                    color = TextDark,
                    style = AppTypography.body12Regular,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(end = 6.dp)
                )
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).padding(start = 10.dp).background(BackgroundLight))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 12.dp),
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
                            text = tripDetail.availableSeats.size.toString(),
                            color = TextDark,
                            style = AppTypography.body14SemiBold
                        )
                    }
                }
                Text(
                    text = "S/. ${tripDetail.price}",
                    color = BluePrimary,
                    style = AppTypography.headline24SemiBold
                )
            }
        }
    }
}
