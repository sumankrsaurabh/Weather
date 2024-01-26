package com.coderon.weather.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.coderon.weather.database.entity.Location

@Dao
interface LocationDao {
    @Insert
    fun addCity(cityEntity: Location)

    @Query("SELECT * FROM location_table")
    fun getCity(): Location

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCity(cityEntity: Location)
}