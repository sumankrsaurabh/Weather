package com.coderon.weather.screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cloud
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigation(
    navHostController: NavHostController,
) {
    val items = listOf(
        BottomNavigation(0, "My locations", Icons.Rounded.LocationOn),
        BottomNavigation(1, "Weather", Icons.Rounded.Cloud),
        BottomNavigation(2, "Settings", Icons.Rounded.Settings)
    )
    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar(
    ) {
        items.forEach {
            NavigationBarItem(
                selected = it.index == selected.intValue,
                onClick = { selected.intValue = it.index },
                icon = { Icon(imageVector = it.icon, contentDescription = null) },
                label = {
                    Text(text = it.text)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Yellow,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.Yellow,
                    unselectedTextColor = Color.White,
                    indicatorColor = Color.Transparent,
                ))
        }
    }
}

data class BottomNavigation(
    val index: Int,
    val text: String,
    val icon: ImageVector,
)

@Preview
@Composable
fun TextStyle() {
    BottomNavigation(navHostController = rememberNavController())
}