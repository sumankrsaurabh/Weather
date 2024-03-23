package com.coderon.weather.screen.utils

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import java.util.Locale


fun parseDate(date: Long): String {
    val format = SimpleDateFormat("hha", Locale.ENGLISH)
    format.timeZone = TimeZone.getDefault()
    return format.format(date * 1000).toString()
}

fun parseDay(date: Long): String {
    val format = SimpleDateFormat("eee", Locale.ENGLISH)
    format.timeZone = TimeZone.getDefault()
    return format.format(date * 1000).toString()
}

fun parseTime(date: Long): String {
    val format = SimpleDateFormat("h:mm a", Locale.ENGLISH)
    format.timeZone = TimeZone.getDefault()
    return format.format(date * 1000).toString()
}

fun Int.fixIcon(): String {
    return if (this < 10) {
        "0${this}"
    } else {
        toString()
    }
}
