package com.coderon.weather.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.coderon.weather.database.daos.HourlyForecastDao
import com.coderon.weather.database.daos.LocationDao
import com.coderon.weather.database.entity.HourlyForecast
import com.coderon.weather.database.entity.Location

@Database(
    version = 1,
    entities = [Location::class, HourlyForecast::class],
    exportSchema = true
)
abstract class DataBase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun hourlyForecastDao(): HourlyForecastDao
}