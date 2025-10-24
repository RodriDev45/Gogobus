package com.example.gogobus.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.gogobus.R
import com.example.gogobus.ui.onboarding.components.OnboardingButtons
import com.example.gogobus.ui.onboarding.components.OnboardingContent
import com.example.gogobus.ui.onboarding.components.OnboardingIndicator
import com.example.gogobus.ui.onboarding.model.OnboardingPage
import androidx.compose.ui.tooling.preview.Preview
import com.example.gogobus.ui.theme.BlueAccent
import com.example.gogobus.ui.theme.BluePrimary

// OnboardingScreen.kt
@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    onRegister: () -> Unit
) {
    val pages = listOf(
        OnboardingPage(
            imageRes = R.drawable.onboard_1,
            title = "Consigue boletos de autobús desde cualquier lugar sin complicaciones",
            description = "Just book through our app and get tickets without coming to our agents."
        ),
        OnboardingPage(
            imageRes = R.drawable.onboard_2,
            title = "Obtén un bono en cada transacción de compra de boletos",
            description = "Acumula puntos con cada ticket y canjéalos por recompensas."
        ),
        OnboardingPage(
            imageRes = R.drawable.onboard_3,
            title = "Realice un seguimiento fácil de su ubicación actual y del estado de sus viajes",
            description = "Ofrecemos una función de seguimiento en vivo para que nunca pierdas tu destino."
        )
    )

    var currentPage by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        BlueAccent,
                        BluePrimary
                    ),
                    startY = 0f,
                    endY = 1500f
                )
            )
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnboardingIndicator(currentPage, pages.size)

        OnboardingContent(
            page = pages[currentPage],
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OnboardingButtons(
            currentPage = currentPage,
            pageCount = pages.size,
            onNext = {
                if (currentPage < pages.lastIndex) currentPage++
                else onFinish()
            },
            onRegister = onRegister
        )
    }
}



@Preview(showBackground = true) 
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(
        onFinish = {},
        onRegister = {}
    )
}
