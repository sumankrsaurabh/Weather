package com.coderon.weather.model

import com.coderon.weather.model.utility.RealFeelTemperature
import com.coderon.weather.model.utility.TemperatureValue
import com.coderon.weather.model.utility.Wind
import com.google.gson.annotations.SerializedName

data class HourlyForecast(
    @SerializedName("DateTime")
    val dateTime: String,
    @SerializedName("EpochDateTime")
    val epochDateTime: Long,
    @SerializedName("WeatherIcon")
    val weatherIcon: Int,
    @SerializedName("IconPhrase")
    val iconPhrase: String,
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean,
    @SerializedName("IsDaylight")
    val isDayNight: Boolean,
    @SerializedName("Temperature")
    val temperature: TemperatureValue,
    @SerializedName("RealFeelTemperature")
    val realFeelTemperature: RealFeelTemperature,
    @SerializedName("Wind")
    val wind: Wind,
    @SerializedName("RelativeHumidity")
    val relativeHumidity: Int,
    @SerializedName("IndoorRelativeHumidity")
    val indoorRelativeHumidity: Int,
    @SerializedName("UVIndex")
    val uvIndex: Int,
    @SerializedName("UVIndexText")
    val uvIndexText: String,
    @SerializedName("PrecipitationProbability")
    val precipitationProbability: Int,
    @SerializedName("ThunderstormProbability")
    val thunderstormProbability: Int,
    @SerializedName("RainProbability")
    val rainProbability: Int,
    @SerializedName("SnowProbability")
    val snowProbability: Int,
    @SerializedName("IceProbability")
    val iceProbability: Int,
    @SerializedName("CloudCover")
    val cloudCover: Int,
    @SerializedName("MobileLink")
    val mobileLink: String,
    @SerializedName("Link")
    val link: String,
)