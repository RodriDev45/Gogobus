package com.example.gogobus.ui.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gogobus.ui.onboarding.model.OnboardingPage
import com.example.gogobus.ui.presentation.home.theme.AppTypography

// components/OnboardingContent.kt
@Composable
fun OnboardingContent(
    page: OnboardingPage,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(Modifier.height(24.dp))
        Text(
            text = page.title,
            style = AppTypography.headline24SemiBold,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = page.description,
            style = AppTypography.body14Regular,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        Spacer(Modifier.height(70.dp))
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(0.85f)
        )
    }
}
