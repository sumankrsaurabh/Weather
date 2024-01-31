package com.coderon.weather.database.converters

import androidx.room.TypeConverter
import com.coderon.weather.model.utility.Temperature
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TemperatureTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromTemperature(temperature: Temperature): String {
        return gson.toJson(temperature)
    }

    @TypeConverter
    fun toTemperature(json: String): Temperature {
        val type = object : TypeToken<Temperature>() {}.type
        return gson.fromJson(json, type)
    }
}
