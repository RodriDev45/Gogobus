package com.example.gogobus.ui.presentation.tripdetail.components

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
import androidx.compose.foundation.layout.size
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
import com.example.gogobus.domain.model.PassengerRequest
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.AlertAccent
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.ErrorText
import com.example.gogobus.ui.theme.GogobusTheme
import com.example.gogobus.ui.theme.OrangeSecondary
import com.example.gogobus.ui.theme.SurfaceLight
import com.example.gogobus.ui.theme.TextDark
import com.example.gogobus.ui.theme.TextGray
import com.example.gogobus.ui.theme.TextPlaceholder

@Composable
fun PassengersTrips(
    modifier: Modifier = Modifier,
    passengerRequests: List<PassengerRequest> = emptyList(),
    onDeleted: (PassengerRequest) -> Unit = {}
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
                    text = "Pasajeros",
                    color = TextDark,
                    style = AppTypography.body14SemiBold
                )

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
                        painter = painterResource(R.drawable.persons_24),
                        contentDescription = null,
                        tint = TextPlaceholder,
                    )

                    Text(
                        text = "${passengerRequests.size} personas",
                        color = TextDark,
                        style = AppTypography.body14SemiBold
                    )
                }
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
                if(passengerRequests.isEmpty()){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.paste_off_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp),
                            tint = TextGray
                        )
                        Text(
                            text = "No hay pasajeros añadidos",
                            style = AppTypography.body14SemiBold,
                            color = TextGray
                        )
                    }
                }else{
                    passengerRequests.forEach {
                        ItemPassenger(
                            passengerRequest = it,
                            onDeleted = onDeleted
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun ItemPassenger(
    modifier: Modifier = Modifier,
    passengerRequest: PassengerRequest,
    onDeleted: (PassengerRequest) -> Unit = {}
) {
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
                    text = passengerRequest.dni,
                    color = TextPlaceholder,
                    style = AppTypography.body12Medium
                )
                Text(
                    text = passengerRequest.fullName,
                    color = TextDark,
                    style = AppTypography.body14SemiBold
                )
            }
        }
        Icon(
            painter = painterResource(R.drawable.delete_24),
            contentDescription = null,
            tint = ErrorText,
            modifier = Modifier
                .clickable{
                    onDeleted(passengerRequest)
                }
        )
    }
}

@Preview
@Composable
private fun PassengersTripsPrev() {
    GogobusTheme {
        Box(
            modifier = Modifier
                .background(BackgroundLight)
                .padding(16.dp)
        ){
            PassengersTrips()
        }
    }

}