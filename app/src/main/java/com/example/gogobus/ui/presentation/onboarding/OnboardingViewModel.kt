package com.example.gogobus.ui.presentation.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    // Aquí puedes inyectar casos de uso o repositorios, p. ej., para guardar el estado del onboarding
) : ViewModel() {

    // En un proyecto real, aquí podrías tener funciones para:
    // fun onUserClicksGetStarted() { /* Lógica antes de navegar */ }
    // fun onUserClicksRegister() { /* Lógica antes de navegar */ }

    // Por ahora, solo es una clase vacía para mantener la estructura MVVM.
}