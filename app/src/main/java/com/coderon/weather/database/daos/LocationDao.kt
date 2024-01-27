package com.coderon.weather.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.coderon.weather.database.entity.Location

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCity(cityEntity: Location)

    @Query("SELECT * FROM location_table")
    suspend fun getCity(): List<Location>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLocation(cityEntity: Location)
}