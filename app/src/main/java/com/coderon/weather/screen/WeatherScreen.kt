package com.coderon.weather.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.coderon.weather.model.BaseModel

@Composable
fun WeatherScreen(
    navController: NavController,
    locationKey: String,
    locationName: String,
    viewModel: WeatherViewModel = viewModel(),
) {
    val dailyForecasts by viewModel.dailyForecast.collectAsState()
    val hourlyForecasts by viewModel.hourlyForecast.collectAsState()

    viewModel.getHourlyForecast(locationKey)
    viewModel.getDailyForecast(locationKey)

    Box(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
        AnimatedVisibility(
            visible = hourlyForecasts is BaseModel.Success
                    && dailyForecasts is BaseModel.Success
        ) {
            val hourly = hourlyForecasts as BaseModel.Success
            val daily = dailyForecasts as BaseModel.Success
            val hourlyData = hourly.data
            val dailyData = daily.data.dailyForecasts


            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = locationName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        color = Color.White
                    )
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "${hourlyData.first().temperature.value.toInt()}°${hourlyData.first().temperature.unit}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 80.sp,
                        color = Color.White
                    )
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                    Text(
                        text = "Min ${dailyData.first().temperature.minimum.value}" +
                                "°${dailyData.first().temperature.minimum.unit}"
                                + " / Max ${dailyData.first().temperature.minimum.value}" +
                                "°${dailyData.first().temperature.minimum.unit}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                    Text(
                        text = "Relative humidity ${hourlyData.first().relativeHumidity}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White
                    )

                }
            }
        }
        AnimatedVisibility(
            visible = hourlyForecasts is BaseModel.Loading
                    && dailyForecasts is BaseModel.Loading
        ) {
            Loading()
        }
    }
}

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

fun Int.fixIcon(): String {
    return if (this < 10) {
        "0${this}"
    } else {
        toString()
    }
}


