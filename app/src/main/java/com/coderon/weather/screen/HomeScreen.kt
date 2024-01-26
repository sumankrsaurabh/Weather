package com.coderon.weather.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.coderon.weather.model.BaseModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel(),
) {
    viewModel.getUserLocation(LocalContext.current)
    val locations by viewModel.location.collectAsState()
    val userLocation by viewModel.userLocation.collectAsState()

    when (userLocation) {
        null -> {
        }

        else -> {
            viewModel.searchLatLong("${userLocation?.latitude},${userLocation?.longitude}")
            AnimatedVisibility(
                visible = locations is BaseModel.Success,
            ) {

                when (val data = locations) {
                    is BaseModel.Success -> {
                        val loc = data.data.first()
                        navController.navigate(
                            "weather/${loc.key}" +
                                    "/${loc.englishName}"
                        )
                    }

                    else -> {}
                }
            }
            AnimatedVisibility(visible = locations is BaseModel.Loading) {
                Loading()
            }
        }
    }
}
