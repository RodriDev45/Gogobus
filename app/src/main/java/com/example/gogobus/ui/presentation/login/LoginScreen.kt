package com.example.gogobus.ui.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lint.kotlin.metadata.Visibility
import com.example.gogobus.R
import com.example.gogobus.navigation.AppNavHost
import com.example.gogobus.ui.components.buttons.ButtonGoogle
import com.example.gogobus.ui.components.buttons.ButtonPrimary
import com.example.gogobus.ui.components.inputs.CheckBox
import com.example.gogobus.ui.components.inputs.EmailInput
import com.example.gogobus.ui.components.inputs.Input
import com.example.gogobus.ui.components.inputs.PasswordInput
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.theme.GogobusTheme

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var passwordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(R.mipmap.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Iniciar Sesión",
            style = AppTypography.headline32SemiBold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Ingrese sus credenciales para iniciar sesión",
            style = AppTypography.body12Regular,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(24.dp))

        EmailInput(
            email = "",
            onEmailChange = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordInput(
            password = "",
            onPasswordChange = {}
        )
        Spacer(modifier = Modifier.height(2.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CheckBox(
                checked = false,
                onCheckedChange = {},
                content = {
                    Text(
                        text = "Recordarme",
                        modifier = Modifier.padding(start = 1.dp),
                        color = Color.White,
                        style = AppTypography.body14Regular,
                    )
                }
            )

            TextButton(
                onClick = {}
            ) {
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = Color.White,
                    style = AppTypography.body14Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        ButtonPrimary(
            onClick = {},
            text = "Iniciar Sesión",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Color.White.copy(alpha = 0.5f))
            )
            Text(
                text = "O incie sesión con",
                color = Color.White,
                style = AppTypography.body14Light,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Color.White.copy(alpha = 0.5f))
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        ButtonGoogle(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "¿No tienes una cuenta?",
                color = Color.White,
                style = AppTypography.body14Light,
                modifier = Modifier.padding(end = 4.dp)
            )
            TextButton(
                onClick = {},
                contentPadding = PaddingValues(horizontal = 0.dp)
            ){
                Text(
                    text = "Regístrate",
                    color = Color.White,
                    style = AppTypography.body14Bold
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewLoginScreen() {
    GogobusTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                LoginScreen()
            }
        }
    }
}