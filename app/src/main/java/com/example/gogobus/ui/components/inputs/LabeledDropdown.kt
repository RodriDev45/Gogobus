package com.example.gogobus.ui.components.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.R
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.TextGray
import com.example.gogobus.ui.theme.TextPlaceholder

@Composable
fun LabeledDropdown(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    leadingIcon: (@Composable (() -> Unit))? = null
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {}, // Deshabilitado para edición
            readOnly = true,
            placeholder = {
                Text(
                    text = placeholder,
                    style = AppTypography.body16Regular,
                    color = TextPlaceholder
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .clickable { expanded = !expanded },
            trailingIcon = {
                Icon(
                    painter = painterResource(
                        if (expanded) R.drawable.arrow_drop_up_24 else R.drawable.arrow_drop_down_24
                    ),
                    contentDescription = null,
                    tint = TextPlaceholder,
                    modifier = Modifier.size(32.dp)
                )
            },
            leadingIcon = leadingIcon,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = TextPlaceholder,
            ),
            singleLine = true,
            shape = RoundedCornerShape(12.dp)
        )

        // Label flotante sobre el borde
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 10.dp, y = (-10).dp)
                .background(Color.White)
                .padding(horizontal = 6.dp)
        ) {
            Text(
                text = label,
                style = AppTypography.body14Medium,
                color = TextGray
            )
        }

        // Menú desplegable
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = AppTypography.body16Regular,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LabeledDropdownPreview() {
    var selected by remember { mutableStateOf("") }

    LabeledDropdown(
        label = "Destino",
        options = listOf("Trujillo", "Lima", "Arequipa"),
        selectedOption = selected,
        onOptionSelected = { selected = it },
        placeholder = "Selecciona un destino",
        modifier = Modifier.padding(16.dp)
    )
}