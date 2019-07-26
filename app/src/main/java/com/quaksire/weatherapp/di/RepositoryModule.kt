package com.quaksire.weatherapp.di

import com.quaksire.database.dao.CityDao
import com.quaksire.database.module.DatabaseModule
import com.quaksire.network.module.NetworkModule
import com.quaksire.network.service.WeatherService
import com.quaksire.weatherapp.repositories.CitiesRepository
import com.quaksire.weatherapp.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by Julio.
 */
@Module(includes = [NetworkModule::class, DatabaseModule::class])
class RepositoryModule {

    @Provides
    fun provideCitiesRepository(cityDao: CityDao): CitiesRepository {
        return CitiesRepository(cityDao)
    }

    @Provides
    fun provideWeatherRepository(
        @Named("WEATHER_KEY") weatherKey: String,
        weatherService: WeatherService): WeatherRepository {
        return WeatherRepository(weatherService, weatherKey)
    }
}