package com.coderon.weather.model.utility

import com.google.gson.annotations.SerializedName

data class Sun(
    @SerializedName("Rise")
    val rise: String,
    @SerializedName("EpochRise")
    val epochRise: Long,
    @SerializedName("Set")
    val set: String,
    @SerializedName("EpochSet")
    val epochSet: Long,
)

data class Moon(
    @SerializedName("Rise")
    val rise: String,
    @SerializedName("EpochRise")
    val epochRise: Long,
    @SerializedName("Set")
    val set: String,
    @SerializedName("EpochSet")
    val epochSet: Long,
    @SerializedName("Phase")
    val phase: String,
    @SerializedName("Age")
    val age: Int,
)
