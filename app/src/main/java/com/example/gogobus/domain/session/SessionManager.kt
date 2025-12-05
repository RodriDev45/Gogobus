package com.example.gogobus.domain.session

import com.example.gogobus.data.local.TokenDataStore
import com.example.gogobus.domain.usecase.RefreshTokenUseCase
import com.example.gogobus.domain.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import android.util.Base64
import android.util.Log
import org.json.JSONObject
import java.nio.charset.StandardCharsets

@Singleton
class SessionManager @Inject constructor(
    private val tokenDataStore: TokenDataStore,
    private val refreshTokenUseCase: RefreshTokenUseCase
) {

    private val _isAuthenticated = MutableStateFlow<Boolean?>(null)
    val isAuthenticated: StateFlow<Boolean?> = _isAuthenticated
    private val _firstOnboarding = MutableStateFlow(false)
    val firstOnboarding: StateFlow<Boolean?> = _firstOnboarding

    init {
        checkAuthentication()
    }

    fun setFirstOnboarding(value: Boolean) {
        _firstOnboarding.value = value
    }
    suspend fun getUserNameFromToken(): String? {
        val token = tokenDataStore.getAccessToken().first()
        if (token.isNullOrEmpty()) return null

        val payload = decodeJwtPayload(token)
        return payload?.optString("username")
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

    fun checkAuthentication() {
        CoroutineScope(Dispatchers.IO).launch {
            val token = tokenDataStore.getAccessToken().first()

            if (token.isNullOrEmpty()) {
                _isAuthenticated.value = false
            } else {
                val isValid = !isTokenExpired(token)

                if (isValid) {
                    _isAuthenticated.value = true
                } else {
                    val refreshed = tryRefreshToken()
                    _isAuthenticated.value = refreshed
                }
            }
        }
    }

    private suspend fun tryRefreshToken(): Boolean {
        Log.d("INNNNN", "Intentando refresh token")
        val result = refreshTokenUseCase()
        return result is Resource.Success<String>
    }

    fun finishSession() {
        _isAuthenticated.value = false
    }

}