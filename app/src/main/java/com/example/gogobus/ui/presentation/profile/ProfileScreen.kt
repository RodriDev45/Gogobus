package com.example.gogobus.ui.presentation.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gogobus.R
import com.example.gogobus.domain.model.User
import com.example.gogobus.ui.theme.*
import com.example.gogobus.utils.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavToHome: () -> Unit
) {
    val userState by viewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProfile()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", color = White) },
                navigationIcon = {
                    IconButton(onClick = { onNavToHome() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BluePrimary
                )
            )
        },
        containerColor = BackgroundLight
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (val state = userState) {
                is Result.Loading -> {
                    CircularProgressIndicator(color = OrangeSecondary)
                }
                is Result.Success -> {
                    val user = state.data
                    if (user != null) {
                        ProfileContent(user = user, onLogout = { /* TODO: Logout logic */ })
                    }
                }
                is Result.Error -> {
                    Text(text = "Error: ${state.message}", color = ErrorText)
                }
            }
        }
    }
}

@Composable
fun ProfileContent(user: User, onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // User Info Header
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = user.username,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = TextDark
            )
            Text(
                text = user.email,
                style = MaterialTheme.typography.bodyLarge,
                color = TextGray
            )
        }

        // Account Settings
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SurfaceLight),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column {
                Text(
                    text = "Account Settings",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp),
                    color = TextDark
                )
                ProfileOptionItem(icon = Icons.Outlined.AccountCircle, label = "Edit Profile") { /*TODO*/ }
                Divider(color = SurfaceCardBorder, thickness = 1.dp)
                ProfileOptionItem(icon = Icons.Outlined.Lock, label = "Change Password") { /*TODO*/ }
                Divider(color = SurfaceCardBorder, thickness = 1.dp)
                ProfileOptionItem(icon = Icons.Outlined.CreditCard, label = "Payment Methods") { /*TODO*/ }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Support
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SurfaceLight),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column {
                Text(
                    text = "Support",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp),
                    color = TextDark
                )
                ProfileOptionItem(icon = Icons.Outlined.Email, label = "Contact Support") { /*TODO*/ }
                Divider(color = SurfaceCardBorder, thickness = 1.dp)
                ProfileOptionItem(icon = Icons.Outlined.Info, label = "FAQ") { /*TODO*/ }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Logout Button
        OutlinedButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, ErrorText)
        ) {
            Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Logout", tint = ErrorText)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Logout", color = ErrorText, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ProfileOptionItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = label, tint = BlueAccent)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = TextDark,
            modifier = Modifier.weight(1f)
        )
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = TextGray)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    GogobusTheme {
        ProfileContent(
            user = User(
                id = 1,
                username = "John Doe",
                email = "john.doe@example.com",
                role = "USER"
            ),
            onLogout = {}
        )
    }
}
