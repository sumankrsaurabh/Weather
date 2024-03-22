package com.coderon.weather.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.coderon.weather.database.WeatherDataBase
import com.coderon.weather.screen.components.CurrentWeather
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    navController: NavController,
    db: WeatherDataBase = koinInject(),
    viewModel: HomeViewModel = viewModel(),
) {

    Column(
        modifier = Modifier.padding(horizontal = 24.dp)
            .fillMaxSize()
            .safeContentPadding()
    ) {
        CurrentWeather(
            city = "Begusarai",
            chanceOfRain = 50,
            temperature = 30,
            weatherIcon = 20
        )
    }
}
