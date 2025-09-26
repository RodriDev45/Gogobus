package com.example.gogobus.ui.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.R
import com.example.gogobus.ui.theme.IconActive
import com.example.gogobus.ui.theme.OrangeGogobus
import com.example.gogobus.ui.theme.White

// --- Componente del Ticket como Imagen ---
@Composable
fun ETicketImage() {
    Image(
        painter = painterResource(id = R.drawable.image_ticket),
        contentDescription = "E-Ticket",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp)
            .heightIn(min = 360.dp)
            .background(Color.Transparent)
    )
}

// --- Indicadores de progreso ---
@Composable
fun OnboardingIndicators(
    totalSteps: Int = 3,
    currentStep: Int = 1
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalSteps) { index ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .height(6.dp)
                    .width(32.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(
                        if (index == currentStep) White else White.copy(alpha = 0.4f)
                    )
            )
        }
    }
}
// --- Pantalla principal ---
@Composable
fun OnboardingScreen(
    onGetStartedClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0D1B2A),
                        IconActive.copy(alpha = 0.95f),
                        Color.Black.copy(alpha = 0.9f)
                    )
                )
            )
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // --- Indicadores arriba ---
                OnboardingIndicators(
                    totalSteps = 3,
                    currentStep = 1
                )


                // --- Texto superior ---
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .padding(top = 48.dp, bottom = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Consigue tu boleto de autobús desde cualquier lugar sin complicaciones",
                        style = MaterialTheme.typography.headlineSmall,
                        color = White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Simplemente reserve a través de GOGOBUS y obtenga boletos sin la molestia de venir a nuestros agentes.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = White.copy(alpha = 0.9f),
                        textAlign = TextAlign.Center
                    )
                }

                // --- Ticket como Imagen ---
                ETicketImage()

                Spacer(modifier = Modifier.height(30.dp))

                // --- Botón principal ---
                Button(
                    onClick = onGetStartedClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 32.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OrangeGogobus)
                ) {
                    Text(
                        "Get Started",
                        color = White,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                // --- Enlace de registro ---
                TextButton(
                    onClick = onRegisterClick,
                    modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
                ) {
                    Row {
                        Text(
                            text = "Don't have an account? ",
                            style = MaterialTheme.typography.bodyMedium,
                            color = White.copy(alpha = 0.8f)
                        )
                        Text(
                            text = "Register Account",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            color = White
                        )
                    }
                }
            }
        }
    }
}

// --- Preview ---
@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(onGetStartedClick = {}, onRegisterClick = {})
}
