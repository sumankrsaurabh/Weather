package com.coderon.weather.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coderon.weather.location.DefaultLocationClient
import com.coderon.weather.model.BaseModel
import com.coderon.weather.model.Location
import com.coderon.weather.repositires.WeatherRepo
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {

    private val repo: WeatherRepo by inject()

    private val _location: MutableStateFlow<BaseModel<List<Location>>?> = MutableStateFlow(null)
    val location = _location.asStateFlow()

    private val _userLocation: MutableStateFlow<android.location.Location?> = MutableStateFlow(null)
    val userLocation = _userLocation.asStateFlow()
    fun searchLocation(query: String) {
        viewModelScope.launch {
            _location.update { BaseModel.Loading }
            repo.searchLocation(query).also { data ->
                _location.update { data }
            }
        }
    }

    fun getUserLocation(context: Context){
        val locationClient = DefaultLocationClient(
            context,
            LocationServices.getFusedLocationProviderClient(context)
        )
        viewModelScope.launch {
            locationClient.getLocationUpdates(10000L).collect { location ->
                _userLocation.update { location }
            }
        }
    }
    fun searchLatLong(query: String) {
        viewModelScope.launch {
            _location.update { BaseModel.Loading }
            repo.searchLocation(query).also { data ->
                _location.update { data }
            }
        }
    }
}