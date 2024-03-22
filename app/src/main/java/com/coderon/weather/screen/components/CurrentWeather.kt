package com.coderon.weather.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.coderon.weather.screen.fixIcon

@Composable
fun CurrentWeather(
    city: String = "",
    temperature: Int = 0,
    chanceOfRain: Int = 0,
    weatherIcon: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = city,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Chances of Rain : $chanceOfRain",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${temperature}°",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
        }
        AsyncImage(
            modifier = Modifier
                .aspectRatio(1f)
                .weight(.5f),
            model = "https://developer.accuweather.com/sites/default/files/" +
                    "${weatherIcon.fixIcon()}-s.png",
            contentDescription = "weather icon",
            contentScale = ContentScale.Fit
        )
    }
}