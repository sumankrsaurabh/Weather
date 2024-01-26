package com.coderon.weather.model

import com.coderon.weather.model.utility.GeoPosition
import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("Version")
    val version: Int,
    @SerializedName("Key")
    val key: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Rank")
    val rank: Int,
    @SerializedName("LocalizedName")
    val localizedName: String,
    @SerializedName("EnglishName")
    val englishName: String,
    @SerializedName("GeoPosition")
    val geoPosition: GeoPosition,
)

