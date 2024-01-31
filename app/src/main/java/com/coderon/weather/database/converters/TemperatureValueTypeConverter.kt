package com.coderon.weather.database.converters

import androidx.room.TypeConverter
import com.coderon.weather.model.utility.TemperatureValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TemperatureValueTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromTemperatureValue(temperatureValue: TemperatureValue): String {
        return gson.toJson(temperatureValue)
    }

    @TypeConverter
    fun toTemperatureValue(json: String): TemperatureValue {
        val type = object : TypeToken<TemperatureValue>() {}.type
        return gson.fromJson(json, type)
    }
}