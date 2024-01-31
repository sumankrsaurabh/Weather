package com.coderon.weather.database.converters

import androidx.room.TypeConverter
import com.coderon.weather.model.utility.Sun
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverterSun {
    private val gson = Gson()

    @TypeConverter
    fun fromTemperature(sun: Sun): String {
        return gson.toJson(sun)
    }

    @TypeConverter
    fun toTemperature(json: String): Sun {
        val type = object : TypeToken<Sun>() {}.type
        return gson.fromJson(json, type)
    }
}