package com.quaksire.network.model

/**
 * Created by Julio.
 */
data class CurrentWeather(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val sea_level: Double,
    val humidity: Int
)