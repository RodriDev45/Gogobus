package com.example.gogobus.ui.components.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.R
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.TextGray
import com.example.gogobus.ui.theme.TextPlaceholder

@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    focused: Boolean? = null,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    leadingIcon: (@Composable (() -> Unit))? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onClick: (() -> Unit)? = null
) {
    val focusManager = LocalFocusManager.current
    LaunchedEffect(focused) {
        if (focused == false) {
            focusManager.clearFocus()
        }
    }
    Box(
        modifier = modifier
    ){
        // Campo principal
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            readOnly = readOnly,
            keyboardOptions = keyboardOptions,
            placeholder = {
                Text(
                    text = placeholder,
                    style = AppTypography.body16Regular,
                    color = TextPlaceholder
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { state ->
                    // Si el campo recibe foco, está en modo readOnly y existe un onClick, lo llamamos
                    if (state.isFocused && readOnly && onClick != null) {
                        // Ejecuta la acción (por ejemplo abrir bottom sheet)
                        onClick()
                        // Quitamos foco inmediatamente para que no se abra teclado ni quede seleccionado
                        //focusManager.clearFocus()
                    }
                }
                .align(Alignment.Center),
            singleLine = singleLine,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            maxLines = maxLines,
            minLines = minLines,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = TextPlaceholder,
            ),
            shape = RoundedCornerShape(12.dp)
        )

        // Label flotante sobre el borde
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(
                    // Sube el label un poco y lo corre según si hay ícono
                    x =  10.dp,
                    y = (-10).dp
                )
                .background(Color.White)
                .padding(horizontal = 6.dp) // padding interno del texto dentro del fondo
        ) {
            Text(
                text = label,
                style = AppTypography.body14Medium,
                color = TextGray
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun OutlinedTextFieldWithFloatingLabelPrev() {
    LabeledTextField(
        label = "Fecha",
        value = "",
        onValueChange = { },
        placeholder = "dd/mm/yyyy",
        modifier = Modifier.padding(16.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.gps_fixed_20),
                contentDescription = null,
                tint = TextPlaceholder
            )
        }
    )
}
