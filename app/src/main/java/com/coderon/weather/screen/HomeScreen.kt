package com.coderon.weather.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.coderon.weather.database.WeatherDataBase
import com.coderon.weather.database.converters.toLocal
import com.coderon.weather.model.BaseModel
import com.coderon.weather.screen.components.CurrentWeather
import com.coderon.weather.screen.components.Loading
import com.coderon.weather.screen.components.TodayForecast
import com.coderon.weather.screen.components.WeeklyForecast
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    navController: NavController,
    db: WeatherDataBase = koinInject(),
    viewModel: WeatherViewModel = viewModel(),
) {

    val location by viewModel.location.collectAsState()
    val todayForecast by viewModel.hourlyForecast.collectAsState()
    val dailyForecast by viewModel.dailyForecast.collectAsState()

    LaunchedEffect(true) {
        viewModel.getUserLocation()
    }

    AnimatedVisibility(visible = location is BaseModel.Success) {
        val locationResult = (location as BaseModel.Success).data.first()
        LaunchedEffect(locationResult) {
            db.locationDao().addCity(locationResult.toLocal())
            viewModel.setCity(locationResult.englishName)
            viewModel.getDailyForecast(locationResult.key)
            viewModel.getHourlyForecast(locationResult.key)
        }
    }

    AnimatedVisibility(visible = todayForecast is BaseModel.Success && dailyForecast is BaseModel.Success) {
        val todayForecastResult = (todayForecast as BaseModel.Success).data
        val dailyForecastResult = (dailyForecast as BaseModel.Success).data
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxSize()
                .safeContentPadding()
        ) {
            item {
                CurrentWeather(
                    city = "${viewModel.city.collectAsState().value}",
                    chanceOfRain = todayForecastResult.first().rainProbability,
                    temperature = todayForecastResult.first().temperature.value.toInt(),
                    weatherIcon = todayForecastResult.first().weatherIcon
                )
            }
            item { TodayForecast(todayForecastResult) }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item { WeeklyForecast(dailyForecastResult.dailyForecasts) }
        }
    }

    AnimatedVisibility(
        visible = todayForecast is BaseModel.Loading ||
                dailyForecast is BaseModel.Loading ||
                location is BaseModel.Loading
    ) {
        Loading("Loading")
    }
}
