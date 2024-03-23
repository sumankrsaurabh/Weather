package com.coderon.weather.database.converters

import com.coderon.weather.database.entity.HourlyForecast
import com.coderon.weather.database.entity.Location


fun Location.fromLocal(): com.coderon.weather.model.Location {
    return com.coderon.weather.model.Location(
        version = this.version,
        key = this.key,
        type = this.type,
        rank = this.rank,
        localizedName = this.localizedName,
        englishName = this.englishName,
        geoPosition = this.geoPosition
    )
}

fun com.coderon.weather.model.Location.toLocal(): Location {
    return Location(
        version = this.version,
        key = this.key,
        type = this.type,
        rank = this.rank,
        localizedName = this.localizedName,
        englishName = this.englishName,
        geoPosition = this.geoPosition
    )
}

fun HourlyForecast.fromLocal(): com.coderon.weather.model.HourlyForecast {
    return com.coderon.weather.model.HourlyForecast(
        dateTime = this.dateTime,
        epochDateTime = this.epochDateTime,
        weatherIcon = this.weatherIcon,
        iconPhrase = this.iconPhrase,
        hasPrecipitation = this.hasPrecipitation,
        isDayNight = this.isDayNight,
        temperature = this.temperature,
        realFeelTemperature = this.realFeelTemperature,
        wind = this.wind,
        relativeHumidity = this.relativeHumidity,
        indoorRelativeHumidity = this.indoorRelativeHumidity,
        uvIndex = this.uvIndex,
        uvIndexText = this.uvIndexText,
        precipitationProbability = this.precipitationProbability,
        thunderstormProbability = this.thunderstormProbability,
        rainProbability = this.rainProbability,
        snowProbability = this.snowProbability,
        iceProbability = this.iceProbability,
        cloudCover = this.cloudCover,
        mobileLink = this.mobileLink,
        link = this.link,
    )
}

fun com.coderon.weather.model.HourlyForecast.toLocal(key: String): HourlyForecast {
    return HourlyForecast(
        key = key,
        dateTime = this.dateTime,
        epochDateTime = this.epochDateTime,
        weatherIcon = this.weatherIcon,
        iconPhrase = this.iconPhrase,
        hasPrecipitation = this.hasPrecipitation,
        isDayNight = this.isDayNight,
        temperature = this.temperature,
        realFeelTemperature = this.realFeelTemperature,
        wind = this.wind,
        relativeHumidity = this.relativeHumidity,
        indoorRelativeHumidity = this.indoorRelativeHumidity,
        uvIndex = this.uvIndex,
        uvIndexText = this.uvIndexText,
        precipitationProbability = this.precipitationProbability,
        thunderstormProbability = this.thunderstormProbability,
        rainProbability = this.rainProbability,
        snowProbability = this.snowProbability,
        iceProbability = this.iceProbability,
        cloudCover = this.cloudCover,
        mobileLink = this.mobileLink,
        link = this.link,
    )
}