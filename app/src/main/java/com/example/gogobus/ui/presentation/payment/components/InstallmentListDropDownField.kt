package com.example.gogobus.ui.presentation.payment.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.gogobus.data.local.Installment
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.presentation.payment.state.InstallmentsState
import com.example.gogobus.ui.theme.OrangeSecondary
import com.example.gogobus.ui.theme.TextDark
import com.mercadopago.sdk.android.example.extensions.addBorder

@Composable
internal fun InstallmentListDropDownField(
    modifier: Modifier = Modifier,
    state: InstallmentsState,
    onSelectedInstallment: (Installment) -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }

    if (state.showList) {
        Column(
            modifier = modifier.padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Select the number of installments",
                style = AppTypography.body12Medium,
                color = TextDark
            )
            Spacer(modifier = Modifier.height(10.dp))

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = modifier.wrapContentSize()
            ) {
                BasicTextField(
                    value = "",
                    onValueChange = {},
                    decorationBox = { innerTextField ->
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .addBorder(isFocused)
                                .height(OutlinedTextFieldDefaults.MinHeight)
                                .padding(horizontal = 16.dp)
                        ) {
                            InstallmentDropDown(
                                state = state,
                                onSelect = onSelectedInstallment,
                                modifier = Modifier.height(300.dp)
                            )
                            innerTextField()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                        }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun InstallmentDropDown(
    state: InstallmentsState,
    onSelect: (Installment) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier.height(100.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable)
                .fillMaxHeight()
        ) {

            Text(
                text = state.selectedInstallment?.value ?: "Choose option",
                style = AppTypography.body12Medium,
                color = TextDark
            )
            ExposedDropdownMenuDefaults.TrailingIcon(
                expanded = expanded,
                modifier.weight(1f)
            )
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(color = MaterialTheme.colorScheme.onPrimary)
        ) {
            state.installments.forEach { installment ->
                val selected = installment.value == state.selectedInstallment?.value
                DropdownMenuItem(
                    text = {
                        Text(
                            text = installment.value,
                            style = AppTypography.body12Medium,
                            color = if (selected) OrangeSecondary else Color.Unspecified
                        )
                    },
                    modifier = Modifier
                        .heightIn(Dp.Unspecified, 300.dp)
                        .background(
                            color = if (selected) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onPrimary
                            }
                        ),
                    onClick = {
                        expanded = false
                        onSelect(installment)
                    }
                )
            }
        }
    }
}
