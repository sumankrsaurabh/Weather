package com.coderon.weather.screen

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.coderon.weather.model.BaseModel
import com.coderon.weather.screen.components.Loading
import com.coderon.weather.ui.theme.containerColorWidget
import java.util.Locale.ENGLISH

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

    Box(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        AnimatedVisibility(
            visible = hourlyForecasts is BaseModel.Success
                    && dailyForecasts is BaseModel.Success,
        ) {
            val hourly = hourlyForecasts as BaseModel.Success
            val daily = dailyForecasts as BaseModel.Success
            val hourlyData = hourly.data
            val dailyData = daily.data.dailyForecasts


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(64.dp))
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
                        text = "Min ${dailyData.first().temperature.minimum.value.toInt()}" +
                                "°${dailyData.first().temperature.minimum.unit}"
                                + " / Max ${dailyData.first().temperature.maximum.value.toInt()}" +
                                "°${dailyData.first().temperature.maximum.unit}",
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
                    Spacer(modifier = Modifier.height(64.dp))
                }
                Column(
                    modifier = Modifier.padding(horizontal = 32.dp)
                ) {
                    Text(
                        text = "Hourly Forecast",
                        fontSize = 24.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(hourlyData) { hourlyForecast ->
                            Column(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(32.dp))
                                    .width(64.dp)
                                    .height(144.dp)
                                    .background(containerColorWidget),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = parseDate(hourlyForecast.epochDateTime),
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                                AsyncImage(
                                    modifier = Modifier.size(48.dp),
                                    model = "https://developer.accuweather.com/sites/default/files/" +
                                            "${hourlyForecast.weatherIcon.fixIcon()}-s.png",
                                    contentDescription = "weather icon"
                                )
                                Text(
                                    text = "${hourlyForecast.temperature.value.toInt()}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    color = Color.White
                                )
                                Text(
                                    text = hourlyForecast.iconPhrase,
                                    fontSize = 8.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Column(
                    modifier = Modifier.padding(horizontal = 32.dp)
                ) {
                    Text(
                        text = "Daily Forecast",
                        fontSize = 24.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(dailyData) { dailyForecast ->
                            Column(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(32.dp))
                                    .width(64.dp)
                                    .height(144.dp)
                                    .background(containerColorWidget),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = parseDay(dailyForecast.epochDate),
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                                AsyncImage(
                                    modifier = Modifier.size(48.dp),
                                    model = "https://developer.accuweather.com/sites/default/files/" +
                                            "${dailyForecast.day.icon.fixIcon()}-s.png",
                                    contentDescription = "weather icon"
                                )
                                Text(
                                    text = "${dailyForecast.temperature.minimum.value.toInt()}/" +
                                            "${dailyForecast.temperature.maximum.value.toInt()}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    color = Color.White
                                )
                                Text(
                                    text = dailyForecast.day.rainProbability.toString(),
                                    fontSize = 8.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .padding(horizontal = 32.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(containerColorWidget)
                ) {
                    val sunSetRatio =
                        dailyData.first().sun.epochRise / dailyData.first().sun.epochSet

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = sunTime(dailyData.first().sun.epochRise),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = Color.White
                        )
                        Text(
                            text = sunTime(dailyData.first().sun.epochSet),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = Color.White
                        )
                    }
                    Text(
                        text = "Total duration about ${dailyData.first().hoursOfSun}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
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

fun Int.fixIcon(): String {
    return if (this < 10) {
        "0${this}"
    } else {
        toString()
    }
}

fun parseDate(date: Long): String {
    val format = SimpleDateFormat("hha", ENGLISH)
    format.timeZone = TimeZone.getDefault()
    return format.format(date * 1000).toString()
}

fun parseDay(date: Long): String {
    val format = SimpleDateFormat("eee", ENGLISH)
    format.timeZone = TimeZone.getDefault()
    return format.format(date * 1000).toString()
}

fun sunTime(date: Long): String {
    val format = SimpleDateFormat("hh:mm: a", ENGLISH)
    format.timeZone = TimeZone.getDefault()
    return format.format(date * 1000).toString()
}
