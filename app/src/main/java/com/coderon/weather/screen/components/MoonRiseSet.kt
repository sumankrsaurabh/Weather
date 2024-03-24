package com.coderon.weather.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.coderon.weather.R
import com.coderon.weather.screen.utils.parseTime

@Composable
fun MoonriseMoonset(
    moonRise: Long,
    moonSet: Long,
    moonDescription: String,
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.25f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = CenterHorizontally
                ) {
                    Text(
                        text = "Moonrise",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = parseTime(moonRise),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = CenterHorizontally
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = R.drawable.moon_img),
                        contentDescription = "moon",
                        modifier = Modifier
                            .width(120.dp)
                            .aspectRatio(1f)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = moonDescription,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = CenterHorizontally
                ) {
                    Text(
                        text = "Moonrise",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = parseTime(moonSet),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}