package com.example.gogobus.ui.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gogobus.R
import com.example.gogobus.ui.components.buttons.ButtonGoogle
import com.example.gogobus.ui.components.buttons.ButtonPrimary
import com.example.gogobus.ui.components.inputs.CheckBox
import com.example.gogobus.ui.components.inputs.EmailInput
import com.example.gogobus.ui.components.inputs.Input
import com.example.gogobus.ui.components.inputs.PasswordInput
import com.example.gogobus.ui.presentation.home.theme.AppTypography
import com.example.gogobus.ui.presentation.home.theme.Inter
import com.example.gogobus.ui.presentation.login.LoginScreen
import com.example.gogobus.ui.theme.GogobusTheme

@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {
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
            text = "Registrarse",
            style = AppTypography.headline32SemiBold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Crea una cuenta y vive una experiencia de viaje inolvidable",
            style = AppTypography.body12Regular,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(24.dp))

        Input(
            value = "",
            onValueChange = {  },
            modifier = modifier,
            placeholder = "Nombre completo",
            leadingIcon = {
                Icon(imageVector = Icons.Default.Badge, contentDescription = "Email Icon")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        EmailInput(
            email = "",
            onEmailChange = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordInput(
            password = "",
            onPasswordChange = {},
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordInput(
            password = "",
            onPasswordChange = {},
            placeholder = "Confirmar contraseña"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CheckBox(
            checked = false,
            onCheckedChange = {},
            content = {
                val annotatedText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            fontFamily = Inter,
                            color = Color.White
                        )
                    ) {
                        append("He leído y acepto los ")
                    }

                    pushStringAnnotation(tag = "TERMS", annotation = "terms")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            fontFamily = Inter,
                            textDecoration = TextDecoration.Underline,
                            color = Color.White
                        )
                    ) {
                        append("Términos y Condiciones")
                    }
                    pop()
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            fontFamily = Inter,
                            color = Color.White
                        )
                    ) {
                        append(" de Gogobus.")
                    }
                }

                ClickableText(
                    text = annotatedText,
                    style = MaterialTheme.typography.bodyMedium,
                    onClick = { offset ->
                        annotatedText.getStringAnnotations("TERMS", offset, offset)
                            .firstOrNull()?.let {  }
                    }
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonPrimary(
            onClick = {},
            text = "Registrarse",
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
                text = "O registrese con",
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
                text = "¿Ya tienes una cuenta?",
                color = Color.White,
                style = AppTypography.body14Light,
                modifier = Modifier.padding(end = 4.dp)
            )
            TextButton(
                onClick = {},
                contentPadding = PaddingValues(horizontal = 0.dp)
            ){
                Text(
                    text = "Iniciar sesión",
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
private fun PreviewRegisterScreen() {
    GogobusTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                RegisterScreen()
            }
        }
    }
}