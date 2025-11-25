package com.example.gogobus.data.remote.interceptors

import com.example.gogobus.data.local.TokenDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.FormBody
import org.json.JSONObject

class AuthInterceptor(
    private val tokenDataStore: TokenDataStore
) : Interceptor {

    private val publicEndpoints = listOf(
        "users/login/",
        "users/register/",
        "users/token/refresh/",
        "trips/locations/"
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val requestUrl = request.url.toString()

        val isPublicEndpoint = publicEndpoints.any { endpoint ->
            requestUrl.contains(endpoint, ignoreCase = true)
        }

        // ✅ 1. Si es público, no agregamos token
        if (isPublicEndpoint) return chain.proceed(request)

        // ✅ 2. Agregamos el access token actual
        val token = runBlocking { tokenDataStore.getAccessToken().first() }
        if (!token.isNullOrEmpty()) {
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        }

        var response = chain.proceed(request)

        // ✅ 3. Si el token expiró (401), intentamos refrescarlo
        if (response.code == 401) {
            response.close() // Cerramos la respuesta anterior
            val newToken = runBlocking { refreshAccessToken() }

            if (newToken != null) {
                // Guardamos el nuevo token
                runBlocking { tokenDataStore.saveAccessToken(newToken) }

                // Reintentamos la petición original con el nuevo token
                val newRequest = request.newBuilder()
                    .removeHeader("Authorization")
                    .addHeader("Authorization", "Bearer $newToken")
                    .build()

                response = chain.proceed(newRequest)
            }
        }

        return response
    }

    /**
     * 🔄 Obtiene un nuevo access token usando el refresh token.
     */
    private fun refreshAccessToken(): String? = runBlocking {
        val refreshToken = tokenDataStore.getRefreshToken().first() ?: return@runBlocking null

        try {
            val client = OkHttpClient()

            // Tu endpoint de refresh
            val url = "https://gogobusbackend.onrender.com/api/users/token/refresh/"

            val requestBody = JSONObject().apply {
                put("refresh", refreshToken)
            }.toString().toRequestBody("application/json".toMediaType())

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val bodyString = response.body?.string()
                val json = JSONObject(bodyString ?: "{}")
                val newAccess = json.optString("access", null)
                response.close()
                return@runBlocking newAccess
            } else {
                response.close()
                return@runBlocking null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return@runBlocking null
        }
    }
}
