package com.quaksire.weatherapp.di

import com.quaksire.weatherapp.AddCityFragment
import com.quaksire.weatherapp.CitiesFragment
import com.quaksire.weatherapp.CityWeatherFragment
import com.quaksire.weatherapp.SettingsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Julio.
 */
@Singleton
@Component(modules = [RepositoryModule::class])
interface AppComponent {

    fun inject(addCityFragment: AddCityFragment)

    fun inject(citiesFragment: CitiesFragment)

    fun inject(cityWeatherFragment: CityWeatherFragment)

    fun inject(settingsFragment: SettingsFragment)
}