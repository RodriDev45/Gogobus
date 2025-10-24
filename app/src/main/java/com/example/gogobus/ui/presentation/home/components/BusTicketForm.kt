package com.example.gogobus.ui.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.GogobusTheme
import com.example.gogobus.ui.theme.OrangeSecondary

@Composable
fun BusTicketForm(
    modifier: Modifier = Modifier,
    origin: String,
    destination: String,
    date: String,
    onOriginChange: (String) -> Unit,
    onDestinationChange: (String) -> Unit,
    onDateChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
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
            LocationSelector(label = "Departure", placeholder = "Select departure point", value = origin, onValueChange = onOriginChange)

            // Swap Button
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                IconButton(
                    onClick = { /* TODO: Swap origin and destination */ },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(OrangeSecondary)
                        .size(36.dp)
                ) {
                    Icon(imageVector = Icons.Default.SwapVert, contentDescription = "Swap", tint = Color.White)
                }
            }

            // Destination Point
            LocationSelector(label = "Destination", placeholder = "Select destination", value = destination, onValueChange = onDestinationChange)

            // Date Picker
            DatePickerField(label = "Date", placeholder = "dd/mm/yyyy", value = date, onValueChange = onDateChange)

            Spacer(modifier = Modifier.height(8.dp))

            // Search Button
            Button(
                onClick = onSearchClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangeSecondary)
            ) {
                Text(text = "Search Ticket", style = AppTypography.body16Bold)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSelector(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val locations = listOf("New York", "Los Angeles", "Chicago", "Houston", "Phoenix")

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = label, style = AppTypography.body12Bold, color = Color.Gray)
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
            TextField(
                value = value,
                onValueChange = {},
                readOnly = true,
                placeholder = { Text(text = placeholder, color = Color.Gray) },
                leadingIcon = { Icon(Icons.Outlined.LocationOn, contentDescription = null, tint = Color.Gray) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF4F4F4),
                    focusedContainerColor = Color(0xFFF4F4F4),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                locations.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            onValueChange(it)
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = label, style = AppTypography.body12Bold, color = Color.Gray)
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.Gray) },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF4F4F4),
                focusedContainerColor = Color(0xFFF4F4F4),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusTicketFormPreview() {
    GogobusTheme {
        BusTicketForm(
            origin = "",
            destination = "",
            date = "",
            onOriginChange = {},
            onDestinationChange = {},
            onDateChange = {},
            onSearchClick = {}
        )
    }
}
