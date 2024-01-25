package com.coderon.weather.model.utility

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("ID")
    val id: String,
    @SerializedName("LocalizedName")
    val localizedName: String,
    @SerializedName("EnglishName")
    val englishName: String,
)

data class AdministrativeArea(
    @SerializedName("ID")
    val id: String,
    @SerializedName("LocalizedName")
    val localizedName: String,
    @SerializedName("EnglishName")
    val englishName: String,
    @SerializedName("Level")
    val level: Int,
    @SerializedName("LocalizedType")
    val localizedType: String,
    @SerializedName("EnglishType")
    val englishType: String,
    @SerializedName("CountryID")
    val countryID: String,
)

data class LocationData(
    @SerializedName("TimeZone")
    val timeZone: TimeZone,
    @SerializedName("GeoPosition")
    val geoPosition: GeoPosition,
)

data class TimeZone(
    @SerializedName("Code")
    val code: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("GmtOffset")
    val gmtOffset: Double,
    @SerializedName("IsDaylightSaving")
    val isDaylightSaving: Boolean,
    @SerializedName("NextOffsetChange")
    val nextOffsetChange: String?, // Nullability added
)

data class GeoPosition(
    @SerializedName("Latitude")
    val latitude: Double,
    @SerializedName("Longitude")
    val longitude: Double,
    @SerializedName("Elevation")
    val elevation: Elevation,
    @SerializedName("Imperial")
    val imperial: Elevation,
)

data class Elevation(
    @SerializedName("Metric")
    val metric: TemperatureValue,
    @SerializedName("Imperial")
    val imperial: TemperatureValue,
)
