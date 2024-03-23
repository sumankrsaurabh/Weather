package com.coderon.weather.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coderon.weather.database.WeatherDataBase
import com.coderon.weather.database.converters.fromLocal
import com.coderon.weather.location.DefaultLocationClient
import com.coderon.weather.model.BaseModel
import com.coderon.weather.model.DailyForecasts
import com.coderon.weather.model.HourlyForecast
import com.coderon.weather.model.Location
import com.coderon.weather.repositires.WeatherRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeatherViewModel : ViewModel(), KoinComponent {
    private val repo: WeatherRepo by inject()

    private val db: WeatherDataBase by inject()

    private val _city: MutableStateFlow<String?> = MutableStateFlow(null)
    val city = _city.asStateFlow()

    private val _hourlyForecast: MutableStateFlow<BaseModel<List<HourlyForecast>>> =
        MutableStateFlow(BaseModel.Loading)
    val hourlyForecast = _hourlyForecast.asStateFlow()

    private val _dailyForecast: MutableStateFlow<BaseModel<DailyForecasts>> =
        MutableStateFlow(BaseModel.Loading)
    val dailyForecast = _dailyForecast.asStateFlow()

    fun setCity(city:String){
        _city.update { city }
    }
    fun getHourlyForecast(locationKey: String) {
        viewModelScope.launch {
            repo.getHourlyForecasts(locationKey).also { data ->
                _hourlyForecast.update { data }
            }
        }
    }


    fun getDailyForecast(locationKey: String) {
        viewModelScope.launch {
            repo.getDailyForecasts(locationKey).also { data ->
                _dailyForecast.update { data }
            }
        }
    }

    private val _location: MutableStateFlow<BaseModel<List<Location>>?> = MutableStateFlow(null)
    val location = _location.asStateFlow()


    fun searchLocation(query: String) {
        viewModelScope.launch {
            _location.update { BaseModel.Loading }
            repo.searchLocation(query).also { data ->
                _location.update { data }
            }
        }
    }

    fun getUserLocation(refresh: Boolean = true) {
        val locationClient: DefaultLocationClient by inject()

        viewModelScope.launch {
            val city = db.locationDao().getCity()
            _location.update { BaseModel.Loading }

            if (refresh || city.isEmpty()) {
                locationClient.getLocationUpdates(10000L).collect { location ->
                    Log.d(
                        "HomeViewModel",
                        "Received user location: ${location.latitude},${location.longitude}"
                    )
                    searchLatLong("${location.latitude},${location.longitude}")
                }
            } else {
                convertLocalToUi(city)
            }
        }
    }

    private fun searchLatLong(query: String) {
        viewModelScope.launch {
            _location.update { BaseModel.Loading }
            repo.searchLocation(query).also { data ->
                _location.update { data }
            }
        }
    }

    private fun convertLocalToUi(location: List<com.coderon.weather.database.entity.Location>): BaseModel<List<Location>> {
        return BaseModel.Success(location.map {
            it.fromLocal()
        })
    }

}