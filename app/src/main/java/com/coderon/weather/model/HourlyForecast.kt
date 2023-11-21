package com.coderon.weather.model

import com.google.gson.annotations.SerializedName

data class HourlyForecast(
    @SerializedName("DateTime")
    val date:String,
    @SerializedName("EpochDateTime")
    val epochDAteTime:Long,
    @SerializedName("WeatherIcon")
    val weatherIcon:Int,
    @SerializedName("IconPhrase")
    val iconPhrase:String,
    @SerializedName("HasPrecipitation")
    val hasPrecipitation:Boolean,
    @SerializedName("IsDaylight")
    val isDayNight:Boolean,
    @SerializedName("Temperature")
    val temperature: Value,
)
