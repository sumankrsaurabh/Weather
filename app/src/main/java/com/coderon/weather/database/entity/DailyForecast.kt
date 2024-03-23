package com.coderon.weather.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("daily_forecast")
data class DailyForecast(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val key: String,
    val date: String,
    val epochDate: Long,
    val sunriseEpoch: Long,
    val sunsetEpoch: Long,
    val sunRise: String,
    val sunSet: String,
    val moonRise: String,
    val moonSet: String,
    val moonPhase: String,
    val moonAge: Int,
    val moonRiseEpoch: Long,
    val moonSetEpoch: Long,
    val tempMaxValue: Double,
    val tempMaxUnit: String,
    val tempMaxUnitType: Int,
    val tempMinValue: Double,
    val tempMinUnit: String,
    val tempMinUnitType: Int,
    val hourOfSun: Double,
//    day
    val dayIcon: Int,
    val dayIconPhrase: String,
    val dayHasPrecipitation: Boolean,
    val dayShortPhrase: String,
    val dayLongPhrase: String,
    val dayPrecipitationProbability: Int,
    val dayRainProbability: Int,
    val dayThunderProbability: Int,
    val daySnowProbability: Int,
    val dayIceProbability: Int,
//    wind
    val dayWindSpeedValue: Double,
    val dayWindSpeedUnit: String,
    val dayWindSpeedUnitType: Int,
    val dayWindDirectionDegrees: Int,
    val dayWindDirectionLocalized: String,
    val dayWindDirectionEnglish: String,
    val dayWindGustValue: Double,
    val dayWindGustUnit: String,
    val dayWindGustUnitType: Int,
//    total liquid
    val dayTotalLiquidValue: Double,
    val dayTotalLiquidUnit: String,
    val dayTotalLiquidUnitType: Int,
//  Rain
    val dayRainValue: Double,
    val dayRainUnit: String,
    val dayRainUnitType: Int,
    //snow
    val daySnowValue: Double,
    val daySnowUnit: String,
    val daySnowUnitType: Int,
    //ice
    val dayIceValue: Double,
    val dayIceUnit: String,
    val dayIceUnitType: Int,
//Hors of Prescription
    val dayHoursOfPrecipitation: Int,
    val dayHoursOfRain: Int,
    val dayHoursOfSnow: Int,
    val dayHoursOfIce: Int,
    val dayCloudCover: Int,
    //evapration
    val dayEvaporationValue: Double,
    val dayEvaporationUnit: String,
    val dayEvaporationUnitType: Int,

    //SolarIrradiance
    val daySolarIrradianceValue: Double,
    val daySolarIrradianceUnit: String,
    val daySolarIrradianceUnitType: Int,

    //RelativeHumidity
    val dayRelativeHumidityMinimum: Int,
    val dayRelativeHumidityMaximum: Int,
    val dayRelativeHumidityAverage: Int,


    //Night

    val nightIcon: Int,
    val nightIconPhrase: String,
    val nightHasPrecipitation: Boolean,
    val nightShortPhrase: String,
    val nightLongPhrase: String,
    val nightPrecipitationProbability: Int,
    val nightRainProbability: Int,
    val nightThunderProbability: Int,
    val nightSnowProbability: Int,
    val nightIceProbability: Int,
//    wind
    val nightWindSpeedValue: Double,
    val nightWindSpeedUnit: String,
    val nightWindSpeedUnitType: Int,
    val nightWindDirectionLocalized: String,
    val nightWindDirectionDegrees: Int,
    val nightWindDirectionEnglish: String,
    val nightWindGustValue: Double,
    val nightWindGustUnit: String,
    val nightWindGustUnitType: Int,
//    total liquid
    val nightTotalLiquidValue: Double,
    val nightTotalLiquidUnit: String,
    val nightTotalLiquidUnitType: Int,
//  Rain
    val nightRainValue: Double,
    val nightRainUnit: String,
    val nightRainUnitType: Int,
    //snow
    val nightSnowValue: Double,
    val nightSnowUnit: String,
    val nightSnowUnitType: Int,
    //ice
    val nightIceValue: Double,
    val nightIceUnit: String,
    val nightIceUnitType: Int,
//Hors of Prescription
    val nightHoursOfPrecipitation: Int,
    val nightHoursOfRain: Int,
    val nightHoursOfSnow: Int,
    val nightHoursOfIce: Int,
    val nightCloudCover: Int,
    //evapration
    val nightEvaporationValue: Double,
    val nightEvaporationUnit: String,
    val nightEvaporationUnitType: Int,

    //SolarIrradiance
    val nightSolarIrradianceValue: Double,
    val nightSolarIrradianceUnit: String,
    val nightSolarIrradianceUnitType: Int,

    //RelativeHumidity
    val nightRelativeHumidityMinimum: Int,
    val nightRelativeHumidityMaximum: Int,
    val nightRelativeHumidityAverage: Int,
)