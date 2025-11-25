package com.example.gogobus.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.gogobus.domain.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.userDataStore by preferencesDataStore("user_prefs")

@Singleton
class UserDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val USER_ID = intPreferencesKey("user_id")
        private val USERNAME = stringPreferencesKey("username")
        private val EMAIL = stringPreferencesKey("email")
        private val ROLE = stringPreferencesKey("role")
    }

    /** Guarda los datos del usuario */
    suspend fun saveUser(user: User) {
        context.userDataStore.edit { prefs ->
            prefs[USER_ID] = user.id
            prefs[USERNAME] = user.username
            prefs[EMAIL] = user.email
            prefs[ROLE] = user.role
        }
    }

    /** Devuelve un Flow<User?> para observar los cambios */
    fun getUser() = context.userDataStore.data.map { prefs ->
        val id = prefs[USER_ID] ?: return@map null
        val username = prefs[USERNAME] ?: ""
        val email = prefs[EMAIL] ?: ""
        val role = prefs[ROLE] ?: ""
        User(id, username, email, role)
    }

    /** Devuelve el usuario actual (una sola vez) */
    suspend fun getUserOnce(): User? {
        val prefs = context.userDataStore.data.first()
        val id = prefs[USER_ID] ?: return null
        val username = prefs[USERNAME] ?: ""
        val email = prefs[EMAIL] ?: ""
        val role = prefs[ROLE] ?: ""
        return User(id, username, email, role)
    }

    /** Borra todos los datos del usuario */
    suspend fun clear() {
        context.userDataStore.edit { it.clear() }
    }
}
