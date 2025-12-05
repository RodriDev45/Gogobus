package com.example.gogobus.ui.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gogobus.data.local.UserDataStore
import com.example.gogobus.domain.model.User
import com.example.gogobus.domain.session.SessionManager
import com.example.gogobus.domain.usecase.LogoutUseCase
import com.example.gogobus.domain.util.Resource
import com.example.gogobus.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userDataStore: UserDataStore,
    private val logoutUseCase: LogoutUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _user = MutableStateFlow<Result<User>>(Result.Loading())
    val user: StateFlow<Result<User>> = _user

    private val _uiProfileEvent = MutableSharedFlow<ProfileUiEvent>()
    val uiProfileEvent = _uiProfileEvent.asSharedFlow()

    private val _uiProfileState = MutableStateFlow(ProfileUiState())
    val uiProfileState: StateFlow<ProfileUiState> = _uiProfileState.asStateFlow()


    init {
        observeUser()
    }

    private fun observeUser() {
        viewModelScope.launch {
            userDataStore.getUser().collect { user ->
                user?.let {
                    _uiProfileState.value = _uiProfileState.value.copy(
                        user = it,
                    )
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _uiProfileState.value = _uiProfileState.value.copy(isLoading = true)
            val result = logoutUseCase()
            when(result){
                is Resource.Error -> {
                    _uiProfileEvent.emit(ProfileUiEvent.ShowError(result.message))
                }
                Resource.Loading -> Unit
                is Resource.Success<String> -> {
                    sessionManager.finishSession()
                }
            }
        }
    }


}
