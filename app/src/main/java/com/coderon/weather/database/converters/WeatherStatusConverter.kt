package com.coderon.weather.database.converters

import androidx.room.TypeConverter
import com.coderon.weather.model.utility.WeatherStatus
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherStatusConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromWeatherStatus(weatherStatus: WeatherStatus): String {
        return gson.toJson(weatherStatus)
    }

    @TypeConverter
    fun toWeatherStatus(json: String): WeatherStatus {
        val type = object : TypeToken<WeatherStatus>() {}.type
        return gson.fromJson(json, type)
    }
}
