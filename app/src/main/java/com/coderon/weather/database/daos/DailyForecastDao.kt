package com.coderon.weather.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coderon.weather.database.entity.DailyForecast

@Dao
interface DailyForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDailyForecast(dailyForecast: List<DailyForecast>)

    @Query("SELECT * FROM daily_forecast")
    suspend fun getDailyForecast(): List<DailyForecast>

    @Query("DELETE  FROM daily_forecast WHERE :key")
    suspend fun deleteDailyForecast(key: String)
}
