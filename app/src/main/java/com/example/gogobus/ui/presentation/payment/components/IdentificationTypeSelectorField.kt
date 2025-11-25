package com.example.gogobus.ui.presentation.payment.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.presentation.payment.state.IdentificationState
import com.example.gogobus.ui.theme.SurfaceCard
import com.example.gogobus.ui.theme.TextDark
import com.example.gogobus.ui.theme.TextPlaceholder
import com.mercadopago.sdk.android.coremethods.domain.model.IdentificationType
import com.mercadopago.sdk.android.coremethods.ui.utils.MaskVisualTransformation
import com.mercadopago.sdk.android.example.extensions.addBorder

internal fun IdentificationType?.getPlaceholder(): String {
    return when (this?.id) {
        "CPF" -> "999.999.999-99"
        "CNPJ" -> "99.999.999/9999-99"
        "DI" -> ""
        else -> ""
    }
}

internal fun IdentificationType?.getVisualTransformation(): VisualTransformation {
    return when (this?.id) {
        "CPF" -> MaskVisualTransformation("###.###.###-##")
        "CNPJ" -> MaskVisualTransformation("##.###.###/####-##")
        "DI" -> VisualTransformation.None
        else -> VisualTransformation.None
    }
}


@Suppress("LongMethod")
@Composable
internal fun IdentificationTypeSelectorField(
    modifier: Modifier = Modifier,
    state: IdentificationState,
    onSelectIdentification: (IdentificationType) -> Unit,
    onIdentificationTypeChanged: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        Text(
            text = "Cardholder ID",
            style = AppTypography.body12Medium,
            color = TextDark
        )
        Spacer(Modifier.height(4.dp))
        BasicTextField(
            value = state.identificationValue,
            textStyle = AppTypography.body12Medium.copy(
                color = TextDark
            ),
            onValueChange = { value ->
                if (value.length <= (state.selectedIdentification?.maxLength ?: 0)) {
                    onIdentificationTypeChanged(value)
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = if (state.selectedIdentification?.type == "number") {
                    KeyboardType.Number
                } else {
                    KeyboardType.Unspecified
                },
            ),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(OutlinedTextFieldDefaults.MinHeight)
                        .addBorder(isFocused)
                        .padding(horizontal = 16.dp),
                ) {
                    IdentificationTypeSelectorList(
                        state = state,
                        onSelectIdentification = { identificationType ->
                            onSelectIdentification(identificationType)
                            onIdentificationTypeChanged("")
                        },
                    )
                    VerticalDivider(modifier = Modifier.height(40.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(Modifier.fillMaxWidth()) {
                        if (state.identificationValue.isEmpty()) {

                            Text(
                                text = state.selectedIdentification.getPlaceholder(),
                                modifier = Modifier.align(Alignment.CenterStart),
                                style = AppTypography.body12Medium,
                                color = TextPlaceholder
                            )
                        }
                        innerTextField()
                    }
                }
            },
            visualTransformation = state.selectedIdentification.getVisualTransformation(),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun IdentificationTypeSelectorList(
    modifier: Modifier = Modifier,
    state: IdentificationState,
    onSelectIdentification: (IdentificationType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable),
        ) {
            Text(
                text = state.selectedIdentification?.name.orEmpty(),
                style = AppTypography.body12Medium,
                color = TextDark
            )
            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = SurfaceCard
        ) {
            state.identificationList.forEach { option ->
                DropdownMenuItem(
                    text = {
                        option.name?.let {
                            Text(
                                text = it,
                                style = AppTypography.body12Medium,
                                color = TextDark
                            )
                        }
                    },
                    onClick = {
                        expanded = false
                        onSelectIdentification(option)
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = TextDark
                    )
                )
            }
        }
    }
}
