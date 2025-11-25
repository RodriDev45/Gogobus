package com.example.gogobus.ui.presentation.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gogobus.R
import com.example.gogobus.domain.model.Location
import com.example.gogobus.domain.model.TripSearch
import com.example.gogobus.domain.util.DateUtils
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.presentation.search.components.ItemTrip
import com.example.gogobus.ui.presentation.search.components.ItemTripLoading
import com.example.gogobus.ui.presentation.search.components.ScheduleItemLoading
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.GogobusTheme
import com.example.gogobus.ui.theme.OrangeSecondary
import com.example.gogobus.ui.theme.SurfaceCard
import com.example.gogobus.ui.theme.SurfaceCardBorder
import com.example.gogobus.ui.theme.TextDark
import com.example.gogobus.ui.theme.TextPlaceholder
import java.time.LocalDate

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onNavToTripDetail: (trip: TripSearch) -> Unit = {},
    onBack: () -> Unit = {},
    origin: Location,
    destination: Location,
    date: LocalDate
) {

    val uiState by viewModel.searchState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTrips(origin, destination, date)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = BackgroundLight,
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 16.dp)
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
                            onBack()
                        }
                        .background(
                            color = OrangeSecondary,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .padding(2.dp)
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = origin.name,
                            style = AppTypography.body14SemiBold,
                            color = TextDark
                        )

                        Icon(
                            painter = painterResource(R.drawable.arrow_right_alt_24),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(14.dp)
                                .background(
                                    color = OrangeSecondary,
                                    shape = RoundedCornerShape(100.dp)
                                )
                                .padding(2.dp)
                        )

                        Text(
                            text = destination.name,
                            style = AppTypography.body14SemiBold,
                            color = TextDark
                        )
                    }
                    Text(
                        text = DateUtils.formatLocalDate(date),
                        style = AppTypography.body12Medium,
                        color = TextPlaceholder
                    )
                }
            }
            //OtroCah
        }
        if(uiState.isLoading){
            ScheduleItemLoading()
        }else{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Horario Disponible",
                    style = AppTypography.body16SemiBold,
                    color = TextDark
                )
                Card(
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, SurfaceCardBorder),
                    modifier = Modifier
                        .padding(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = SurfaceCard
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 1.dp
                    )
                ) {
                    Text(
                        text = uiState.trips.size.toString(),
                        style = AppTypography.body14SemiBold,
                        color = TextDark,
                        modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp)
                    )
                }
            }
        }
        LazyColumn(
            modifier = Modifier.weight(1f)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if(uiState.isLoading){
                items(3){
                    ItemTripLoading()
                }
            }else{
                items(uiState.trips){
                    ItemTrip(
                        trip = it,
                        onClick = onNavToTripDetail
                    )
                }
            }

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SearchScreenPrev() {
    GogobusTheme {

    }
}