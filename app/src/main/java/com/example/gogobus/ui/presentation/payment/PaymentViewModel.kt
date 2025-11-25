package com.example.gogobus.ui.presentation.payment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gogobus.data.local.Installment
import com.example.gogobus.domain.model.Payment
import com.example.gogobus.domain.model.PaymentRequest
import com.example.gogobus.domain.usecase.CreatePaymentUseCase
import com.example.gogobus.domain.util.Resource
import com.example.gogobus.ui.presentation.payment.state.PaymentScreenViewState
import com.google.gson.GsonBuilder
import com.mercadopago.sdk.android.coremethods.domain.interactor.CoreMethods
import com.mercadopago.sdk.android.coremethods.domain.interactor.coreMethods
import com.mercadopago.sdk.android.coremethods.domain.model.BuyerIdentification
import com.mercadopago.sdk.android.coremethods.domain.model.IdentificationType
import com.mercadopago.sdk.android.coremethods.domain.model.ResultError
import com.mercadopago.sdk.android.coremethods.domain.utils.Result
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.cardnumber.CardNumberTextFieldEvent
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.expirationdate.ExpirationDateTextFieldEvent
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.pcitextfield.PCIFieldState
import com.mercadopago.sdk.android.coremethods.ui.components.textfield.securitycode.SecurityCodeTextFieldEvent
import com.mercadopago.sdk.android.example.data.mappers.toInstallmentModel
import com.mercadopago.sdk.android.initializer.MercadoPagoSDK
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.collections.orEmpty

