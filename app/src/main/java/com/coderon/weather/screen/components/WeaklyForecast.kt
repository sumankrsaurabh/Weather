package com.coderon.weather.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.coderon.weather.model.DailyForecast
import com.coderon.weather.screen.utils.parseDay

@Composable
fun WeeklyForecast(weeklyForecast: List<DailyForecast>) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(.25f)
        ),
        modifier = Modifier.height(356.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp)
        ) {
            item {
                Text(
                    text = "5-DAY FORECAST",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            itemsIndexed(weeklyForecast) { index, forecast ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = parseDay(forecast.epochDate),
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            modifier = Modifier.weight(4f),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    weatherIcon(forecast.day.icon)
                                ),
                                contentDescription = "weather icon",
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = forecast.day.iconPhrase,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                softWrap = false
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${forecast.temperature.maximum.value.toInt()}/${forecast.temperature.minimum.value.toInt()}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                    if (index < weeklyForecast.lastIndex) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Divider(
                            color = Color.White.copy(alpha = 0.5f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.5.dp)
                                .clip(RoundedCornerShape(50.dp))
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }
        }
    }
}
