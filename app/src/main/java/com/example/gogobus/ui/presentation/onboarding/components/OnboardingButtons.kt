package com.example.gogobus.ui.presentation.onboarding.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.OrangeSecondary
import com.example.gogobus.ui.theme.White

// components/OnboardingButtons.kt
@Composable
fun OnboardingButtons(
    currentPage: Int,
    pageCount: Int,
    onNext: () -> Unit,
    onRegister: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 32.dp), // Increased vertical padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onNext,
            colors = ButtonDefaults.buttonColors(containerColor = OrangeSecondary),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), // Standardized height
            shape = RoundedCornerShape(16.dp) // Standardized corner radius
        ) {
            Text(
                text = if (currentPage == pageCount - 1) "Get Started" else "Next",
                style = AppTypography.body16Bold // Bolder text for emphasis
            )
        }

        Spacer(Modifier.height(24.dp)) // Reduced spacer for a tighter look

        Row {
            Text("¿No tienes una cuenta? ",
                color = White,
                style = AppTypography.body14Regular
            )
            Text(
                text = "Crear Cuenta",
                color = OrangeSecondary,
                style = AppTypography.body14SemiBold,
                modifier = Modifier.clickable { onRegister() }
            )
        }
    }
}
