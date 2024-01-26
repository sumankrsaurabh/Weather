package com.coderon.weather.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.coderon.weather.database.entity.Location

@Database(version = 1,
    entities = [Location::class])
abstract class DataBase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}