package com.coderon.weather.screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coderon.weather.ui.theme.containerColorWidget
import kotlin.math.min

@Composable
fun AirSpeedAndDirection(direction: Float,modifier: Modifier = Modifier) {
    val textMeasurer = rememberTextMeasurer()
    val textToDraw = "O"
    val style = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        background = Color.Transparent
    )
    val textLayoutResult = remember(textToDraw, style) {
        textMeasurer.measure(textToDraw, style)
    }
    Column(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(32.dp))
            .background(containerColorWidget)
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Rounded.Air, contentDescription = "air",
                tint = Color.White
            )
            Text(
                text = "WIND",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .aspectRatio(1f)
        ) {

            val diameter = min(size.width, size.height) * 0.9f
            val radius = diameter / 2

            repeat(12) {
                val start = center - Offset(0f, radius)
                val end = start + Offset(0f, radius / 10f)
                rotate(it / 12f * 360) {
                    drawLine(
                        color = Color.White,
                        start = start,
                        end = end,
                        strokeWidth = 1.5.dp.toPx(),
                        cap = StrokeCap.Square
                    )

                }
            }


            repeat(4) {
                rotate(it * 90f) {
                    drawText(
                        textMeasurer = textMeasurer,
                        text = when (it * 90) {
                            0 -> "N"
                            90 -> "E"
                            180 -> "S"
                            270 -> "W"
                            else -> ""
                        },
                        style = style,
                        topLeft = Offset(
                            x = center.x - textLayoutResult.size.width / 2,
                            y = center.y - radius * 0.9f - (textLayoutResult.size.height / 2) + 8.dp.toPx()
                        )
                    )
                }
            }
            repeat(180) {
                val start = center - Offset(0f, radius)
                val end = start + Offset(0f, radius / 10f)
                rotate(it / 180f * 360) {
                    drawLine(
                        color = Color.White.copy(0.5f),
                        start = start,
                        end = end,
                        strokeWidth = 1.dp.toPx(),
                        cap = StrokeCap.Square
                    )
                }
            }

            rotate(direction, center) {
                drawLine(
                    color = Color.White,
                    start = center - Offset(0f, radius * 0.9f),
                    end = center + Offset(0f, radius * 0.9f),
                    strokeWidth = 4.dp.toPx(),
                    cap = StrokeCap.Butt
                )
                drawCircle(
                    color = Color.White,
                    radius = 4.dp.toPx(),
                    center = center - Offset(0f, radius * 0.9f + 3.dp.toPx()),
                    style = Stroke(3.dp.toPx())
                )
                val path = Path()
                path.moveTo(center.x, center.y + radius + 5f)
                path.lineTo(center.x + 4.dp.toPx(), center.y + radius * 0.9f - 2.dp.toPx())
                path.lineTo(center.x, center.y + radius * 0.9f)
                path.lineTo(center.x - 4.dp.toPx(), center.y + radius * 0.9f - 2.dp.toPx())
                path.lineTo(center.x, center.y + radius + 5f)

                drawPath(
                    path = path,
                    brush = SolidColor(Color.White),
                    style = Stroke(3.dp.toPx(), cap = StrokeCap.Round)
                )

            }
        }
    }
}

@Preview
@Composable
fun Test() {
    AirSpeedAndDirection(direction = 180f)
}