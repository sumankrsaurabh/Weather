package com.coderon.weather.database.converters

import com.coderon.weather.model.DailyForecast
import com.coderon.weather.model.utility.Direction
import com.coderon.weather.model.utility.Moon
import com.coderon.weather.model.utility.RelativeHumidity
import com.coderon.weather.model.utility.Speed
import com.coderon.weather.model.utility.Sun
import com.coderon.weather.model.utility.Temperature
import com.coderon.weather.model.utility.TemperatureValue
import com.coderon.weather.model.utility.WeatherStatus
import com.coderon.weather.model.utility.Wind
import com.coderon.weather.model.utility.WindGust

fun DailyForecast.toLocal(key: String): com.coderon.weather.database.entity.DailyForecast {
    return com.coderon.weather.database.entity.DailyForecast(
        key = key,
        date = this.date,
        epochDate = this.epochDate,
        sunriseEpoch = this.sun.epochRise,
        sunsetEpoch = this.sun.epochSet,
        sunRise = this.sun.rise,
        sunSet = this.sun.set,
        moonPhase = this.moon.phase,
        moonRise = this.moon.rise,
        moonSet = this.moon.set,
        moonRiseEpoch = this.moon.epochRise,
        moonSetEpoch = this.moon.epochSet,
        moonAge = this.moon.age,
        tempMaxValue = this.temperature.maximum.value,
        tempMaxUnit = this.temperature.maximum.unit,
        tempMaxUnitType = this.temperature.maximum.unitType,
        tempMinValue = this.temperature.minimum.value,
        tempMinUnit = this.temperature.minimum.unit,
        tempMinUnitType = this.temperature.minimum.unitType,
        hourOfSun = this.hoursOfSun,
        dayIcon = this.day.icon,
        dayIconPhrase = this.day.iconPhrase,
        dayHasPrecipitation = this.day.hasPrecipitation,
        dayShortPhrase = this.day.shortPhrase,
        dayLongPhrase = this.day.longPhrase,
        dayPrecipitationProbability = this.day.precipitationProbability,
        dayRainProbability = this.day.rainProbability,
        dayThunderProbability = this.day.thunderstormProbability,
        daySnowProbability = this.day.snowProbability,
        dayIceProbability = this.day.iceProbability,
        dayWindSpeedUnit = this.day.wind.speed.unit,
        dayWindSpeedValue = this.day.wind.speed.value,
        dayWindSpeedUnitType = this.day.wind.speed.unitType,
        dayWindDirectionLocalized = this.day.wind.direction.localized,
        dayWindDirectionDegrees = this.day.wind.direction.degrees,
        dayWindDirectionEnglish = this.day.wind.direction.english,
        dayWindGustUnit = this.day.windGust.speed.unit,
        dayWindGustValue = this.day.windGust.speed.value,
        dayWindGustUnitType = this.day.windGust.speed.unitType,
        dayTotalLiquidUnit = this.day.totalLiquid.unit,
        dayTotalLiquidValue = this.day.totalLiquid.value,
        dayTotalLiquidUnitType = this.day.totalLiquid.unitType,
        dayRainValue = this.day.rain.value,
        dayRainUnit = this.day.rain.unit,
        dayRainUnitType = this.day.rain.unitType,
        daySnowValue = this.day.snow.value,
        daySnowUnit = this.day.snow.unit,
        daySnowUnitType = this.day.snow.unitType,
        dayIceValue = this.day.ice.value,
        dayIceUnit = this.day.ice.unit,
        dayIceUnitType = this.day.ice.unitType,
        dayHoursOfPrecipitation = this.day.hoursOfPrecipitation,
        dayHoursOfRain = this.day.hoursOfRain,
        dayHoursOfSnow = this.day.hoursOfSnow,
        dayHoursOfIce = this.day.hoursOfIce,
        dayCloudCover = this.day.cloudCover,
        dayEvaporationValue = this.day.evapotranspiration.value,
        dayEvaporationUnit = this.day.evapotranspiration.unit,
        dayEvaporationUnitType = this.day.evapotranspiration.unitType,
        daySolarIrradianceUnit = this.day.solarIrradiance.unit,
        daySolarIrradianceValue = this.day.solarIrradiance.value,
        daySolarIrradianceUnitType = this.day.solarIrradiance.unitType,
        dayRelativeHumidityMinimum = this.day.relativeHumidity.minimum,
        dayRelativeHumidityMaximum = this.day.relativeHumidity.maximum,
        dayRelativeHumidityAverage = this.day.relativeHumidity.average,
        //night
        nightIcon = this.night.icon,
        nightIconPhrase = this.night.iconPhrase,
        nightHasPrecipitation = this.night.hasPrecipitation,
        nightShortPhrase = this.night.shortPhrase,
        nightLongPhrase = this.night.longPhrase,
        nightPrecipitationProbability = this.night.precipitationProbability,
        nightRainProbability = this.night.rainProbability,
        nightThunderProbability = this.night.thunderstormProbability,
        nightSnowProbability = this.night.snowProbability,
        nightIceProbability = this.night.iceProbability,
        nightWindSpeedUnit = this.night.wind.speed.unit,
        nightWindSpeedValue = this.night.wind.speed.value,
        nightWindSpeedUnitType = this.night.wind.speed.unitType,
        nightWindDirectionLocalized = this.night.wind.direction.localized,
        nightWindDirectionDegrees = this.night.wind.direction.degrees,
        nightWindDirectionEnglish = this.night.wind.direction.english,
        nightWindGustUnit = this.night.windGust.speed.unit,
        nightWindGustValue = this.night.windGust.speed.value,
        nightWindGustUnitType = this.night.windGust.speed.unitType,
        nightTotalLiquidUnit = this.night.totalLiquid.unit,
        nightTotalLiquidValue = this.night.totalLiquid.value,
        nightTotalLiquidUnitType = this.night.totalLiquid.unitType,
        nightRainValue = this.night.rain.value,
        nightRainUnit = this.night.rain.unit,
        nightRainUnitType = this.night.rain.unitType,
        nightSnowValue = this.night.snow.value,
        nightSnowUnit = this.night.snow.unit,
        nightSnowUnitType = this.night.snow.unitType,
        nightIceValue = this.night.ice.value,
        nightIceUnit = this.night.ice.unit,
        nightIceUnitType = this.night.ice.unitType,
        nightHoursOfPrecipitation = this.night.hoursOfPrecipitation,
        nightHoursOfRain = this.night.hoursOfRain,
        nightHoursOfSnow = this.night.hoursOfSnow,
        nightHoursOfIce = this.night.hoursOfIce,
        nightCloudCover = this.night.cloudCover,
        nightEvaporationValue = this.night.evapotranspiration.value,
        nightEvaporationUnit = this.night.evapotranspiration.unit,
        nightEvaporationUnitType = this.night.evapotranspiration.unitType,
        nightSolarIrradianceUnit = this.night.solarIrradiance.unit,
        nightSolarIrradianceValue = this.night.solarIrradiance.value,
        nightSolarIrradianceUnitType = this.night.solarIrradiance.unitType,
        nightRelativeHumidityMinimum = this.night.relativeHumidity.minimum,
        nightRelativeHumidityMaximum = this.night.relativeHumidity.maximum,
        nightRelativeHumidityAverage = this.night.relativeHumidity.average,
    )
}

