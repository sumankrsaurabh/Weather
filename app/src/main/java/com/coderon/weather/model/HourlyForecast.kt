package com.coderon.weather.model

import com.coderon.weather.model.utility.RealFeelTemperature
import com.coderon.weather.model.utility.TemperatureValue
import com.coderon.weather.model.utility.Wind
import com.coderon.weather.model.utility.WindGust
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
    @SerializedName("RealFeelTemperatureShade")
    val realFeelTemperatureShade: RealFeelTemperature,
    @SerializedName("WetBulbTemperature")
    val wetBulbTemperature: TemperatureValue,
    @SerializedName("WetBulbGlobeTemperature")
    val wetBulbGlobeTemperature: TemperatureValue,
    @SerializedName("DewPoint")
    val dewPoint: TemperatureValue,
    @SerializedName("Wind")
    val wind: Wind,
    @SerializedName("WindGust")
    val windGust: WindGust,
    @SerializedName("RelativeHumidity")
    val relativeHumidity: Int,
    @SerializedName("IndoorRelativeHumidity")
    val indoorRelativeHumidity: Int,
    @SerializedName("Visibility")
    val visibility: TemperatureValue,// Temperature class as Visibility
    @SerializedName("Ceiling")
    val ceiling: TemperatureValue, // Temperature class as Ceiling
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
    @SerializedName("TotalLiquid")
    val totalLiquid: TemperatureValue, // as TotalLiquid instance
    @SerializedName("Rain")
    val rain: TemperatureValue,// as rain object
    @SerializedName("Snow")
    val snow: TemperatureValue, // as snow object
    @SerializedName("Ice")
    val ice: TemperatureValue,// as ice instance
    @SerializedName("CloudCover")
    val cloudCover: Int,
    @SerializedName("Evapotranspiration")
    val evapotranspiration: TemperatureValue, //as Evapotranspiration object
    @SerializedName("SolarIrradiance")
    val solarIrradiance: TemperatureValue, //as SolarIrradiance object
    @SerializedName("MobileLink")
    val mobileLink: String,
    @SerializedName("Link")
    val link: String,
)