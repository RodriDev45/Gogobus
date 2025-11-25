package com.example.gogobus.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.onboardingDataStore by preferencesDataStore("onboarding_prefs")

@Singleton
class OnboardingDataStore @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    }

    // Guarda si el usuario completó el onboarding
    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.onboardingDataStore.edit {
            it[ONBOARDING_COMPLETED] = completed
        }
    }

    // Retorna un Flow<Boolean> para observar el estado
    fun isOnboardingCompleted() = context.onboardingDataStore.data.map {
        it[ONBOARDING_COMPLETED] ?: false
    }

    // Retorna un valor puntual (no Flow)
    suspend fun hasCompletedOnboarding(): Boolean {
        return context.onboardingDataStore.data.map {
            it[ONBOARDING_COMPLETED] ?: false
        }.first()
    }

    // Borra la preferencia si es necesario
    suspend fun clear() {
        context.onboardingDataStore.edit { it.clear() }
    }
}