fun com.coderon.weather.database.entity.DailyForecast.fromLocal(): DailyForecast {
    return DailyForecast(
        date = this.date,
        epochDate = this.epochDate,
        sun = Sun(
            epochRise = this.sunriseEpoch,
            epochSet = this.sunsetEpoch,
            rise = this.sunRise,
            set = this.sunSet
        ),
        moon = Moon(
            phase = this.moonPhase,
            rise = this.moonRise,
            set = this.moonSet,
            epochRise = this.moonRiseEpoch,
            epochSet = this.moonSetEpoch,
            age = this.moonAge
        ),
        temperature = Temperature(
            minimum = TemperatureValue(
                value = this.tempMinValue,
                unit = this.tempMinUnit,
                unitType = this.tempMinUnitType
            ),
            maximum = TemperatureValue(
                value = this.tempMaxValue,
                unit = this.tempMaxUnit,
                unitType = this.tempMaxUnitType
            )
        ),
        hoursOfSun = this.hourOfSun,
        day = WeatherStatus(
            icon = this.dayIcon,
            iconPhrase = this.dayIconPhrase,
            hasPrecipitation = this.dayHasPrecipitation,
            shortPhrase = this.dayShortPhrase,
            longPhrase = this.dayLongPhrase,
            precipitationProbability = this.dayPrecipitationProbability,
            rainProbability = this.dayRainProbability,
            thunderstormProbability = this.dayThunderProbability,
            snowProbability = this.daySnowProbability,
            iceProbability = this.dayIceProbability,
            wind = Wind(
                speed = Speed(
                    unit = this.dayWindSpeedUnit,
                    value = this.dayWindSpeedValue,
                    unitType = this.dayWindSpeedUnitType
                ),
                direction = Direction(
                    localized = this.dayWindDirectionLocalized,
                    degrees = this.dayWindDirectionDegrees,
                    english = this.dayWindDirectionEnglish
                )
            ),
            windGust = WindGust(
                speed = Speed(
                    unit = this.dayWindGustUnit,
                    value = this.dayWindGustValue,
                    unitType = this.dayWindGustUnitType
                )
            ),
            totalLiquid = TemperatureValue(
                unit = this.dayTotalLiquidUnit,
                value = this.dayTotalLiquidValue,
                unitType = this.dayTotalLiquidUnitType
            ),
            rain = TemperatureValue(
                value = this.dayRainValue,
                unit = this.dayRainUnit,
                unitType = this.dayRainUnitType
            ),
            snow = TemperatureValue(
                value = this.daySnowValue,
                unit = this.daySnowUnit,
                unitType = this.daySnowUnitType
            ),
            ice = TemperatureValue(
                value = this.dayIceValue,
                unit = this.dayIceUnit,
                unitType = this.dayIceUnitType
            ),
            hoursOfPrecipitation = this.dayHoursOfPrecipitation,
            hoursOfRain = this.dayHoursOfRain,
            hoursOfSnow = this.dayHoursOfSnow,
            hoursOfIce = this.dayHoursOfIce,
            cloudCover = this.dayCloudCover,
            evapotranspiration = TemperatureValue(
                value = this.dayEvaporationValue,
                unit = this.dayEvaporationUnit,
                unitType = this.dayEvaporationUnitType
            ),
            solarIrradiance = TemperatureValue(
                value = this.daySolarIrradianceValue,
                unit = this.daySolarIrradianceUnit,
                unitType = this.daySolarIrradianceUnitType
            ),
            relativeHumidity = RelativeHumidity(
                minimum = this.dayRelativeHumidityMinimum,
                maximum = this.dayRelativeHumidityMaximum,
                average = this.dayRelativeHumidityAverage
            )
        ),
        night = WeatherStatus(
            icon = this.nightIcon,
            iconPhrase = this.nightIconPhrase,
            hasPrecipitation = this.nightHasPrecipitation,
            shortPhrase = this.nightShortPhrase,
            longPhrase = this.nightLongPhrase,
            precipitationProbability = this.nightPrecipitationProbability,
            rainProbability = this.nightRainProbability,
            thunderstormProbability = this.nightThunderProbability,
            snowProbability = this.nightSnowProbability,
            iceProbability = this.nightIceProbability,
            wind = Wind(
                speed = Speed(
                    unit = this.nightWindSpeedUnit,
                    value = this.nightWindSpeedValue,
                    unitType = this.nightWindSpeedUnitType
                ),
                direction = Direction(
                    localized = this.nightWindDirectionLocalized,
                    degrees = this.nightWindDirectionDegrees,
                    english = this.nightWindDirectionEnglish
                )
            ),
            windGust = WindGust(
                speed = Speed(
                    unit = this.nightWindGustUnit,
                    value = this.nightWindGustValue,
                    unitType = this.nightWindGustUnitType
                )
            ),
            totalLiquid = TemperatureValue(
                unit = this.nightTotalLiquidUnit,
                value = this.nightTotalLiquidValue,
                unitType = this.nightTotalLiquidUnitType
            ),
            rain = TemperatureValue(
                value = this.nightRainValue,
                unit = this.nightRainUnit,
                unitType = this.nightRainUnitType
            ),
            snow = TemperatureValue(
                value = this.nightSnowValue,
                unit = this.nightSnowUnit,
                unitType = this.nightSnowUnitType
            ),
            ice = TemperatureValue(
                value = this.nightIceValue,
                unit = this.nightIceUnit,
                unitType = this.nightIceUnitType
            ),
            hoursOfPrecipitation = this.nightHoursOfPrecipitation,
            hoursOfRain = this.nightHoursOfRain,
            hoursOfSnow = this.nightHoursOfSnow,
            hoursOfIce = this.nightHoursOfIce,
            cloudCover = this.nightCloudCover,
            evapotranspiration = TemperatureValue(
                value = this.nightEvaporationValue,
                unit = this.nightEvaporationUnit,
                unitType = this.nightEvaporationUnitType
            ),
            solarIrradiance = TemperatureValue(
                value = this.nightSolarIrradianceValue,
                unit = this.nightSolarIrradianceUnit,
                unitType = this.nightSolarIrradianceUnitType
            ),
            relativeHumidity = RelativeHumidity(
                minimum = this.nightRelativeHumidityMinimum,
                maximum = this.nightRelativeHumidityMaximum,
                average = this.nightRelativeHumidityAverage
            )
        )
    )
}