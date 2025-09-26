package com.example.gogobus.presentation.tracking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gogobus.R
import com.example.gogobus.ui.theme.OrangeGogobus
import com.example.gogobus.ui.theme.TextGrayDark
import com.example.gogobus.ui.theme.White
import com.example.gogobus.ui.theme.IconActive
import androidx.navigation.NavController
import com.example.gogobus.navigation.Destinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackingScreen(
    navController: NavController,
    viewModel: TrackingViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(IconActive),
        topBar = {
            TopAppBar(
                title = { Text(text = "Rastreo de Bus") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Botón de retroceso"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Placeholder for the map (simulated with an image)
            Image(
                painter = painterResource(id = R.drawable.image_map),
                contentDescription = "Mapa de seguimiento",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Main container for tracking information
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Drag handle bar
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color.Gray.copy(alpha = 0.5f))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Card header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Current Location",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = TextGrayDark
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "On the trip",
                            tint = OrangeGogobus,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "On The Trip",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = OrangeGogobus
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Current location
                Text(
                    text = "Kartonegoro Terminal, Ngawi, East Jawa",
                    color = TextGrayDark,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "HDWP+2M7, Mojorejo, Griyoto, Ngawi, Ngawi Regency, East Java",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )

                // Divider line
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.LightGray)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Next stop
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Next Stop",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = TextGrayDark
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Stop list items
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Info, // Reemplazar con el icono de parada
                            contentDescription = "Next Stop",
                            tint = OrangeGogobus,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "SAE Ngawi Restaurant",
                            color = TextGrayDark,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Jl. Raya Ngawi - Solo No.KM.4, Gemarang Barat, Watualang, Ngawi",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn, // Reemplazar con el icono de parada
                            contentDescription = "Old Ngawi Bus Terminal",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Old Ngawi Bus Terminal",
                            color = TextGrayDark,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Jl. Ir. Soekarno, Klitik, Kec. Geneng, Kab. Ngawi",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Button to navigate to Home
                    Button(
                        onClick = { navController.navigate(Destinations.Home.route) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = OrangeGogobus)
                    ) {
                        Text(text = "Ir a la pantalla principal")
                    }
                }
            }
        }
    }
}
