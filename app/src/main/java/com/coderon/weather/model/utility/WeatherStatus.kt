package com.coderon.weather.model.utility

import com.google.gson.annotations.SerializedName

data class WeatherStatus (
    @SerializedName("Icon")
    val icon: Int,
    @SerializedName("IconPhrase")
    val iconPhrase: String,
    @SerializedName("HasPrecipitation")
    val hasPrecipitation:Boolean,
    @SerializedName("ShortPhrase")
    val shortPhrase: String,
    @SerializedName("LongPhrase")
    val longPhrase: String,
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
    @SerializedName("Wind")
    val wind: Wind,
    @SerializedName("WindGust")
    val windGust: WindGust,
    @SerializedName("TotalLiquid")
    val totalLiquid : TemperatureValue,
    @SerializedName("Rain")
    val rain : TemperatureValue,
    @SerializedName("Snow")
    val snow: TemperatureValue,
    @SerializedName("Ice")
    val ice: TemperatureValue,
    @SerializedName("HoursOfPrecipitation")
    val hoursOfPrecipitation: Int,
    @SerializedName("HoursOfRain")
    val hoursOfRain: Int,
    @SerializedName("HoursOfSnow")
    val hoursOfSnow: Int,
    @SerializedName("HoursOfIce")
    val hoursOfIce: Int,
    @SerializedName("CloudCover")
    val cloudCover: Int,
    @SerializedName("Evapotranspiration")
    val evapotranspiration: TemperatureValue,
    @SerializedName("SolarIrradiance")
    val solarIrradiance: TemperatureValue,
    @SerializedName("RelativeHumidity")
    val relativeHumidity: RelativeHumidity,
)
