package com.coderon.weather

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.coderon.weather.screen.HomeScreen
import com.coderon.weather.screen.SearchScreen
import com.coderon.weather.screen.WeatherScreen

@Composable
fun App(
    navController: NavHostController,
    startDestination: String = "home",
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("search") {
            SearchScreen(navController = navController)
        }
    }
}