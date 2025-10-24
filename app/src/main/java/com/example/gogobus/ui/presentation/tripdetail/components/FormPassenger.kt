package com.example.gogobus.ui.presentation.tripdetail.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.gogobus.R
import com.example.gogobus.domain.model.PassengerRequest
import com.example.gogobus.ui.components.buttons.ButtonPrimary
import com.example.gogobus.ui.components.inputs.LabeledTextField
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.TextDark
import com.example.gogobus.ui.theme.TextPlaceholder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPassenger(
    modifier: Modifier = Modifier,
    passengerRequest: PassengerRequest,
    onDismissRequest: ()->Unit,
    onPassengerChange: (PassengerRequest)->Unit,
    showForm: Boolean
) {
    var passengerForm by remember { mutableStateOf(passengerRequest) }
    val context = LocalContext.current

    LaunchedEffect(passengerRequest) {
        passengerForm = passengerRequest
    }

    if(showForm){
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            modifier = modifier,
            containerColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Datos del Pasajero - ${passengerRequest.seatNumber}",
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
                LabeledTextField(
                    label = "Nombre Completo",
                    placeholder = "Ej. Carlos Perez Alavarez",
                    value = passengerForm.fullName,
                    onValueChange = {
                        passengerForm = passengerForm.copy(fullName = it)
                    }
                )
                LabeledTextField(
                    label = "Documento de Identidad (DNI)",
                    placeholder = "Ej. 11223311",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = passengerForm.dni,
                    onValueChange = {
                        if (it.length <= 8 && it.matches(Regex("\\d{0,8}"))) {
                            passengerForm = passengerForm.copy(dni = it)
                        }
                    }
                )
                ButtonPrimary(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Guardar",
                    onClick = {
                        if(passengerForm.fullName.isNotEmpty() && passengerForm.dni.isNotEmpty()){
                            if(passengerForm.dni.length == 8){
                                onPassengerChange(passengerForm.copy(seatNumber = passengerRequest.seatNumber))
                            }else{
                                Toast.makeText(context, "El DNI debe tener 8 dígitos", Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Toast.makeText(context, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
                        }
                    }
                )

            }
        }
    }
}