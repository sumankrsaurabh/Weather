package com.coderon.weather.database.converters

import androidx.room.TypeConverter
import com.coderon.weather.model.utility.Moon
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverterMoon {
    private val gson = Gson()

    @TypeConverter
    fun fromTemperature(moon: Moon): String {
        return gson.toJson(moon)
    }

    @TypeConverter
    fun toTemperature(json: String): Moon {
        val type = object : TypeToken<Moon>() {}.type
        return gson.fromJson(json, type)
    }
}