package com.coderon.weather.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.coderon.weather.database.DataBase
import com.coderon.weather.database.entity.Location
import com.coderon.weather.model.BaseModel
import com.coderon.weather.screen.components.Loading
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    navController: NavController,
    db: DataBase = koinInject(),
    viewModel: HomeViewModel = viewModel(),
) {
    val locations by viewModel.location.collectAsState()
    val userLocation by viewModel.userLocation.collectAsState()
    val context = LocalContext.current
    val searchLatLongCalled by remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        // Fetch local locations from the database
        val localLocation = db.locationDao().getCity()

        // Check whether local locations are available
        if (localLocation.isNotEmpty()) {
            // Set local locations in the ViewModel
            viewModel.setLocation(localLocation)
            // Navigate to the first local location
            val firstLocalLocation = localLocation.first()
            navController.navigate(
                "weather/${firstLocalLocation.key}/${firstLocalLocation.englishName}"
            )
        } else {
            // Local locations are not available, check user location
            viewModel.getUserLocation(context)
            if (!searchLatLongCalled) {
                // Ensure searchLatLong is called only once
                when (userLocation) {
                    null -> {}
                    else -> {
                        viewModel.searchLatLong("${userLocation?.latitude}," +
                                "${userLocation?.longitude}")
                        Log.d("Coordinate","${userLocation?.latitude}," +
                                "${userLocation?.longitude}")
                    }
                }
            }
        }
    }

    // Display loading UI if fetching data
    AnimatedVisibility(visible = locations is BaseModel.Loading) {
        Loading()
    }

    // Display content if data is available
    AnimatedVisibility(visible = locations is BaseModel.Success) {
        val data = locations as BaseModel.Success
        val loc = data.data.first()
        // Add the location to the local database
        LaunchedEffect(loc) {
            db.locationDao().addCity(
                Location(
                    version = loc.version,
                    key = loc.key,
                    type = loc.type,
                    rank = loc.rank,
                    localizedName = loc.localizedName,
                    englishName = loc.englishName,
                    latitude = loc.geoPosition.latitude,
                    longitude = loc.geoPosition.longitude,
                )
            )
        }
        // Navigate to the weather details screen
        navController.navigate("weather/${loc.key}/${loc.englishName}")
    }
}
