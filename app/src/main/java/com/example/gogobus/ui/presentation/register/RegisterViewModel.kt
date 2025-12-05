package com.example.gogobus.ui.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gogobus.domain.usecase.RegisterUseCase
import com.example.gogobus.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel() {
    private val _registerState = MutableStateFlow<RegisterUiState>(RegisterUiState())
    val registerState = _registerState.asStateFlow()

    private val _registerEvent = MutableSharedFlow<RegisterUiEvent>()
    val registerEvent = _registerEvent.asSharedFlow()

    fun register(){
        viewModelScope.launch {
            if (!_registerState.value.isFormFilled) {
                _registerEvent.emit(RegisterUiEvent.ShowError("Todos los campos son obligatorios"))
                return@launch
            }

            if(_registerState.value.registerRequest.password != _registerState.value.registerRequest.password2) {
                _registerEvent.emit(RegisterUiEvent.ShowError("Las contraseñas no coinciden"))
                return@launch
            }

            if (!_registerState.value.areTermsAccepted) {
                _registerEvent.emit(RegisterUiEvent.ShowError("Debes aceptar los términos y condiciones"))
                return@launch
            }

            _registerState.value = _registerState.value.copy(
                isLoading = true
            )
            val response = registerUseCase(_registerState.value.registerRequest)
            when(response){
                is Resource.Error -> {
                    _registerEvent.emit(RegisterUiEvent.ShowError(response.message))
                }
                Resource.Loading -> {}
                is Resource.Success<*> -> {
                    _registerEvent.emit(RegisterUiEvent.NavigateToHome)
                }
            }
            _registerState.value = _registerState.value.copy(
                isLoading = false
            )
        }
    }

    private fun validateForm() {
        val form = _registerState.value.registerRequest
        val isFilled = form.username.isNotEmpty() &&
                form.email.isNotEmpty() &&
                form.password.isNotEmpty() &&
                form.password2.isNotEmpty()
        _registerState.value = _registerState.value.copy(isFormFilled = isFilled)
    }

    fun updateTermsAccepted(areAccepted: Boolean) {
        _registerState.value = _registerState.value.copy(areTermsAccepted = areAccepted)
    }

    fun updateUserName(username: String){
        _registerState.value = _registerState.value.copy(
            registerRequest = _registerState.value.registerRequest.copy(username = username)
        )
        validateForm()
    }

    fun updateEmail(email: String){
        _registerState.value = _registerState.value.copy(
            registerRequest = _registerState.value.registerRequest.copy(email = email)
        )
        validateForm()
    }

    fun updatePassword(password: String){
        _registerState.value = _registerState.value.copy(
            registerRequest = _registerState.value.registerRequest.copy(password = password)
        )
        validateForm()
    }

    fun updatePassword2(password2: String){
        _registerState.value = _registerState.value.copy(
            registerRequest = _registerState.value.registerRequest.copy(password2 = password2)
        )
        validateForm()
    }

}