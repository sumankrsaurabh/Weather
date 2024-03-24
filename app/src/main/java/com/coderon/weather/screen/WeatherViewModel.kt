package com.coderon.weather.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coderon.weather.database.WeatherDataBase
import com.coderon.weather.database.converters.fromLocal
import com.coderon.weather.database.converters.toLocal
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


    private val _location: MutableStateFlow<BaseModel<List<Location>>> =
        MutableStateFlow(BaseModel.Loading)
    val location = _location.asStateFlow()

    private val _hourlyForecast: MutableStateFlow<BaseModel<List<HourlyForecast>>> =
        MutableStateFlow(BaseModel.Loading)
    val hourlyForecast = _hourlyForecast.asStateFlow()

    private val _dailyForecast: MutableStateFlow<BaseModel<DailyForecasts>> =
        MutableStateFlow(BaseModel.Loading)
    val dailyForecast = _dailyForecast.asStateFlow()

    fun setCity(city: String) {
        _city.update { city }
    }

    fun getHourlyForecast(locationKey: String) {
        viewModelScope.launch {
            val hourlyForecast = db.hourlyForecastDao().getHourlyForecast()
            if (hourlyForecast.isEmpty()) {
                _hourlyForecast.update { BaseModel.Loading }
            } else {
                _hourlyForecast.update {
                    BaseModel.Success(hourlyForecast.map {
                        it.fromLocal()
                    })
                }
            }
            /*repo.getHourlyForecasts(locationKey).also { data ->
                if (data is BaseModel.Success) {
                    val forecast = data.data
                    val savableForecast = forecast.map { it.toLocal(locationKey) }
                    db.hourlyForecastDao().deleteHourlyForecast(locationKey)
                    db.hourlyForecastDao().addHourlyForecast(savableForecast)
                    _hourlyForecast.update {
                        BaseModel.Success(forecast)
                    }
                }
            }*/
        }
    }


    fun getDailyForecast(locationKey: String) {
        viewModelScope.launch {
            val dailyForecast = db.dailyForecastDao().getDailyForecast()
            if (dailyForecast.isEmpty()) {
                _dailyForecast.update { BaseModel.Loading }
            } else {
                _dailyForecast.update { BaseModel.Success(DailyForecasts(dailyForecast.map { it.fromLocal() })) }
            }
            /*repo.getDailyForecasts(locationKey).also { data ->
                if (data is BaseModel.Success) {
                    val forecast = data.data
                    val savableForecast = forecast.dailyForecasts.map { it.toLocal(locationKey) }
                    db.dailyForecastDao().deleteDailyForecast(locationKey)
                    db.dailyForecastDao().addDailyForecast(savableForecast)
                }
                _dailyForecast.update { data }
            }*/
        }
    }


    fun searchLocation(query: String) {
        viewModelScope.launch {
            _location.update { BaseModel.Loading }
            repo.searchLocation(query).also { data ->
                _location.update { data }
            }
        }
    }

    fun getUserLocation(refresh: Boolean = false) {
        val locationClient: DefaultLocationClient by inject()

        viewModelScope.launch {
            val city = db.locationDao().getCity()

            if (city.isEmpty() || refresh) {
                locationClient.getLocationUpdates(10000L).collect { location ->
                    searchLatLong("${location.latitude},${location.longitude}")
                }
            } else {
                _location.update { BaseModel.Success(city.map { it.fromLocal() }) }
            }
        }
    }

    private fun searchLatLong(query: String) {
        viewModelScope.launch {
            _location.update { BaseModel.Loading }
            repo.searchLocation(query).also { data ->
                if (data is BaseModel.Success) {
                    val location = data.data
                    location.forEach { db.locationDao().addCity(it.toLocal()) }
                }
                _location.update { data }
            }
        }
    }

}