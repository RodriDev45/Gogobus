package com.example.gogobus.ui.presentation.rewards

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RewardsViewModel @Inject constructor(
    // Aquí puedes inyectar los repositorios o casos de uso
    // que necesitarás para obtener y gestionar los datos
    // de las recompensas, como la cantidad de puntos del usuario.
) : ViewModel() {
    // Por ahora, el ViewModel está vacío. Aquí es donde
    // agregarías funciones, StateFlows y otra lógica para
    // la interacción de la UI y los datos.
}
