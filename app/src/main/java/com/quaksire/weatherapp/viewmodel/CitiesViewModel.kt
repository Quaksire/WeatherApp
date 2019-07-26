package com.quaksire.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import com.quaksire.weatherapp.repositories.CitiesRepository

/**
 * Created by Julio.
 */
class CitiesViewModel internal constructor(citiesRepository: CitiesRepository) : ViewModel() {
    val cities = citiesRepository.getAllCities()
}