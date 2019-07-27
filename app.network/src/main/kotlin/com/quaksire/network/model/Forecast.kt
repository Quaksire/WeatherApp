package com.quaksire.network.model

import java.util.*

/**
 * Created by Julio.
 */
data class Forecast(
    val dt: Long,
    val main: CurrentWeather,
    val weather: List<Weather>,
    val dt_txt: String
) {
    fun toDayOfWeekLongFormat() : String{
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dt * 1000
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
    }

    fun toDayOfWeekShortFormat() : String{
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dt * 1000
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())
    }
}