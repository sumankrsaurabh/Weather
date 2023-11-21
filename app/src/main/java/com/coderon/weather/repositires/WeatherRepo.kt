package com.coderon.weather.repositires

import com.coderon.weather.model.BaseModel
import com.coderon.weather.model.DailyForecasts
import com.coderon.weather.model.HourlyForecast
import com.coderon.weather.model.Location

interface WeatherRepo {
    suspend fun searchLocation(query:String):BaseModel<List<Location>>
    suspend fun getDailyForecasts(locationKey:String):BaseModel<DailyForecasts>
    suspend fun getHourlyForecasts(locationKey:String):BaseModel<List<HourlyForecast>>
}