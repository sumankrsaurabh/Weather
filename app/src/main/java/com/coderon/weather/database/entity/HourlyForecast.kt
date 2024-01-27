package com.coderon.weather.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("hourly_forecast")
data class HourlyForecast(
    @PrimaryKey(true)
    val id : Int,
    val dateTime: String,
    val epochDateTime: Long,
    val weatherIcon: Int,
    val iconPhrase: String,
    val hasPrecipitation: Boolean,
    val isDayNight: Boolean,
//    temperature
    val temperatureValue: Double,
    val temperatureUnit: String,
    val temperatureUnitType: Int,
    // realFeelTemperature
    val realFeelTemperatureValue: Double,
    val realFeelTemperatureUnit: String,
    val realFeelTemperatureUnitType: Int,
    val realFeelTemperaturePhrase: String,
//    Wind class
    val windSpeedValue: Double,
    val windSpeedUnit: String,
    val windSpeedUnitType: Int,
    val windDirectionDegrees: Int,
    val windDirectionLocalized: String,
    val windDirectionEnglish: String,
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
