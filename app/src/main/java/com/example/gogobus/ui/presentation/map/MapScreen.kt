package com.example.gogobus.ui.presentation.map

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gogobus.R
import com.example.gogobus.domain.model.Location
import com.example.gogobus.ui.theme.OrangeSecondary
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    origin: Location,
    destination: Location,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel()
) {
    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    val uiState by viewModel.mapUiState.collectAsState()
    val cameraPositionState = rememberCameraPositionState()

    // Cargar ruta
    LaunchedEffect(origin, destination) {
        viewModel.getTravelRoute(origin, destination)
    }

    // CENTRAR CÁMARA CUANDO LA RUTA LLEGUE
    LaunchedEffect(uiState.route) {
        if (uiState.route.isNotEmpty()) {
            val boundsBuilder = LatLngBounds.Builder()
            uiState.route.forEach { boundsBuilder.include(it) }
            val bounds = boundsBuilder.build()

            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngBounds(bounds, 120)
            )
        }
    }

    Box(modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            properties = properties,
            uiSettings = uiSettings,
            cameraPositionState = cameraPositionState
        ) {
            Polyline(
                points = uiState.route,
                color = Color(0xFF007AFF),
                width = 10f,
                jointType = JointType.ROUND,
            )

            if(uiState.origin != null && uiState.destination != null){
                Marker(state = uiState.origin!!, title = origin.name)
                Marker(state = uiState.destination!!, title = destination.name)
            }

        }

        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .align(Alignment.TopStart),
        ){
            Icon(
                painter = painterResource(R.drawable.chevron_left_24),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .clickable {
                        onBack()
                    }
                    .background(
                        color = OrangeSecondary,
                        shape = RoundedCornerShape(100.dp)
                    )
                    .padding(2.dp)
            )
        }


        // ⭐️ BLUR OVERLAY + LOADER ELEGANTE (estilo Uber)
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(25.dp) // BLUR suave estilo Uber
            )

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color.White.copy(alpha = 0.9f),
                    strokeWidth = 4.dp
                )
            }
        }
    }
}
