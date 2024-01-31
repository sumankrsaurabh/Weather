package com.coderon.weather

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.navigation.compose.rememberNavController
import com.coderon.weather.location.RequestLocationPermission
import com.coderon.weather.ui.theme.WeatherTheme
import com.coderon.weather.ui.theme.bottomBackground
import com.coderon.weather.ui.theme.topBackground


class MainActivity : ComponentActivity() {


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            RequestLocationPermission()
            val navController = rememberNavController()
            WeatherTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        topBackground.copy(0.32f),
                                        bottomBackground
                                    )
                                )
                            ),
                    )
                    Navigation(navController, startDestination = "home")
                }
            }
        }
    }
}
