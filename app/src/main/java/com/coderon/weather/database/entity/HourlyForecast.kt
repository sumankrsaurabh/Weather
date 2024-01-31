package com.coderon.weather.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coderon.weather.model.utility.RealFeelTemperature
import com.coderon.weather.model.utility.TemperatureValue
import com.coderon.weather.model.utility.Wind

@Entity("hourly_forecast")
data class HourlyForecast(
    @PrimaryKey(true)
    val id: Int = 0,
    val key: String,
    val dateTime: String,
    val epochDateTime: Long,
    val weatherIcon: Int,
    val iconPhrase: String,
    val hasPrecipitation: Boolean,
    val isDayNight: Boolean,
//    temperature
    @Embedded("temp_")
    val temperature: TemperatureValue,
    // realFeelTemperature
    @Embedded("realFeelTemp_")
    val realFeelTemperature: RealFeelTemperature,
//    Wind class
    @Embedded("wind_")
    val wind: Wind,
    val relativeHumidity: Int,
    val indoorRelativeHumidity: Int,
    val uvIndex: Int,
    val uvIndexText: String,
    val precipitationProbability: Int,
    val thunderstormProbability: Int,
    val rainProbability: Int,
    val snowProbability: Int,
    val iceProbability: Int,
    val cloudCover: Int,
    val mobileLink: String,
    val link: String,
)