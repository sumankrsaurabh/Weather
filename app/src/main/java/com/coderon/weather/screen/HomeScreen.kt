package com.coderon.weather.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.coderon.weather.R
import com.coderon.weather.database.WeatherDataBase
import com.coderon.weather.database.converters.toLocal
import com.coderon.weather.model.BaseModel
import com.coderon.weather.screen.components.AirSpeedAndDirection
import com.coderon.weather.screen.components.CurrentWeather
import com.coderon.weather.screen.components.MoonriseMoonset
import com.coderon.weather.screen.components.RealFeelTemperature
import com.coderon.weather.screen.components.RelativeHumidity
import com.coderon.weather.screen.components.SunriseSunset
import com.coderon.weather.screen.components.Text
import com.coderon.weather.screen.components.TodayForecast
import com.coderon.weather.screen.components.UvIndex
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
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .safeContentPadding()
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            imageVector = Icons.Rounded.Menu, contentDescription = "menu",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { navController.navigate("search") }) {
                        Icon(
                            imageVector = Icons.Rounded.Search, contentDescription = "Search",
                            tint = Color.White
                        )
                    }
                }
            }
            item {
                CurrentWeather(
                    city = "${viewModel.city.collectAsState().value}",
                    chanceOfRain = todayForecastResult.first().rainProbability,
                    temperature = todayForecastResult.first().temperature.value.toInt(),
                    weatherIcon = todayForecastResult.first().weatherIcon
                )
            }
            item { TodayForecast(todayForecastResult) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { WeeklyForecast(dailyForecastResult.dailyForecasts) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        UvIndex(UvIndex = todayForecastResult.first().uvIndexText)
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        RelativeHumidity(humidity = todayForecastResult.first().relativeHumidity)
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        AirSpeedAndDirection(
                            direction = dailyForecastResult.dailyForecasts.first().day.wind.direction.degrees.toFloat(),
                            speed = "${dailyForecastResult.dailyForecasts.first().day.wind.speed.value}" +
                                    " ${dailyForecastResult.dailyForecasts.first().day.wind.speed.unit}",
                        )
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        RealFeelTemperature(realFeelTemperature = todayForecastResult.first().realFeelTemperature.value.toInt())
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                SunriseSunset(
                    sunrise = dailyForecastResult.dailyForecasts.first().sun.epochRise,
                    sunset = dailyForecastResult.dailyForecasts.first().sun.epochSet,
                    currentTime = (System.currentTimeMillis()) / 1000
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                MoonriseMoonset(
                    moonRise = dailyForecastResult.dailyForecasts.first().moon.epochRise,
                    moonSet = dailyForecastResult.dailyForecasts.first().moon.epochSet,
                    moonDescription = dailyForecastResult.dailyForecasts.first().moon.phase
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(text = "Data provided in part by")
                    Image(
                        painter = painterResource(id = R.drawable.accuweather_logo),
                        contentDescription = "Acu"
                    )
                }
            }
        }
    }
}
