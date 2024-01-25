package com.coderon.weather.model.utility

import com.google.gson.annotations.SerializedName

data class Wind (
    @SerializedName("Speed")
    val speed: Speed,
    @SerializedName("Direction")
    val direction: Direction
)


data class Speed(
    @SerializedName("Value")
    val value: Double,
    @SerializedName("Unit")
    val unit: String,
    @SerializedName("UnitType")
    val unitType: Int,
)

data class Direction(
    @SerializedName("Degrees")
    val degrees: Int,
    @SerializedName("Localized")
    val localized: String,
    @SerializedName("English")
    val english: String
)

data class WindGust(
    @SerializedName("Speed")
    val speed: Speed
)