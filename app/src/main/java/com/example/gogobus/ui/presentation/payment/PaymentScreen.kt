package com.example.gogobus.ui.presentation.payment

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gogobus.R
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.presentation.payment.components.CardForm
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.OrangeSecondary
import com.example.gogobus.ui.theme.TextDark
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.pcitextfield.rememberPCIFieldState

@Composable
fun PaymentScreen(
    bookingId: Int,
    totalAmount: Double,
    modifier: Modifier = Modifier,
    viewModel: PaymentViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val viewState by viewModel.viewState.collectAsState()
    val cardNumberState = rememberPCIFieldState()
    val expirationDateState = rememberPCIFieldState()
    val securityCodeState = rememberPCIFieldState()

    LaunchedEffect(key1 = true) {
        viewModel.getIdentificationTypes()
    }

    LaunchedEffect(Unit) {
        viewModel.paymentEvent.collect { event ->
            when(event){
                is PaymentUiEvent.OnSuccessPayment -> {
                    Toast.makeText(context, "Pago exitoso", Toast.LENGTH_SHORT).show()
                }
                is PaymentUiEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.chevron_left_24),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .clickable {

                        }
                        .background(
                            color = OrangeSecondary,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .padding(2.dp)
                )
                Text(
                    text = "Pago",
                    style = AppTypography.body14SemiBold,
                    color = TextDark
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = BackgroundLight,
                )
                .padding(horizontal = 24.dp, vertical = 24.dp),
        ){
            CardForm(
                viewState = viewState,
                cardNumberState = cardNumberState,
                expirationDateState = expirationDateState,
                securityCodeState = securityCodeState,
                onGenerateCardToken = {
                    viewModel.generateToken(
                        cardNumberState = cardNumberState,
                        expirationDateState = expirationDateState,
                        securityCodeState = securityCodeState,
                        bookingId = bookingId,
                        totalAmount = totalAmount
                    )
                },
                onExpirationDateEvent = viewModel::onExpirationDateEvent,
                onSecurityCodeEvent = viewModel::onSecurityCodeEvent,
                onCardNumberEvent = viewModel::onCardNumberEvent,
                onSelectIdentification = viewModel::onIdentificationTypeChanged,
                onIdentificationTypeChanged = viewModel::onIdentificationTypeValueChanged,
                onCardHolderNameChanged = viewModel::onCardHolderNameChanged,
                isLoading = viewState.isLoading,
                totalAmount = totalAmount
            )
        }

    }
}