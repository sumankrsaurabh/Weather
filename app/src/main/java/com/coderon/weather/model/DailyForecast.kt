package com.coderon.weather.model


import com.coderon.weather.model.utility.Moon
import com.coderon.weather.model.utility.Sun
import com.coderon.weather.model.utility.Temperature
import com.coderon.weather.model.utility.WeatherStatus
import com.google.gson.annotations.SerializedName

data class DailyForecast(
    @SerializedName("Date")
    val date: String,
    @SerializedName("EpochDate")
    val epochDate: Long,
    @SerializedName("Sun")
    val sun: Sun,
    @SerializedName("Moon")
    val moon: Moon,
    @SerializedName("Temperature")
    val temperature: Temperature,
    @SerializedName("HoursOfSun")
    val hoursOfSun: Double,
    @SerializedName("Day")
    val day: WeatherStatus,
    @SerializedName("Night")
    val night: WeatherStatus,
)
