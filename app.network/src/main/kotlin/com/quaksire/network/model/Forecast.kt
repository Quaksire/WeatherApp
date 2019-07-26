package com.quaksire.network.model

/**
 * Created by Julio.
 */
data class Forecast(
    val dt: Long,
    val main: CurrentWeather,
    val weather: List<Weather>
)