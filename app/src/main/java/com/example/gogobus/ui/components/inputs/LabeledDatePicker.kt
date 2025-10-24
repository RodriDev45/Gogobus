package com.example.gogobus.ui.components.inputs

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.R
import com.example.gogobus.domain.util.DateUtils
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.TextGray
import com.example.gogobus.ui.theme.TextPlaceholder
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Composable
fun LabeledDatePicker(
    label: String,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "dd/mm/yyyy",
    focused: Boolean = false
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val focusManager = LocalFocusManager.current
    LaunchedEffect(focused) {
        if (!focused) {
            focusManager.clearFocus()
        }
    }

    // Mostrar el DatePicker al hacer click
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val date = LocalDate.of(year, month + 1, dayOfMonth)
            onDateSelected(date)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    datePickerDialog.datePicker.minDate = System.currentTimeMillis()

    Box(modifier = modifier) {
        OutlinedTextField(
            value = DateUtils.formatLocalDate(selectedDate),
            onValueChange = {},
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
                .onFocusChanged { state ->
                    // Si el campo recibe foco, está en modo readOnly y existe un onClick, lo llamamos
                    if (state.isFocused) {
                        // Ejecuta la acción (por ejemplo abrir bottom sheet)
                        datePickerDialog.show()
                        // Quitamos foco inmediatamente para que no se abra teclado ni quede seleccionado
                        focusManager.clearFocus()
                    }
                }
                .align(Alignment.Center),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.calendar_month_24), // ícono de calendario
                    contentDescription = null,
                    tint = TextPlaceholder
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = TextPlaceholder
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
    }
}