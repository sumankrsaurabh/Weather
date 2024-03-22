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
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.coderon.weather.R
import com.coderon.weather.database.WeatherDataBase
import com.coderon.weather.database.converters.toLocal
import com.coderon.weather.model.BaseModel
import com.coderon.weather.screen.components.AirSpeedAndDirection
import com.coderon.weather.screen.components.Loading
import com.coderon.weather.ui.theme.containerColorWidget
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import java.util.Locale.ENGLISH

@Composable
fun WeatherScreen(
    navController: NavController,
    locationKey: String,
    locationName: String,
    db: WeatherDataBase = koinInject(),
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
            val scope = rememberCoroutineScope()
            SideEffect {
                scope.launch {
                    db.hourlyForecastDao().addHourlyForecast(
                        hourlyData.map {
                            it.toLocal(key = locationKey)
                        }
                    )
//                    db.dailyForecastDao().addDailyForecast(
//                        dailyData.map {
//                            it.toLocal(locationKey)
//                        }
//                    )
                }
            }
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
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.firasans_regular))
                    )
                    Spacer(modifier = Modifier.height(64.dp))
                }
                Column(
                    modifier = Modifier.padding(horizontal = 32.dp)
                ) {
                    Text(
                        text = "Hourly Forecast",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.firasans_regular))
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
                                    color = Color.White,
                                    fontFamily = FontFamily(Font(R.font.firasans_regular))
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
                                    color = Color.White,
                                    fontFamily = FontFamily(Font(R.font.firasans_regular))
                                )
                                Text(
                                    text = hourlyForecast.iconPhrase,
                                    fontSize = 8.sp,
                                    color = Color.White,
                                    fontFamily = FontFamily(Font(R.font.firasans_regular))
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
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.firasans_regular))
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

                /*Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .padding(horizontal = 32.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(containerColorWidget)
                ) {
                    val progress =
                        (System.currentTimeMillis() / (dailyData.first().sun.epochSet - dailyData.first().sun.epochRise).toFloat())
                    Canvas(
                        modifier = Modifier
                            .padding(32.dp)
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        val cornerRadius = CornerRadius.Zero
                        // Draw background
                        drawRoundRect(
                            color = containerColorWidget,
                            size = size,
                            cornerRadius = cornerRadius
                        )

                        // Draw progress
                        drawRoundRect(
                            color = Color.White,
                            size = Size(size.width * progress, size.height),
                            cornerRadius = cornerRadius
                        )

                        // Draw stroke
                        drawRoundRect(
                            color = Color.Black, // Change the color of the stroke if needed
                            size = size,
                            cornerRadius = cornerRadius,
                            style = Fill
                        )
                    }

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
                }*/

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 32.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        AirSpeedAndDirection(
                            direction = hourlyData.first().wind.direction.degrees.toFloat(),
                            Modifier.weight(1f)
                        )
                        Column(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(32.dp))
                                .background(containerColorWidget)
                                .padding(16.dp)
                                .weight(1f)
                        ) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Icon(
                                    imageVector = Icons.Rounded.WaterDrop,
                                    contentDescription = "air",
                                    tint = Color.White
                                )
                                Text(
                                    text = "Rain",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            }
                            Text(
                                text = hourlyData.first().precipitationProbability.toString(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16 .dp))
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(32.dp))
                                .background(containerColorWidget)
                                .padding(16.dp)
                                .weight(1f)

                        ) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Icon(
                                    imageVector = Icons.Rounded.WbSunny, contentDescription = "air",
                                    tint = Color.White
                                )
                                Text(
                                    text = "UV index",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            }
                            Text(
                                text = hourlyData.first().uvIndexText,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        }
                        Column(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(32.dp))
                                .background(containerColorWidget)
                                .padding(16.dp)
                                .weight(1f)

                        ) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Icon(
                                    imageVector = Icons.Rounded.WbSunny, contentDescription = "air",
                                    tint = Color.White
                                )
                                Text(
                                    text = "UV index",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            }
                            Text(
                                text = hourlyData.first().uvIndexText,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        AnimatedVisibility(
            visible = hourlyForecasts is BaseModel.Loading
                    && dailyForecasts is BaseModel.Loading
        ) {
            Loading("Fetching data")
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
