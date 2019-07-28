package com.quaksire.weatherapp.repositories

import com.quaksire.network.service.WeatherService
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by Julio.
 */
class WeatherRepositoryTest {

    private lateinit var weatherRepository: WeatherRepository

    private lateinit var weatherService: WeatherService

    @Before
    fun setUp() {
        this.weatherService = mock(WeatherService::class.java)
        this.weatherRepository = WeatherRepository(weatherService, "")
    }

    @Test
    fun canCallWeatherServicesUsingMetrics() {
        this.weatherRepository.getWeather(1, false)
        verify(this.weatherService, times(1)).getWeather(1, "json", "metric", "")
    }

    @Test
    fun canCallWeatherServicesUsingImperial() {
        this.weatherRepository.getWeather(1, true)
        verify(this.weatherService, times(1)).getWeather(1, "json", "imperial", "")
    }
}