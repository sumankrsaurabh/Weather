package com.coderon.weather.screen.components

import android.util.Log
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coderon.weather.screen.utils.parseTime
import kotlin.math.max
import kotlin.math.min


@Composable
fun SunriseSunset(
    sunrise: Long,
    sunset: Long,
    currentTime: Long
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.25f)
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 64.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Canvas(
                modifier = Modifier
                    .width(200.dp)
                    .height(100.dp)
                    .aspectRatio(2f)
            ) {
                val d1 = min(size.width, size.height)
                val d2 = max(size.width, size.height)

                // Calculate the percentage of the day passed
                val totalDuration = sunset - sunrise
                val currentTimeRelativeToSunrise = (currentTime /1000) - sunrise
                val percentageOfDay = currentTimeRelativeToSunrise.toFloat() / totalDuration.toFloat()
                val angle = percentageOfDay * 180 // Convert percentage to angle

                val radius = (d2) / 2
                val angleInRadians = Math.toRadians(angle.toDouble())

                // Calculate the position of the circle on the arc
                val circleX = (size.width / 2 - radius * kotlin.math.cos(angleInRadians)).toFloat()
                val circleY = (size.height - radius * kotlin.math.sin(angleInRadians)).toFloat()

                // Draw the line representing the horizon
                drawLine(
                    color = Color.White,
                    start = Offset(-24.dp.toPx(), d1),
                    end = Offset(d2 + 24.dp.toPx(), d1),
                    strokeWidth = 1.dp.toPx()
                )

                // Draw the arc representing the day's progress
                drawArc(
                    color = Color.White,
                    startAngle = -180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    size = Size(size.width, size.height * 2),
                    style = Stroke(1.dp.toPx(), cap = StrokeCap.Square)
                )

                // Draw the circle indicating the current time position
                drawCircle(
                    color = Color(0xFFFFC400),
                    radius = 8.dp.toPx(),
                    center = Offset(circleX, circleY)
                )
                Log.d("Position" ,"(${circleX},${circleY})")
            }

            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.width(240.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Sunrise",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "Sunset",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = parseTime(sunrise),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = parseTime(sunset),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(backgroundColor = 0xFF101010, showBackground = true)
@Composable
private fun SunSetPreview() {
    // Pass the current time for preview, replace with actual time in your implementation
    SunriseSunset(
        sunrise = 1711239300,
        sunset = 1711283340,
        currentTime = 1711242550403
    )
}
