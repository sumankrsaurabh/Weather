package com.coderon.weather.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.coderon.weather.database.entity.HourlyForecast

@Dao
interface HourlyForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHourlyForecast(hourlyForecast: HourlyForecast)

    @Query("SELECT * FROM hourly_forecast")
    suspend fun getHourlyForecast(): List<HourlyForecast>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateHourlyForecast(hourlyForecast: HourlyForecast)
}
