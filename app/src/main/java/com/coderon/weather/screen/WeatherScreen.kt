package com.coderon.weather.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.ArrowDownward
import androidx.compose.material.icons.sharp.ArrowUpward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.coderon.weather.model.BaseModel
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun WeatherScreen(
    navController: NavController,
    locationKey: String,
    locationName: String,
    state:String,
    country: String,
    viewModel: WeatherViewModel = viewModel(),
) {
    val dailyForecasts by viewModel.dailyForecast.collectAsState()
    val hourlyForecasts by viewModel.hourlyForecast.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getHourlyForecast(locationKey)
        viewModel.getDailyForecast(locationKey)
    }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {

        Row(modifier = Modifier.padding(vertical = 32.dp)) {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowLeft,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navController.popBackStack()
                    },
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = locationName,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                Text(
                    text = "$state , $country",
                    color = Color.Gray
                )
            }
        }
        AnimatedVisibility(visible = hourlyForecasts is BaseModel.Success) {
            val data = hourlyForecasts as BaseModel.Success
            val temp = data.data.first().temperature
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = "${temp.value}°${temp.unit}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 80.sp,
                    color = Color.White,
                )
            }
        }
        AnimatedVisibility(visible = hourlyForecasts is BaseModel.Loading) {
            Loading()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Hourly Forecast",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(10.dp))
        AnimatedVisibility(visible = hourlyForecasts is BaseModel.Success) {
            val data = hourlyForecasts as BaseModel.Success
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(data.data) { forecast ->
                    Column(
                        modifier = Modifier
                            .size(100.dp, 140.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(colorScheme.secondary),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = SimpleDateFormat("H a").format(Date(forecast.epochDAteTime * 1000)),
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        AsyncImage(
                            modifier = Modifier.size(70.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://developer.accuweather.com/sites/default/files/${forecast.weatherIcon.fixIcon()}-s.png")
                                .build(), contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = forecast.temperature.value.toString() + "°" + forecast.temperature.unit,
                            color = Color.White
                        )
                    }
                }
            }
        }
        AnimatedVisibility(visible = hourlyForecasts is BaseModel.Loading) {
            Loading()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Hourly Forecast",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(10.dp))
        AnimatedVisibility(visible = dailyForecasts is BaseModel.Success) {

            val data = dailyForecasts as BaseModel.Success
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(data.data.dailyForecasts) { forecast ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(colorScheme.secondary)
                            .padding(start = 16.dp, end = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${SimpleDateFormat("d").format(Date(forecast.epochDate * 1000))}th",
                            color = Color.White
                        )
                        Row {
                            Icon(
                                imageVector = Icons.Sharp.ArrowDownward,
                                contentDescription = null,
                                tint = Color(0xffff5353)
                            )
                            Text(
                                text = "${forecast.temperature.minimum.value}°${forecast.temperature.minimum.unit}",
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.Sharp.ArrowUpward,
                                contentDescription = null,
                                tint = Color(0xff2eff8c)
                            )
                            Text(
                                text = "${forecast.temperature.maximum.value}°${forecast.temperature.maximum.unit}",
                                color = Color.White
                            )
                        }
                        AsyncImage(
                            modifier = Modifier.size(70.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://developer.accuweather.com/sites/defaults/files${forecast.day.icon.fixIcon()}-s.png")
                                .build(),
                            contentDescription = null
                        )
                    }
                }
            }
        }
        AnimatedVisibility(visible = dailyForecasts is BaseModel.Loading) {
            Loading()
        }
    }
}

fun Int.fixIcon(): String {
    return if (this > 9) {
        toString()
    } else {
        "0${this}"
    }
}

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}