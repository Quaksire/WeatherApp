package com.quaksire.weatherapp.repositories

import com.quaksire.network.service.WeatherService

/**
 * Created by Julio.
 */
class WeatherRepository constructor(
    private val weatherService: WeatherService,
    private val weatherServiceKey: String)  {

    fun getWeather(cityId: Int) {
        this.weatherService.getWeather(cityId, "json", "metric", weatherServiceKey)
    }
}