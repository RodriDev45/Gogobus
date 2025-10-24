package com.example.gogobus.ui.presentation.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AltRoute
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogobus.ui.theme.GogobusTheme
import com.example.gogobus.ui.theme.OrangeSecondary

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
)

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Activity", Icons.Outlined.AltRoute),
        BottomNavItem("Notification", Icons.Default.Notifications),
        BottomNavItem("Profile", Icons.Default.Person)
    )
    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar(
        modifier = modifier,
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = OrangeSecondary,
                    selectedTextColor = OrangeSecondary,
                    unselectedIconColor = Color.Gray.copy(alpha = 0.8f),
                    unselectedTextColor = Color.Gray.copy(alpha = 0.8f),
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    GogobusTheme {
        BottomNavigationBar()
    }
}
