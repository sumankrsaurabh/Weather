package com.coderon.weather.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coderon.weather.model.utility.GeoPosition

@Entity(tableName = "location_table")
data class Location(
    val version: Int,
    @PrimaryKey(autoGenerate = false)
    val key: String,
    val type: String,
    val rank: Int,
    val localizedName: String,
    val englishName: String,
    @Embedded
    val geoPosition : GeoPosition
)