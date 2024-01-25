package com.coderon.weather.screen

import android.icu.text.SimpleDateFormat
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.coderon.weather.R
import com.coderon.weather.model.BaseModel
import java.util.Date
import java.util.Locale

@Composable
fun WeatherScreen(
    navController: NavController,
    locationKey: String,
    locationName: String,
    state: String,
    country: String,
    viewModel: WeatherViewModel = viewModel(),
) {
    val dailyForecasts by viewModel.dailyForecast.collectAsState()
    val hourlyForecasts by viewModel.hourlyForecast.collectAsState()

    viewModel.getHourlyForecast(locationKey)
    viewModel.getDailyForecast(locationKey)

    Box {

        Image(
            modifier = Modifier.fillMaxSize()
                .blur(4.dp),
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    //TODO: implement navigation drawer with setting and other option
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu),
                        modifier = Modifier.size(30.dp),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        modifier = Modifier.size(30.dp),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = locationName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 56.sp,
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Text(
                    text = "$state, $country",
                    fontWeight = FontWeight.Normal,
                    fontSize = 32.sp,
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                AnimatedVisibility(visible = hourlyForecasts is BaseModel.Success) {
                    val data = hourlyForecasts as BaseModel.Success
                    val temp = data.data.first().temperature
                    val weatherIcon = data.data.first().weatherIcon

                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 70.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(150.dp),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(weatherIcon(weatherIcon))
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "${temp.value.toInt()}°${temp.unit}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 72.sp,
                            )
                        }

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Wind Speed")
                                Text(text = "${data.data.first().wind.speed.value} ${data.data.first().wind.speed.unit}")
                            }
                            Divider()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "UV Index")
                                Text(text = "${data.data.first().uvIndexText} ( ${data.data.first().uvIndex})")
                            }
                            Divider()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Humidity")
                                Text(text = "${data.data.first().relativeHumidity}")
                            }
                            Divider()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Real feel")
                                Text(text = "${data.data.first().realFeelTemperature.value.toInt()}")
                            }
                            Divider()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Chance of rain")
                                Text(text = "${data.data.first().precipitationProbability} %")
                            }
                        }
                    }
                }
                AnimatedVisibility(visible = hourlyForecasts is BaseModel.Loading) {
                    Loading()
                }
            }



            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Hourly Forecast",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(10.dp))
            AnimatedVisibility(visible = hourlyForecasts is BaseModel.Success) {
                val data = hourlyForecasts as BaseModel.Success
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        items(data.data) { forecast ->
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = SimpleDateFormat("h a", Locale("en")).format(
                                            Date(
                                                forecast.epochDateTime * 1000
                                            )
                                        ),
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    AsyncImage(
                                        modifier = Modifier.size(80.dp),
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(weatherIcon(forecast.weatherIcon))
                                            .build(),
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = forecast.temperature.value.toInt()
                                            .toString() + "°" + forecast.temperature.unit,
                                    )
                                }
                            }
                        }
                    }
                }
            }
            AnimatedVisibility(visible = hourlyForecasts is BaseModel.Loading) {
                Loading()
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Daily Forecast",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedVisibility(visible = dailyForecasts is BaseModel.Success) {

                val data = dailyForecasts as BaseModel.Success
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(data.data.dailyForecasts) { forecast ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(vertical = 16.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = SimpleDateFormat("eeee", Locale("en")).format(
                                            Date(
                                                forecast.epochDate * 1000
                                            )
                                        ),
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    AsyncImage(
                                        modifier = Modifier.size(70.dp),
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(weatherIcon(forecast.day.icon))
                                            .build(),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "${forecast.temperature.minimum.value.toInt()}° / ${forecast.temperature.maximum.value.toInt()}°"
                                    )
                                }
                            }
                        }
                    }
                }
            }
            AnimatedVisibility(visible = dailyForecasts is BaseModel.Loading) {
                Loading()
            }
            AnimatedVisibility(visible = dailyForecasts is BaseModel.Error) {
                val message = dailyForecasts as BaseModel.Error
                Text(text = message.toString())
            }
        }
    }
}

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

fun weatherIcon(int: Int): Int {
    return when (int) {
        1 -> R.drawable.clear_sky_day
        2 -> R.drawable.clear_sky_day
        3 -> R.drawable.mist_day
        4 -> R.drawable.mist_day
        5 -> R.drawable.few_clouds_day
        6 -> R.drawable.broken_clouds_day
        7 -> R.drawable.scattered_clouds
        8 -> R.drawable.scattered_clouds
        12 -> R.drawable.rain_day
        13 -> R.drawable.rain_day
        14 -> R.drawable.rain_day

        else -> R.drawable.error
    }
}
