package com.example.gogobus.ui.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gogobus.data.local.TokenDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Base64
import com.example.gogobus.domain.usecase.RefreshTokenUseCase
import com.example.gogobus.domain.util.Resource
import org.json.JSONObject
import java.nio.charset.StandardCharsets

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val tokenDataStore: TokenDataStore,
    private val refreshTokenUseCase: RefreshTokenUseCase
) : ViewModel() {

    private val _isAuthenticated = MutableStateFlow<Boolean?>(null) // null = aún verificando
    val isAuthenticated: StateFlow<Boolean?> = _isAuthenticated

    init {
        checkAuthentication()
    }

    private fun decodeJwtPayload(token: String): JSONObject? {
        return try {
            val parts = token.split(".")
            if (parts.size != 3) return null
            val payload = String(Base64.decode(parts[1], Base64.URL_SAFE), StandardCharsets.UTF_8)
            JSONObject(payload)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun isTokenExpired(token: String): Boolean {
        val payload = decodeJwtPayload(token) ?: return true
        val exp = payload.optLong("exp", 0)
        if (exp == 0L) return true

        val now = System.currentTimeMillis() / 1000
        return now >= exp
    }

    private fun checkAuthentication() {
        viewModelScope.launch {
            val token = tokenDataStore.getAccessToken().first()

            if (token.isNullOrEmpty()) {
                _isAuthenticated.value = false
            } else {
                // Aquí podrías verificar si el token sigue siendo válido (expiración)
                val isValid = validateToken(token)

                if (isValid) {
                    _isAuthenticated.value = true
                } else {
                    val refreshed = tryRefreshToken()
                    _isAuthenticated.value = refreshed
                }
            }
        }
    }

    private fun validateToken(token: String): Boolean {
        return !isTokenExpired(token)
    }

    private suspend fun tryRefreshToken(): Boolean {
        val resource = refreshTokenUseCase()
        return resource is Resource.Success<String>
    }

    fun logout() {
        viewModelScope.launch {
            tokenDataStore.clearTokens()
            _isAuthenticated.value = false
        }
    }
}