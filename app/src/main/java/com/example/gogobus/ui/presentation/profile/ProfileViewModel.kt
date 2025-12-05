package com.example.gogobus.ui.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gogobus.domain.model.User
import com.example.gogobus.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _user = MutableStateFlow<Result<User>>(Result.Loading())
    val user: StateFlow<Result<User>> = _user

    fun getProfile() {
        viewModelScope.launch {
            // TODO: Implementar la logica para obtener el perfil del usuario
            _user.value = Result.Success(
                User(
                    id = 1,
                    username = "John Doe",
                    email = "john.doe@example.com",
                    role = "USER"
                )
            )
        }
    }
}
