package com.example.gogobus.ui.presentation.tripdetail.components

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.R
import com.example.gogobus.domain.model.BusLayout
import com.example.gogobus.domain.model.Seat
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.BluePrimary
import com.example.gogobus.ui.theme.GogobusTheme
import com.example.gogobus.ui.theme.SuccessText
import com.example.gogobus.ui.theme.SurfaceLight
import com.example.gogobus.ui.theme.TextDark
import com.example.gogobus.ui.theme.TextGray
import com.example.gogobus.ui.theme.TextPlaceholder

@Composable
fun SeatsBus(
    modifier: Modifier = Modifier,
    onSeatSelect: (Seat) -> Unit = {},
    busLayout: BusLayout,
    seatsAvailable: List<Int>,
    seatsSelected: List<Int>,
    seatsOccupied: List<Int>
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
                    text = "Asientos",
                    color = TextDark,
                    style = AppTypography.body14SemiBold
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LegendSeat(
                        color = Color(0xFF004E5A),
                        text = "Disponible"
                    )
                    LegendSeat(
                        color = Color(0xFFF5951F),
                        text = "Seleccionado"
                    )
                    LegendSeat(
                        color = TextGray,
                        text = "Ocupado"
                    )
                }
            }
            Spacer(modifier
                .fillMaxWidth()
                .height(2.dp)
                .padding(horizontal = 10.dp)
                .background(BackgroundLight))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 12.dp),
                contentAlignment = Alignment.Center
            ){
                BusLayoutView(
                    onSelectSeat = onSeatSelect,
                    busLayout = busLayout,
                    seatsAvailable = seatsAvailable,
                    seatsSelected = seatsSelected,
                    seatsOccupied = seatsOccupied

                )
            }

        }
    }
}

@Composable
fun BusLayoutView(
    busLayout: BusLayout,
    onSelectSeat: (Seat) -> Unit,
    seatsAvailable: List<Int>,
    seatsSelected: List<Int>,
    seatsOccupied: List<Int>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Iterar sobre cada fila
        busLayout.layoutConfig.toSortedMap().forEach { (rowKey, columns) ->
            val seatRow = busLayout.seats[rowKey] ?: emptyList()

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                columns.forEachIndexed { index, cell ->
                    when (cell) {
                        "A" -> {
                            // Buscar si existe un asiento en esa columna
                            val seat = seatRow.find { it.column == index + 1 }
                            if (seat != null) {
                                val state = when {
                                    seatsAvailable.contains(seat.number) -> 2
                                    seatsSelected.contains(seat.number) -> 1
                                    seatsOccupied.contains(seat.number) -> 0
                                    else ->-1
                                }
                                SeatItem(seat, state, onSelectSeat)
                            } else {
                                EmptyCell()
                            }
                        }
                        "W" -> {
                            val seat = seatRow.find { it.column == index + 1 }
                            if (seat != null) {
                                val state = when {
                                    seatsAvailable.contains(seat.number) -> 2
                                    seatsSelected.contains(seat.number) -> 1
                                    seatsOccupied.contains(seat.number) -> 0
                                    else ->-1
                                }
                                if(index == columns.size-1){
                                    WindowCellRight(seat, state, onSelectSeat)
                                }else{
                                    WindowCellLeft(seat, state, onSelectSeat)
                                }
                            } else {
                                EmptyCell()
                            }
                        }
                        "_" -> EmptyCell()

                    }
                }
            }
        }
    }
}

@Composable
fun SeatItem(
    seat: Seat,
    state: Int,
    onSelectSeat: (Seat) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = when(state){
                    0 -> TextGray //Disable
                    1 -> Color(0xFFF5951F) //Selected
                    else -> Color(0xFF004E5A) //Available
                },
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                when(state){
                    0 -> Unit //Disable
                    1 -> Unit //Selected
                    else -> onSelectSeat(seat) //Available
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = seat.number.toString(),
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun WindowCellLeft(
    seat: Seat,
    state: Int,
    onSelectSeat: (Seat) -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(R.drawable.window_24),
            contentDescription = null,
            tint = TextPlaceholder
        )
        SeatItem(seat, state, onSelectSeat)
    }
    
}

@Composable
fun WindowCellRight(
    seat: Seat,
    state: Int,
    onSelectSeat: (Seat) -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SeatItem(seat, state, onSelectSeat)
        Icon(
            painter = painterResource(R.drawable.window_24),
            contentDescription = null,
            tint = TextPlaceholder
        )
    }

}

@Composable
fun EmptyCell() {
    Box(
        modifier = Modifier
            .size(40.dp)         ,
    )
}



@Composable
fun LegendSeat(
    modifier: Modifier = Modifier,
    color: Color,
    text: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(
                    color = color,
                    shape = RoundedCornerShape(2.dp)
                )
        )
        Text(
            text = text,
            color = TextDark,
            style = AppTypography.body12Medium
        )
    }
}

