package com.coderon.weather

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.coderon.weather.screen.HomeScreen
import com.coderon.weather.screen.SearchScreen
import com.coderon.weather.screen.WeatherScreen

@Composable
fun Navigation(
    startDestination: String,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("home") {
            HomeScreen(navController = navController)

        }
        composable("weather/{location_key}/{name}", listOf(
            navArgument("location_key") {
                type = NavType.StringType
            }, navArgument("name") {
                type = NavType.StringType
            }
        )) {
            WeatherScreen(
                navController = navController,
                locationKey = it.arguments?.getString("location_key") ?: "",
                locationName = it.arguments?.getString("name") ?: ""
            )
        }
        composable("search") {
            SearchScreen(navController = navController)
        }
    }
}