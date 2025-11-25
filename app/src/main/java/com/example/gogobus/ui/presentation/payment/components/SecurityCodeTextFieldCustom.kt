package com.example.gogobus.ui.presentation.payment.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gogobus.R
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.presentation.payment.state.SecurityCodeState
import com.example.gogobus.ui.theme.TextDark
import com.example.gogobus.ui.theme.TextPlaceholder
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.pcitextfield.PCIFieldState
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.securitycode.SecurityCodeTextField
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.securitycode.SecurityCodeTextFieldEvent
import com.mercadopago.sdk.android.example.extensions.addBorder


@Composable
internal fun SecurityCodeTextFieldExample(
    modifier: Modifier = Modifier,
    state: PCIFieldState,
    securityCodeState: SecurityCodeState,
    onSecurityCodeEvent: (SecurityCodeTextFieldEvent) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = "Security code",
            style = AppTypography.body12Medium,
            color = TextDark
        )
        Spacer(Modifier.height(4.dp))
        CompositionLocalProvider(
            LocalTextSelectionColors provides TextSelectionColors(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            SecurityCodeTextField(
                state = state,
                onEvent = onSecurityCodeEvent,
                textStyle = AppTypography.body12Medium.copy(
                    color = TextDark
                ),
                securityCodeSize = securityCodeState.secureCodeLength,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .addBorder(
                                isFocused = securityCodeState.isFocused,
                                error = securityCodeState.error.first
                            )
                            .height(OutlinedTextFieldDefaults.MinHeight)
                            .padding(horizontal = 16.dp),
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            if (securityCodeState.length == 0) {
                                Text(
                                    text = "123",
                                    modifier = Modifier.align(Alignment.CenterStart),
                                    style = AppTypography.body12Medium,
                                    color = TextPlaceholder
                                )
                            }
                            innerTextField()
                        }
                        Spacer(Modifier.width(4.dp))
                        Image(
                            painter = painterResource(R.drawable.ic_security_code),
                            contentDescription = null,
                            modifier = Modifier.size(34.dp),
                        )
                    }
                }
            )
        }

        if (securityCodeState.error.first) {
            Text(
                text = securityCodeState.error.second,
                style = AppTypography.body12Medium,
                color = TextDark
            )
        }
    }
}
