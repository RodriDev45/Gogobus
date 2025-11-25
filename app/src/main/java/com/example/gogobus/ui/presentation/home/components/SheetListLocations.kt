package com.example.gogobus.ui.presentation.home.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.R
import com.example.gogobus.domain.model.Location
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.GogobusTheme
import com.example.gogobus.ui.theme.OrangeSecondary
import com.example.gogobus.ui.theme.TextDark
import com.example.gogobus.ui.theme.TextPlaceholder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetListLocations(
    title: String,
    showSheet: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    locations: List<Location>,
    itemSelected: Location?,
    onSelected: (Location) -> Unit,
    onLoadMore: () -> Unit,
    loading: Boolean = false,
    hasMore: Boolean = true,
) {

    if(showSheet){
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            modifier = modifier,
            containerColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        color = TextDark,
                        style = AppTypography.body18SemiBold
                    )


                    Icon(
                        painter = painterResource(R.drawable.close_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .clickable {
                                onDismissRequest()
                            }
                            .background(color = TextPlaceholder, shape = RoundedCornerShape(100.dp))
                            .padding(4.dp)
                            .size(20.dp)

                    )
                }
                Spacer(modifier=Modifier.height(12.dp))
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    itemsIndexed(locations){ index, location ->
                        ItemSheetListLocation(
                            location = location,
                            onSelected = {
                                onSelected(location)
                                onDismissRequest()
                            },
                            selected = itemSelected?.id == location.id
                        )
                    }
                    if(!loading && hasMore){
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                TextButton(
                                    onClick = { onLoadMore() }
                                ) {
                                    Text(
                                        text = "Ver más",
                                        color = OrangeSecondary,
                                        style = AppTypography.body14Bold
                                    )
                                }

                            }
                        }

                    }
                    if (loading) {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator(
                                    color = OrangeSecondary
                                )
                            }
                        }
                    }
                }
            }

        }
    }

}

@Composable
fun ItemSheetListLocation(
    modifier: Modifier = Modifier,
    location: Location,
    selected: Boolean = false,
    onSelected: (location: Location)->Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (selected) OrangeSecondary else TextPlaceholder,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(start = 16.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
            .clickable {
                onSelected(location)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.gps_fixed_20),
                contentDescription = null,
                tint = TextPlaceholder,
            )
            Column {
                Text(
                    text = location.name,
                    color = TextDark,
                    style = AppTypography.body16Bold
                )
                Spacer(modifier = Modifier.padding(top = 2.dp))
                Text(
                    text = location.terminal,
                    color = TextPlaceholder,
                    style = AppTypography.body14Medium
                )
            }
        }
        RadioButton(
            selected = selected,
            onClick = {
                onSelected(location)
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = OrangeSecondary,
                unselectedColor = TextPlaceholder
            )
        )
    }
}
