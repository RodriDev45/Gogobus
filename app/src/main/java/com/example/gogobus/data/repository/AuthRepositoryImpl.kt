package com.example.gogobus.data.repository

import android.util.Log
import com.example.gogobus.data.local.TokenDataStore
import com.example.gogobus.data.local.UserDataStore
import com.example.gogobus.data.remote.GogobusApi
import com.example.gogobus.data.remote.api.UserApi
import com.example.gogobus.data.remote.dto.responses.toDomain
import com.example.gogobus.domain.model.ApiResponse
import com.example.gogobus.domain.model.AuthResponse
import com.example.gogobus.domain.model.LoginRequest
import com.example.gogobus.domain.model.LogoutRequest
import com.example.gogobus.domain.model.RegisterRequest
import com.example.gogobus.domain.repository.AuthRepository
import com.example.gogobus.domain.repository.BaseRepository
import com.example.gogobus.domain.session.SessionManager
import com.example.gogobus.domain.util.Resource
import com.example.gogobus.domain.util.parseErrorMessage
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: UserApi,
    private val dataStore: TokenDataStore,
    private val userDataStore: UserDataStore
): BaseRepository(),  AuthRepository {

    override suspend fun login(email: String, password: String): Resource<String> =
        safeApiCall(
            apiCall = { api.login(LoginRequest(email, password).toEntity()).toDomain { it.toDomain() } },
            mapData = { data ->

                dataStore.saveTokens(data.access, data.refresh)
                userDataStore.saveUser(data.user)

                "Inicio de sesión exitoso"
            }
        )

    override suspend fun register(registerRequest: RegisterRequest): Resource<String> =
        safeApiCall(
            apiCall = { api.register(registerRequest.toEntity()).toDomain { it.toDomain() } },
            mapData = {
                if(it != null){
                    dataStore.saveTokens(it.tokens.access, it.tokens.refresh)
                    userDataStore.saveUser(it.user)
                }
                "Registro exitoso"
            }
        )


    override suspend fun refreshAccessToken(): Resource<String> {
        return try {
            val refresh = dataStore.getRefreshToken().first()
                ?: return Resource.Error("No existe refresh token")

            Log.d("SessionManager", "🔄 Intentando refrescar token con: $refresh")

            val response = api.refreshToken(mapOf("refresh" to refresh))
            val newAccess = response.access

            if (!newAccess.isNullOrEmpty()) {
                dataStore.saveAccessToken(newAccess)
                Log.d("SessionManager", "✅ Nuevo access token guardado")
                Resource.Success("Token refresh success")
            } else {
                Log.e("SessionManager", "❌ Token refresh failed: access vacío")
                Resource.Error("Token refresh failed")
            }

        } catch (e: retrofit2.HttpException) {
            // Maneja errores HTTP (401, 403, 500, etc.)
            val code = e.code()
            Log.e("SessionManager", "⚠️ Error HTTP $code: ${e.message()}")

            if (code == 401) {
                // Token inválido o blacklisted
                dataStore.clearTokens()
                Resource.Error("Token inválido o expirado")
            } else {
                Resource.Error("Error del servidor ($code)")
            }

        } catch (e: Exception) {
            // Maneja errores inesperados
            Log.e("SessionManager", "💥 Error inesperado: ${e.localizedMessage}")
            Resource.Error(e.localizedMessage ?: "Error desconocido")
        }
    }


    override suspend fun logout(): Resource<String> {
        val refresh = dataStore.getRefreshToken().first()
        if (refresh != null) {
            val response = api.logout(LogoutRequest(refresh).toEntity())
            if (response.isSuccessful) {
                val body = response.body()
                dataStore.clearTokens()
                return Resource.Success(body?.message ?: "Logout exitoso")
            } else {
                dataStore.clearTokens()
                return Resource.Error("Error al cerrar sesión: ${response.code()}")
            }
        }
        return Resource.Error("No existe refresh token")
    }

}