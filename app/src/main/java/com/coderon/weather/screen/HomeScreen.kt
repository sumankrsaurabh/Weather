package com.coderon.weather.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
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
                                    "/${loc.englishName}" +
                                    "/${loc.country.englishName}" +
                                    "/${loc.administrativeArea.englishName}"
                        )
                    }

                    else -> {}
                }
            }
            AnimatedVisibility(visible = locations is BaseModel.Loading) {
                Loading()
            }
            AnimatedVisibility(visible = locations is BaseModel.Error) {
                AlertDialog(
                    onDismissRequest = { /*todo*/ },
                    properties = DialogProperties(false),
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(8.dp)
                        )
                        .background(Color.White)
                ) {
                    Column(
                        Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Unable to get your location " +
                                    "but you can search your city",
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Button(onClick = { navController.navigate("search") }) {
                            Text(text = "Goto search Page")
                        }
                    }
                }
            }
        }
    }
}
