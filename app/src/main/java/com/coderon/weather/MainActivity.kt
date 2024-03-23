package com.coderon.weather

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.coderon.weather.location.RequestLocationPermission
import com.coderon.weather.ui.theme.WeatherTheme
import com.coderon.weather.ui.theme.backgroundColor


class MainActivity : ComponentActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            RequestLocationPermission()
            val navController = rememberNavController()
            WeatherTheme {
                Surface(
                    color = if (isSystemInDarkTheme()) Color.Black.copy(.9f)
                    else backgroundColor.copy(.9f),
                ) {
                    App(navController)
                }
            }
        }
    }
}
