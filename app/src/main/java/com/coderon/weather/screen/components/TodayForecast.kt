package com.coderon.weather.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.coderon.weather.model.HourlyForecast
import com.coderon.weather.screen.utils.parseTime

@Composable
fun TodayForecast(
    todayForecast: List<HourlyForecast>,
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background.copy(.25f)
        ),
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Text(
                text = "TODAY FORECAST",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                itemsIndexed(todayForecast) { index, forecast ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = parseTime(forecast.epochDateTime),
                                fontSize = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Image(
                                modifier = Modifier.size(64.dp),
                                painter = rememberAsyncImagePainter(weatherIcon(forecast.weatherIcon)),
                                contentDescription = "weather icon"
                            )
                            Text(
                                text = "${forecast.temperature.value.toInt()}°",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = Color.White
                            )

                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        if (index < todayForecast.lastIndex) {
                            Divider(
                                color = Color.White.copy(.5f), modifier = Modifier
                                    .height(110.dp)
                                    .width(1.dp)
                                    .clip(RoundedCornerShape(50))
                            )
                        }
                    }
                }
            }
        }
    }
}