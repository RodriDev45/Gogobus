package com.example.gogobus.ui.presentation.payment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.gogobus.data.local.Installment
import com.example.gogobus.ui.components.buttons.ButtonPrimary
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.presentation.payment.state.PaymentScreenViewState
import com.example.gogobus.ui.theme.BackgroundLight
import com.example.gogobus.ui.theme.SurfaceLight
import com.example.gogobus.ui.theme.TextDark
import com.mercadopago.sdk.android.coremethods.domain.interactor.coreMethods
import com.mercadopago.sdk.android.coremethods.domain.model.BuyerIdentification
import com.mercadopago.sdk.android.coremethods.domain.model.CardToken
import com.mercadopago.sdk.android.coremethods.domain.model.IdentificationType
import com.mercadopago.sdk.android.coremethods.domain.model.ResultError
import com.mercadopago.sdk.android.coremethods.domain.utils.Result
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.cardnumber.CardNumberTextField
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.cardnumber.CardNumberTextFieldEvent
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.expirationdate.ExpirationDateTextField
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.expirationdate.ExpirationDateTextFieldEvent
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.pcitextfield.PCIFieldState
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.pcitextfield.rememberPCIFieldState
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.securitycode.SecurityCodeTextField
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.securitycode.SecurityCodeTextFieldEvent
import com.mercadopago.sdk.android.initializer.MercadoPagoSDK
import kotlinx.coroutines.launch

@Composable
fun CardForm(
    modifier: Modifier = Modifier,
    viewState: PaymentScreenViewState,
    cardNumberState: PCIFieldState,
    expirationDateState: PCIFieldState,
    securityCodeState: PCIFieldState,
    onGenerateCardToken: () -> Unit,
    onExpirationDateEvent: (ExpirationDateTextFieldEvent) -> Unit,
    onSecurityCodeEvent: (SecurityCodeTextFieldEvent) -> Unit,
    onCardNumberEvent: (CardNumberTextFieldEvent) -> Unit,
    onSelectIdentification: (IdentificationType) -> Unit,
    onIdentificationTypeChanged: (String) -> Unit,
    onCardHolderNameChanged: (String) -> Unit,
    isLoading: Boolean = false,
    totalAmount: Double
) {


    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceLight
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Datos de la tarjeta",
                    color = TextDark,
                    style = AppTypography.body16SemiBold
                )
            }
            Spacer(modifier
                .fillMaxWidth()
                .height(2.dp)
                .padding(horizontal = 10.dp)
                .background(BackgroundLight))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                CardNumberTextFieldCustom(
                    state = cardNumberState,
                    cardNumberState = viewState.cardNumberState,
                    onCardNumberEvent = onCardNumberEvent
                )

                Row(modifier = Modifier) {
                    ExpirationDateTextFieldExample(
                        Modifier.weight(1f),
                        state = expirationDateState,
                        expirationDateState = viewState.expirationDateState,
                        onExpirationDateEvent = onExpirationDateEvent
                    )
                    Spacer(Modifier.width(16.dp))
                    SecurityCodeTextFieldExample(
                        Modifier.weight(1f),
                        state = securityCodeState,
                        securityCodeState = viewState.secureCodeState,
                        onSecurityCodeEvent = onSecurityCodeEvent
                    )
                }

                IdentificationName(
                    identificationState = viewState.identificationState,
                    onCardHolderNameChanged = onCardHolderNameChanged
                )
                IdentificationTypeSelectorField(
                    state = viewState.identificationState,
                    onSelectIdentification = onSelectIdentification,
                    onIdentificationTypeChanged = onIdentificationTypeChanged
                )

                ButtonPrimary(
                    text = "Pager Ticket - S/. $totalAmount",
                    onClick = onGenerateCardToken,
                    modifier = Modifier.fillMaxWidth(),
                    loading = isLoading,
                    enabled = !isLoading
                )
            }


        }
    }

}
