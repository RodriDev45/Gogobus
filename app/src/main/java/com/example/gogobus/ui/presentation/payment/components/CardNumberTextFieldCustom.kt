package com.example.gogobus.ui.presentation.payment.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.presentation.payment.state.CardNumberTextFieldState
import com.example.gogobus.ui.theme.TextDark
import com.example.gogobus.ui.theme.TextPlaceholder
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.cardnumber.CardNumberTextField
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.cardnumber.CardNumberTextFieldEvent
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.pcitextfield.PCIFieldState
import com.mercadopago.sdk.android.example.extensions.addBorder
/**
 * This is a example of implementation of the card number secure field
 * The component ensures PCI compliance by handling sensitive card data securely.
 */
@Composable
internal fun CardNumberTextFieldCustom(
    modifier: Modifier = Modifier,
    state: PCIFieldState,
    cardNumberState: CardNumberTextFieldState,
    onCardNumberEvent: (CardNumberTextFieldEvent) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = "Card Number",
            style = AppTypography.body12Medium,
            color = TextDark
        )
        Spacer(Modifier.height(4.dp))
        CardNumberTextField(
            state = state,
            onEvent = onCardNumberEvent,
            textStyle = AppTypography.body12Medium.copy(
                color = TextDark
            ),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        // Adding a border by field state created for this example
                        .addBorder(
                            isFocused = cardNumberState.isFocused,
                            error = cardNumberState.error.first
                        )
                        .height(OutlinedTextFieldDefaults.MinHeight)
                        .padding(horizontal = 16.dp),
                ) {
                    if (cardNumberState.image != null) {
                        val context = LocalContext.current
                        Log.d("CardToken", "CardNumberTextFieldCustom: ${cardNumberState.image}")
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(cardNumberState.image)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                    }
                    Box {
                        if (cardNumberState.length == 0) {
                            Text(
                                text = "4444 4444 4444 4444",
                                modifier = Modifier.align(Alignment.CenterStart),
                                style = AppTypography.body12Medium,
                                color = TextPlaceholder
                            )
                        }
                        // Never forget to call innerTextField again inside decorationBox
                        innerTextField()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )

        if (cardNumberState.error.first) {
            Text(
                text = cardNumberState.error.second,
                style = AppTypography.body12Medium,
                color = TextDark
            )
        }
    }
}