package com.coderon.weather

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.coderon.weather.screen.HomeScreen
import com.coderon.weather.screen.WeatherScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("weather/{location_key}/{name}/{country}/{state}", listOf(
            navArgument("location_key") {
                type = NavType.StringType
            }, navArgument("name") {
                type = NavType.StringType
            }, navArgument("country") {
                type = NavType.StringType
            }, navArgument("state") {
                type = NavType.StringType
            }
        )) {
            WeatherScreen(
                navController = navController,
                locationKey = it.arguments?.getString("location_key") ?: "",
                locationName = it.arguments?.getString("name") ?: "",
                country = it.arguments?.getString("country") ?: "",
                state = it.arguments?.getString("state") ?: "",
            )
        }
    }
}