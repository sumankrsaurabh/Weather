package com.coderon.weather.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.coderon.weather.database.entity.DailyForecast

@Dao
interface DailyForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDailyForecast(hourlyForecast: List<DailyForecast>)

    @Query("SELECT * FROM daily_forecast")
    suspend fun getDailyForecast(): List<DailyForecast>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDailyForecast(dailyForecast: DailyForecast)
}
