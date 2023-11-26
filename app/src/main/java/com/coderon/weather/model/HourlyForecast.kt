package com.coderon.weather.model

import com.google.gson.annotations.SerializedName

data class HourlyForecast(
    @SerializedName("DateTime")
    val date: String,
    @SerializedName("EpochDateTime")
    val epochDAteTime: Long,
    @SerializedName("WeatherIcon")
    val weatherIcon: Int,
    @SerializedName("IconPhrase")
    val iconPhrase: String,
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean,
    @SerializedName("IsDaylight")
    val isDayNight: Boolean,
    @SerializedName("Temperature")
    val temperature: Value,
    @SerializedName("RealFeelTemperature")
    val realFeelTemperature: RealFeelTemperature,
    @SerializedName("RealFeelTemperatureShade")
    val realFeelTemperatureShade: RealFeelTemperature,
    @SerializedName("Wind")
    val wind: Wind,
    @SerializedName("WindGust")
    val windGust: WindGust,
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
    @SerializedName("Rain")
    val rain: Value,
    @SerializedName("Snow")
    val snow: Value,
    @SerializedName("Ice")
    val ice: Value,
    @SerializedName("CloudCover")
    val cloudCover: Int,
    @SerializedName("MobileLink") val mobileLink: String,
    @SerializedName("Link") val link: String,
)