internal const val CARD_NUMBER_BIN_LENGTH = 6
internal const val DEFAULT_MAX_CARD_LENGTH = 19
private const val CARD_LENGTH_8_MASK = "#### ####"
private const val CARD_LENGTH_9_MASK = "#### #####"
private const val CARD_LENGTH_10_MASK = "#### ######"
private const val CARD_LENGTH_11_MASK = "#### #### ###"
private const val CARD_LENGTH_12_MASK = "#### #### ####"
private const val CARD_LENGTH_13_MASK = "#### ###### ###"
private const val CARD_LENGTH_14_MASK = "#### ###### ####"
private const val CARD_LENGTH_15_MASK = "#### ###### #####"
private const val CARD_LENGTH_17_MASK = "#### #### #### #####"
private const val CARD_LENGTH_19_MASK = "#### #### #### #### ###"
internal const val DEFAULT_CARD_MASK = "#### #### #### ####"

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val coreMethods: CoreMethods,
    private val createPaymentUseCase: CreatePaymentUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(PaymentScreenViewState())
    val viewState: StateFlow<PaymentScreenViewState> = _viewState

    private val _paymentEvent = MutableSharedFlow<PaymentUiEvent>()
    val paymentEvent: MutableSharedFlow<PaymentUiEvent> = _paymentEvent


    private val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    fun generateToken(
        cardNumberState: PCIFieldState,
        expirationDateState: PCIFieldState,
        securityCodeState: PCIFieldState,
        bookingId: Int,
        totalAmount: Double
    ) {
        viewModelScope.launch {
            if (viewState.value.cardNumberState.length != viewState.value.cardNumberState.maxLength) {
                _viewState.value = _viewState.value.copy(
                    cardNumberState = _viewState.value.cardNumberState.copy(
                        error = Pair(
                            true,
                            "Please, fill the card number"
                        )
                    )
                )
                return@launch
            }
            _viewState.value = _viewState.value.copy(
                isLoading = true
            )
            val result = coreMethods.generateCardToken(
                cardNumberState = cardNumberState,
                expirationDateState = expirationDateState,
                securityCodeState = securityCodeState,
                buyerIdentification = BuyerIdentification(
                    name = viewState.value.identificationState.identificationNameValue,
                    number = viewState.value.identificationState.identificationValue,
                    type = viewState.value.identificationState.selectedIdentification?.name
                )
            )

            when (result) {
                is com.mercadopago.sdk.android.coremethods.domain.utils.Result.Success -> {
                    Log.d("CardToken", "PaymentMethod: ${_viewState.value.paymentId}")
                    Log.d("CardToken", "CardToken: ${result.data}")
                    val requestPayment = PaymentRequest(
                        transactionAmount = totalAmount,
                        token = result.data.token,
                        description = "Pago de viaje",
                        installments = 1,
                        paymentMethodId = _viewState.value.paymentId,
                        email = "rodrigovvvasquez@gmail.com",
                        type = result.data.cardHolder!!.identification!!.type ?: "DNI",
                        number = _viewState.value.identificationState.identificationValue,
                        bookingId = bookingId
                    )
                    val result = createPaymentUseCase(requestPayment)
                    when (result) {
                        is Resource.Error -> {
                            Log.d("CardToken", "CardErrToken: ${result}")
                            _paymentEvent.emit(PaymentUiEvent.ShowError(result.message))
                        }
                        Resource.Loading -> Unit
                        is Resource.Success<Payment> -> {
                            if(result.data.status == "approved"){
                                _paymentEvent.emit(PaymentUiEvent.OnSuccessPayment(result.data))
                            }else{
                                _paymentEvent.emit(PaymentUiEvent.ShowError("No se aprobo el pago, intentelo de nuevo"))
                            }
                        }
                    }
                }

                is com.mercadopago.sdk.android.coremethods.domain.utils.Result.Error -> {
                    when (result.error) {
                        is ResultError.Request -> {
                            Log.d("CardToken", "CardErrToken: ${result.error}")
                        }

                        is ResultError.Validation -> {
                            Log.d("CardToken", "CardValidToken: ${result.error}")
                        }
                    }
                }
            }
            _viewState.value = _viewState.value.copy(
                isLoading = false
            )
        }
    }


    fun getIdentificationTypes() {
        viewModelScope.launch {
            val result = coreMethods.getIdentificationTypes()
            when (result) {
                is Result.Success -> {

                    _viewState.value = _viewState.value.copy(
                        identificationState = _viewState.value.identificationState.copy(
                            identificationList = result.data,
                            selectedIdentification = result.data.firstOrNull(),
                        )
                    )
                }

                is Result.Error -> {
                    when (result.error) {
                        is ResultError.Request -> {

                        }

                        is ResultError.Validation -> {

                        }
                    }
                }
            }
        }
    }


    fun getInstallment(
        bin: String,
        amount: BigDecimal
    ) {
        viewModelScope.launch {
            val result = coreMethods.getInstallments(
                bin = bin,
                amount = amount,
            )

            when (result) {
                is Result.Success -> {

                    _viewState.value = _viewState.value.copy(
                        installmentsState = _viewState.value.installmentsState.copy(
                            showList = true,
                            installments = result.data.getOrNull(0)?.payerCost?.toInstallmentModel()
                                .orEmpty(),
                        )
                    )
                }

                is Result.Error -> {
                    when (result.error) {
                        is ResultError.Request -> {

                        }

                        is ResultError.Validation -> {

                        }
                    }
                }
            }
        }
    }



    fun getCardIssuers(bin: String, paymentMethodId: String) {
        viewModelScope.launch {
            val result = coreMethods.getCardIssuers(bin, paymentMethodId)

            when (result) {
                is com.mercadopago.sdk.android.coremethods.domain.utils.Result.Success -> {

                    _viewState.value = _viewState.value.copy(
                        cardIssuers = result.data,
                        paymentId = paymentMethodId,
                        cardNumberState = _viewState.value.cardNumberState.copy(image = result.data[0].thumbnail)
                    )
                }

                is com.mercadopago.sdk.android.coremethods.domain.utils.Result.Error -> {
                    when (result.error) {
                        is ResultError.Request -> {

                        }

                        is ResultError.Validation -> {

                        }
                    }
                }
            }
        }
    }

    fun getPaymentMethods(bin: String) {
        viewModelScope.launch {
            val result = coreMethods.getPaymentMethods(bin = bin)

            when (result) {
                is com.mercadopago.sdk.android.coremethods.domain.utils.Result.Success -> {

                    _viewState.value = _viewState.value.copy(
                        secureCodeState = _viewState.value.secureCodeState.copy(
                            secureCodeLength = result.data[0].card?.securityCode?.length ?: 3
                        )

                    )
                    getCardIssuers(
                        bin = bin,
                        paymentMethodId = result.data[0].id!!
                    )
                    updateCardMaskState(
                        result.data.firstOrNull()?.card?.length?.max ?: DEFAULT_MAX_CARD_LENGTH
                    )
                }

                is Result.Error -> {
                    when (result.error) {
                        is ResultError.Request -> {

                        }

                        is ResultError.Validation -> {

                        }
                    }
                }
            }
        }
    }

    fun onExpirationDateEvent(event: ExpirationDateTextFieldEvent) {

        when (event) {
            is ExpirationDateTextFieldEvent.OnInputFilled -> {
                _viewState.value = _viewState.value.copy(
                    expirationDateState = _viewState.value.expirationDateState.copy(
                        filled = event.isFilled
                    ),
                )
            }

            is ExpirationDateTextFieldEvent.IsValid -> {
                _viewState.value = _viewState.value.copy(
                    expirationDateState = _viewState.value.expirationDateState.copy(
                        valid = event.isValid,
                        error = Pair(!event.isValid, "Invalid expiration date")
                    )
                )
            }

            is ExpirationDateTextFieldEvent.OnFocusChanged -> {
                _viewState.value = _viewState.value.copy(
                    expirationDateState = _viewState.value.expirationDateState.copy(
                        isFocused = event.isFocused
                    )
                )
            }

            is ExpirationDateTextFieldEvent.OnLengthChanged -> {
                _viewState.value = _viewState.value.copy(
                    expirationDateState = _viewState.value.expirationDateState.copy(
                        length = event.length
                    )
                )
            }
        }
    }

    fun onSecurityCodeEvent(event: SecurityCodeTextFieldEvent) {

        when (event) {
            is SecurityCodeTextFieldEvent.OnFocusChanged -> {
                _viewState.value = _viewState.value.copy(
                    secureCodeState = _viewState.value.secureCodeState.copy(
                        isFocused = event.isFocused,
                        error = Pair(
                            !_viewState.value.secureCodeState.filled
                                    && _viewState.value.secureCodeState.isFocused,
                            "Please, fill the security code"
                        )
                    )
                )
            }

            is SecurityCodeTextFieldEvent.OnLengthChanged -> {
                _viewState.value = _viewState.value.copy(
                    secureCodeState = _viewState.value.secureCodeState.copy(
                        length = event.length
                    )
                )
            }

            is SecurityCodeTextFieldEvent.OnInputFilled -> {
                _viewState.value = _viewState.value.copy(
                    secureCodeState = _viewState.value.secureCodeState.copy(
                        filled = event.isFilled,
                        error = Pair(false, "")
                    )
                )
            }
        }
    }

    fun onCardNumberEvent(event: CardNumberTextFieldEvent) {

        when (event) {
            is CardNumberTextFieldEvent.OnFocusChanged -> {
                _viewState.value = _viewState.value.copy(
                    cardNumberState = _viewState.value.cardNumberState.copy(
                        isFocused = event.isFocused
                    )
                )
            }

            is CardNumberTextFieldEvent.OnLengthChanged -> {
                _viewState.value = _viewState.value.copy(
                    cardNumberState = _viewState.value.cardNumberState.copy(
                        length = event.length
                    )
                )
            }

            is CardNumberTextFieldEvent.OnLastFourDigitsFilled -> {
                _viewState.value = _viewState.value.copy(
                    cardNumberState = _viewState.value.cardNumberState.copy(
                        lastFourDigits = event.lastFourDigits
                    )
                )
            }

            is CardNumberTextFieldEvent.IsValid -> {
                _viewState.value = _viewState.value.copy(
                    cardNumberState = _viewState.value.cardNumberState.copy(
                        isValid = event.isValid,
                        error = Pair(!event.isValid, "Invalid card number")
                    )
                )
            }

            is CardNumberTextFieldEvent.OnBinChanged -> {
                if ((event.cardBin?.length ?: 0) < CARD_NUMBER_BIN_LENGTH) {
                    _viewState.value =
                        _viewState.value.copy(
                            cardNumberState = _viewState.value.cardNumberState.copy(image = null),
                            installmentsState = _viewState.value.installmentsState.copy(showList = false)
                        )
                    updateCardMaskState(DEFAULT_MAX_CARD_LENGTH)
                } else {
                    getInstallment(
                        bin = event.cardBin.orEmpty(),
                        amount = 1000.0.toBigDecimal(),
                    )
                    getPaymentMethods(bin = event.cardBin.orEmpty())
                }

                _viewState.value = _viewState.value.copy(
                    cardNumberState = _viewState.value.cardNumberState.copy(
                        cardBin = event.cardBin
                    )
                )

                getInstallment(
                    bin = event.cardBin.orEmpty(),
                    amount = 1000.0.toBigDecimal(),
                )
            }
        }
    }

    fun onInstallmentSelected(value: Installment) {
        _viewState.value = _viewState.value.copy(
            installmentsState = _viewState.value.installmentsState.copy(
                selectedInstallment = value
            )
        )
    }

    fun onIdentificationTypeValueChanged(value: String) {
        _viewState.value = _viewState.value.copy(
            identificationState = _viewState.value.identificationState.copy(
                identificationValue = value
            )
        )
    }

    fun onIdentificationTypeChanged(identificationType: IdentificationType) {
        _viewState.value = _viewState.value.copy(
            identificationState = _viewState.value.identificationState.copy(
                selectedIdentification = identificationType,
            )
        )
    }

    fun onCardHolderNameChanged(value: String) {
        _viewState.value = _viewState.value.copy(
            identificationState = _viewState.value.identificationState.copy(
                identificationNameValue = value
            )
        )
    }


    private fun updateCardMaskState(cardLength: Int) {
        _viewState.value = _viewState.value.copy(
            cardNumberState = _viewState.value.cardNumberState.copy(
                maxLength = cardLength,
                mask = when (cardLength) {
                    8 -> CARD_LENGTH_8_MASK
                    9 -> CARD_LENGTH_9_MASK
                    10 -> CARD_LENGTH_10_MASK
                    11 -> CARD_LENGTH_11_MASK
                    12 -> CARD_LENGTH_12_MASK
                    13 -> CARD_LENGTH_13_MASK
                    14 -> CARD_LENGTH_14_MASK
                    15 -> CARD_LENGTH_15_MASK
                    16 -> DEFAULT_CARD_MASK
                    17 -> CARD_LENGTH_17_MASK
                    19 -> CARD_LENGTH_19_MASK
                    else -> DEFAULT_CARD_MASK
                }
            )
        )
    }
}