package com.coderon.weather.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_table")
data class Location(
    val version: Int,
    @PrimaryKey(autoGenerate = false)
    val key: String,
    val type: String,
    val rank: Int,
    val localizedName: String,
    val englishName: String,
    val latitude: Double,
    val longitude: Double,
)