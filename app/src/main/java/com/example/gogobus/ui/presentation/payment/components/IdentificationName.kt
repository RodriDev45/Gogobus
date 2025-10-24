package com.example.gogobus.ui.presentation.payment.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.presentation.payment.state.IdentificationState
import com.example.gogobus.ui.theme.TextDark
import com.example.gogobus.ui.theme.TextPlaceholder

@Composable
internal fun IdentificationName(
    modifier: Modifier = Modifier,
    identificationState: IdentificationState,
    onCardHolderNameChanged: (String) -> Unit
) {
    Column(modifier = modifier) {
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Cardholder's name as it appears on the card",
            style = AppTypography.body12Medium,
            color = TextDark
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = identificationState.identificationNameValue,
            placeholder = {
                Text(
                    text = "Maria Lopez",
                    style = AppTypography.body12Medium,
                    color = TextPlaceholder
                )
            },
            textStyle = AppTypography.body12Medium.copy(
                color = TextDark
            ),
            onValueChange = onCardHolderNameChanged,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}