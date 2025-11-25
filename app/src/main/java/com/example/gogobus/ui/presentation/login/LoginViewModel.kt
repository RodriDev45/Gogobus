package com.example.gogobus.ui.presentation.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.gogobus.domain.usecase.LoginUseCase
import com.example.gogobus.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState())
    val loginState = _loginState.asStateFlow()
    private val _loginEvent = MutableSharedFlow<LoginUiEvent>()
    val loginEvent: SharedFlow<LoginUiEvent> = _loginEvent



    fun login(){
        Log.d("LOGIN", "Entrando al login")
        viewModelScope.launch {
            try {
                _loginState.value = _loginState.value.copy(
                    isLoading = true
                )
                val response = loginUseCase(_loginState.value.email, _loginState.value.password)

                when(response){
                    is Resource.Error -> {
                        _loginEvent.emit(LoginUiEvent.ShowError(response.message))
                    }
                    Resource.Loading -> {

                    }
                    is Resource.Success<*> -> {
                        _loginEvent.emit(LoginUiEvent.NavigateToHome)
                    }
                }

                _loginState.value = _loginState.value.copy(
                    isLoading = false,
                )

            }catch (e: Exception){
                Log.e("Errores", "Error en login", e)
                _loginState.value = _loginState.value.copy(
                    isLoading = false,
                    response = null,
                )
            }
        }
    }

    fun updateEmail(email: String) {
        _loginState.value = _loginState.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _loginState.value = _loginState.value.copy(password = password)
    }
}