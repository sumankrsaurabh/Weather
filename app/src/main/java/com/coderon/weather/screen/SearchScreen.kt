package com.coderon.weather.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.coderon.weather.database.DataBase
import com.coderon.weather.database.entity.Location
import com.coderon.weather.model.BaseModel
import com.coderon.weather.ui.theme.containerColor
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun SearchScreen(
    navController: NavController,
    db: DataBase = koinInject(),
    viewModel: HomeViewModel = viewModel(),
) {
    val locations by viewModel.location.collectAsState()
    val scope = rememberCoroutineScope()
    val (city, setCity) = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(32.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp)),
            contentAlignment = Alignment.Center
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                value = city,
                onValueChange = { setCity(it) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = containerColor.copy(0.5f),
                    unfocusedContainerColor = containerColor.copy(0.5f),
                    disabledContainerColor = containerColor.copy(0.5f),
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    disabledIndicatorColor = Transparent,
                    errorIndicatorColor = Transparent,
                    focusedTextColor = White,
                    disabledSupportingTextColor = White,
                    disabledTextColor = White,
                    errorTextColor = Transparent,
                    errorSupportingTextColor = Transparent,
                ),
                placeholder = {
                    Text(
                        text = "Search your desire location",
                        fontSize = 16.sp,
                        color = White
                    )
                },
                shape = RoundedCornerShape(32.dp),
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null,
                        tint = White
                    )
                },
                trailingIcon = {
                    if (city.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.clickable {
                                setCity("")
                            }
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        // Handle search action here
                        if (city.isNotEmpty()) {
                            viewModel.searchLocation(city)
                        }
                    }
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(
            visible = locations is BaseModel.Success,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            Column {
                when (val data = locations) {
                    is BaseModel.Success -> {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(data.data) { location ->
                                Row(
                                    modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .height(64.dp)
                                        .clip(RoundedCornerShape(32.dp))
                                        .background(containerColor)
                                        .clickable {
                                            scope.launch {
                                                db
                                                    .locationDao()
                                                    .addCity(
                                                        Location(
                                                            version = location.version,
                                                            key = location.key,
                                                            type = location.type,
                                                            rank = location.rank,
                                                            localizedName = location.localizedName,
                                                            englishName = location.englishName,
                                                            latitude = location.geoPosition.latitude,
                                                            longitude = location.geoPosition.longitude,
                                                        )

                                                    )
                                            }
                                            navController.navigate("weather/${location.key}/${location.englishName}")
                                        }
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = location.englishName,
                                            fontSize = 24.sp,
                                            color = White
                                        )
                                    }
                                    FilledTonalIconButton(
                                        onClick = {
                                            scope.launch {
                                                db.locationDao().addCity(
                                                    Location(
                                                        version = location.version,
                                                        key = location.key,
                                                        type = location.type,
                                                        rank = location.rank,
                                                        localizedName = location.localizedName,
                                                        englishName = location.englishName,
                                                        latitude = location.geoPosition.latitude,
                                                        longitude = location.geoPosition.longitude,
                                                    )
                                                )
                                            }
                                        },
                                        modifier = Modifier.size(40.dp),
                                        colors = IconButtonDefaults.filledTonalIconButtonColors(
                                            containerColor = White.copy(0.5f)
                                        )
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.Add,
                                            contentDescription = "add city",
                                            tint = White
                                        )
                                    }
                                }
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
        AnimatedVisibility(
            visible = locations is BaseModel.Loading,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = White)
            }
        }
    }

}