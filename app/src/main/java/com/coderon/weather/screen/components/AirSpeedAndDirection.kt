package com.coderon.weather.screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AirSpeedAndDirection(direction: Float, speed: String) {

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.25f)
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Air, contentDescription = "air",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Wind",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Canvas(
                    modifier = Modifier
                        .height(24.dp)
                        .aspectRatio(1f)
                ) {
                    val radius = size.width / 2F

                    rotate(direction, center) {
                        drawLine(
                            color = Color.White,
                            start = center - Offset(0f, radius * 0.9f),
                            end = center + Offset(0f, radius * 0.9f),
                            strokeWidth = 4.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                        drawLine(
                            color = Color.White,
                            start = center + Offset(0f, radius * 0.9f),
                            end = center + Offset(16f, radius * 0.5f),
                            strokeWidth = 4.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                        drawLine(
                            color = Color.White,
                            start = center + Offset(0f, radius * 0.9f),
                            end = center + Offset(-16f, radius * 0.5f),
                            strokeWidth = 4.dp.toPx(),
                            cap = StrokeCap.Round
                        )

                    }
                }
                Text(
                    text = speed,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
fun Test() {
    AirSpeedAndDirection(direction = 135f, "  ")
}