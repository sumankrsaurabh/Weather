package com.coderon.weather.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.coderon.weather.database.converters.DirectionTypeConverter
import com.coderon.weather.database.converters.RelativeHumidityTypeConverter
import com.coderon.weather.database.converters.TemperatureTypeConverter
import com.coderon.weather.database.converters.TemperatureValueTypeConverter
import com.coderon.weather.database.converters.TypeConverterMoon
import com.coderon.weather.database.converters.TypeConverterSun
import com.coderon.weather.database.converters.TypeConverterWind
import com.coderon.weather.database.converters.WeatherStatusConverter
import com.coderon.weather.database.converters.WindGustTypeConverter
import com.coderon.weather.database.converters.WindTypeConverter
import com.coderon.weather.database.daos.DailyForecastDao
import com.coderon.weather.database.daos.HourlyForecastDao
import com.coderon.weather.database.daos.LocationDao
import com.coderon.weather.database.entity.DailyForecast
import com.coderon.weather.database.entity.HourlyForecast
import com.coderon.weather.database.entity.Location

@Database(
    version = 8,
    entities = [Location::class, HourlyForecast::class, DailyForecast::class],
    exportSchema = false
)
@TypeConverters(
    TypeConverterWind::class,
    DirectionTypeConverter::class,
    TemperatureTypeConverter::class,
    WeatherStatusConverter::class,
    TemperatureValueTypeConverter::class,
    WindTypeConverter::class,
    WindGustTypeConverter::class,
    RelativeHumidityTypeConverter::class,
    TypeConverterMoon::class,
    TypeConverterSun::class
)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun hourlyForecastDao(): HourlyForecastDao
    abstract fun dailyForecastDao(): DailyForecastDao
}