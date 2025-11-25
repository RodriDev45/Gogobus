package com.example.gogobus.ui.presentation.summary.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.R
import com.example.gogobus.domain.model.Passenger
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.AlertAccent
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.ErrorText
import com.example.gogobus.ui.theme.GogobusTheme
import com.example.gogobus.ui.theme.OrangeSecondary
import com.example.gogobus.ui.theme.SurfaceLight
import com.example.gogobus.ui.theme.TextDark
import com.example.gogobus.ui.theme.TextPlaceholder

@Composable
fun ItemSummaryPassengers(
    modifier: Modifier = Modifier,
    passengers: List<Passenger> = emptyList()
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Información de Pasajeros",
                    color = TextDark,
                    style = AppTypography.body16SemiBold
                )
            }
            Spacer(modifier
                .fillMaxWidth()
                .height(2.dp)
                .padding(horizontal = 10.dp)
                .background(BackgroundLight))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                passengers.forEach { passenger ->
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.person_24),
                                contentDescription = null,
                                tint = OrangeSecondary,
                                modifier = Modifier
                                    .background(
                                        color = AlertAccent,
                                        shape = RoundedCornerShape(100.dp)
                                    )
                                    .padding(8.dp)
                            )

                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = passenger.fullName,
                                    color = TextDark,
                                    style = AppTypography.body14SemiBold
                                )
                                Text(
                                    text = passenger.dni,
                                    color = TextPlaceholder,
                                    style = AppTypography.body12Medium
                                )
                            }
                        }
                        Text(
                            text = "Asiento ${passenger.seatNumber}",
                            color = TextPlaceholder,
                            style = AppTypography.body12Medium
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun ItemSummaryPassengersPrev() {
    GogobusTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundLight)
                .padding(16.dp)
        ){
            ItemSummaryPassengers(
                passengers = listOf(
                    Passenger(
                        id = 1,
                        fullName = "Juan Perez",
                        dni = "12345678",
                        seatNumber = 12
                    )
                )
            )
        }
    }
}