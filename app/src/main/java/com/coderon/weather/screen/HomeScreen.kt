package com.coderon.weather.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.coderon.weather.database.WeatherDataBase
import com.coderon.weather.database.converters.toLocal
import com.coderon.weather.model.BaseModel
import com.coderon.weather.screen.components.Loading
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    navController: NavController,
    db: WeatherDataBase = koinInject(),
    viewModel: HomeViewModel = viewModel(),
) {
    val locations by viewModel.location.collectAsState()

    // Fetch local locations from the database or navigate to the first location
    LaunchedEffect(true) {
        viewModel.getUserLocation()
    }
    AnimatedVisibility(visible = locations is BaseModel.Loading) {
        Loading()
    }
    AnimatedVisibility(visible = locations is BaseModel.Success) {
        val data = locations as BaseModel.Success
        val loc = data.data.first()
        LaunchedEffect(true) {
            db.locationDao().addCity(loc.toLocal())
        }
        navController.navigate("weather/${loc.key}/${loc.englishName}")
    }
}
