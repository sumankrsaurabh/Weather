package com.coderon.weather.model

import com.google.gson.annotations.SerializedName


data class Name(
    @SerializedName("ID")
    val id: String,
    @SerializedName("LocalizedName")
    val localizedName: String,
    @SerializedName("EnglishName")
    val englishName: String,
)

data class Position(
    @SerializedName("Latitude")
    val latitude: Double,
    @SerializedName("Longitude")
    val longitude: Double,
    @SerializedName("Elevation")
    val elevation: Elevation,

    )

data class Elevation(
    @SerializedName("Metric")
    val metric: Value,
    @SerializedName("Imperial")
    val imperial: Value,

    )

data class Value(
    @SerializedName("Value")
    val value: Double,
    @SerializedName("Unit")
    val unit: String,
    @SerializedName("UnitType")
    val unitType: Int,

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

data class TimeZone(
    @SerializedName("Code")
    val code: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("GmtOffset")
    val gmtOffset: Double,
    @SerializedName("IsDayNightSaving")
    val isDayNightSaving: Boolean,
    @SerializedName("NextOffsetChange")
    val nextOffsetChange: String?,
)


data class WeatherStat(
    @SerializedName("Icon")
    val icon: Int,
    @SerializedName("IconPhrase")
    val iconPhrase: String,
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean,
)

