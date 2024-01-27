package com.coderon.weather.screen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coderon.weather.location.DefaultLocationClient
import com.coderon.weather.model.BaseModel
import com.coderon.weather.model.Location
import com.coderon.weather.model.utility.GeoPosition
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

    fun setLocation(location: List<com.coderon.weather.database.entity.Location>) {
        _location.update { BaseModel.Loading }
        convertLocalToUi(location)
    }

    fun getUserLocation(context: Context) {
        val locationClient = DefaultLocationClient(
            context,
            LocationServices.getFusedLocationProviderClient(context)
        )
        viewModelScope.launch {
            locationClient.getLocationUpdates(10000L).collect { location ->
                _userLocation.update { location }
                Log.d(
                    "HomeViewModel",
                    "Received user location: ${location.latitude},${location.longitude}"
                )
                // Optionally, you can stop listening for updates here if needed
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

    private fun convertLocalToUi(location: List<com.coderon.weather.database.entity.Location>): BaseModel<List<Location>> {
        val convertedList = location.map {
            Location(
                version = it.version,
                key = it.key,
                type = it.type,
                rank = it.rank,
                localizedName = it.localizedName,
                englishName = it.englishName,
                geoPosition = GeoPosition(
                    it.latitude, it.longitude
                )
            )
        }

        return BaseModel.Success(convertedList)
    }

}