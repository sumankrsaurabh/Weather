package com.coderon.weather.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coderon.weather.model.utility.Moon
import com.coderon.weather.model.utility.Sun
import com.coderon.weather.model.utility.Temperature
import com.coderon.weather.model.utility.WeatherStatus

@Entity("daily_forecast")
data class DailyForecast (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val key:String,
    val date: String,
    val epochDate: Long,
    @Embedded("sun_")
    val sun: Sun,
    @Embedded("moon_")
    val moon: Moon,
    @Embedded("temp_")
    val temperature: Temperature,
    val hoursOfSun: Double,
    @Embedded("day_")
    val day: WeatherStatus,
    @Embedded("night_")
    val night: WeatherStatus,
)