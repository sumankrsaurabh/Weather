package com.coderon.weather.database.converters

import androidx.room.TypeConverter
import com.coderon.weather.model.utility.Direction
import com.coderon.weather.model.utility.RelativeHumidity
import com.coderon.weather.model.utility.Speed
import com.coderon.weather.model.utility.Wind
import com.coderon.weather.model.utility.WindGust
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class TypeConverterWind {
    private val gson = Gson()

    @TypeConverter
    fun fromSpeed(speed: Speed): String {
        return gson.toJson(speed)
    }

    @TypeConverter
    fun toSpeed(json: String): Speed {
        return gson.fromJson(json, Speed::class.java)
    }
}

class DirectionTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDirection(direction: Direction): String {
        return gson.toJson(direction)
    }

    @TypeConverter
    fun toDirection(json: String): Direction {
        return gson.fromJson(json, Direction::class.java)
    }
}
class WindTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDirection(wind: Wind): String {
        return gson.toJson(wind)
    }

    @TypeConverter
    fun toDirection(json: String): Wind {
        val type = object : TypeToken<WindGust>() {}.type
        return gson.fromJson(json,type)
    }
}



class WindGustTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromWindGust(windGust: WindGust): String {
        return gson.toJson(windGust)
    }

    @TypeConverter
    fun toWindGust(json: String): WindGust {
        val type = object : TypeToken<WindGust>() {}.type
        return gson.fromJson(json, type)
    }
}
class RelativeHumidityTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromWindGust(relativeHumidity: RelativeHumidity): String {
        return gson.toJson(relativeHumidity)
    }

    @TypeConverter
    fun toWindGust(json: String): RelativeHumidity {
        val type = object : TypeToken<RelativeHumidity>() {}.type
        return gson.fromJson(json, type)
    }
}

