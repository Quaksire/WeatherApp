package com.quaksire.weatherapp.repositories

import com.quaksire.network.service.WeatherService

/**
 * Created by Julio.
 */
class WeatherRepository constructor(
    private val weatherService: WeatherService,
    private val weatherServiceKey: String)  {

    // TODO - Inject it
    val API_KEY = "0b4b04e7cd0342fa7352e106d38d34ae"

    fun getWeather(cityId: Int) {
        this.weatherService.getWeather(cityId, "json", "metric", weatherServiceKey)
    }
}