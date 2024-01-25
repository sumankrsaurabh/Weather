package com.coderon.weather.model

import com.coderon.weather.model.utility.AdministrativeArea
import com.coderon.weather.model.utility.GeoPosition
import com.coderon.weather.model.utility.Name
import com.coderon.weather.model.utility.SupplementalAdminAreas
import com.coderon.weather.model.utility.TimeZone
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
    @SerializedName("PrimaryPostalCode")
    val primaryPostalCode: String,
    @SerializedName("Country")
    val country: Name,
    @SerializedName("AdministrativeArea")
    val administrativeArea: AdministrativeArea,
    @SerializedName("TimeZone")
    val timeZone: TimeZone,
    @SerializedName("GeoPosition")
    val geoPosition: GeoPosition,
    @SerializedName("IsAlias")
    val isAlias: Boolean,
    @SerializedName("SupplementalAdminAreas")
    val supplementalAdminAreas: List<SupplementalAdminAreas>,
)

