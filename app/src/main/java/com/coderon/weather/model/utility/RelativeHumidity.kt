package com.coderon.weather.model.utility

import com.google.gson.annotations.SerializedName

data class RelativeHumidity(
    @SerializedName("Minimum")
    val minimum: Int,
    @SerializedName("Maximum")
    val maximum: Int,
    @SerializedName("Average")
    val average: Int,
)
