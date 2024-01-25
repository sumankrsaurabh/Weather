package com.coderon.weather.model.utility

import com.google.gson.annotations.SerializedName

data class SupplementalAdminAreas(
    @SerializedName("Level")
    val level: Int,
    @SerializedName("LocalizedName")
    val localizedName: String,
    @SerializedName("EnglishName")
    val englishName: String,
)