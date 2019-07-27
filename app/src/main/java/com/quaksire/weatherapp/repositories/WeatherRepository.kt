package com.quaksire.weatherapp.repositories

import com.quaksire.network.model.Response
import com.quaksire.network.service.WeatherService
import io.reactivex.Single

/**
 * Created by Julio.
 */
class WeatherRepository constructor(
    private val weatherService: WeatherService,
    private val weatherServiceKey: String)  {

    fun getWeather(cityId: Int, useFarengheit: Boolean) : Single<Response> {
        val measure = if (useFarengheit) "imperial" else "metric"
        return this.weatherService.getWeather(cityId, "json", measure, weatherServiceKey)
    }
}