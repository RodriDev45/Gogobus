package com.example.gogobus.ui.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gogobus.ui.theme.IconActive
import com.example.gogobus.ui.theme.White

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onTimeout: () -> Unit
) {
    // Escucha el estado del ViewModel para saber cuándo terminar el tiempo de espera
    val isSplashTimeOut by viewModel.isSplashTimeOut.collectAsState()

    // Si el tiempo de espera ha terminado, llama a la función de navegación
    LaunchedEffect(isSplashTimeOut) {
        if (isSplashTimeOut) {
            onTimeout()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(IconActive),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        GogobusLogo(
            modifier = Modifier.size(120.dp),
            tint = White
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "GOGOBUS",
            style = LocalTextStyle.current.copy(
                fontSize = 32.sp,
                color = White
            ),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

// Este Composable del logo se puede mover a una carpeta `core/ui/` para ser reutilizado
@Composable
private fun GogobusLogo(
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified
) {
    // Aquí iría el código del logo
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(onTimeout = {})
}
