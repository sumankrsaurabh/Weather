package com.coderon.weather.model.utility

import com.google.gson.annotations.SerializedName


data class Temperature(
    @SerializedName("Minimum")
    val minimum: TemperatureValue,
    @SerializedName("Maximum")
    val maximum: TemperatureValue,
)

data class TemperatureValue(
    @SerializedName("Value")
    val value: Double,
    @SerializedName("Unit")
    val unit: String,
    @SerializedName("UnitType")
    val unitType: Int,
)

data class RealFeelTemperature(
    @SerializedName("Value")
    val value: Double,
    @SerializedName("Unit")
    val unit: String,
    @SerializedName("UnitType")
    val unitType: Int,
    @SerializedName("Phrase")
    val phrase: String,
)
