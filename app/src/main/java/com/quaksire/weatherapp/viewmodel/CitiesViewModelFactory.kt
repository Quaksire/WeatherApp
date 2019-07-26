package com.quaksire.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.quaksire.weatherapp.repositories.CitiesRepository

/**
 * Created by Julio.
 */
class CitiesViewModelFactory(private val repository: CitiesRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CitiesViewModel(repository) as T
    }
}