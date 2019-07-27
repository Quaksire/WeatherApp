package com.quaksire.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.quaksire.weatherapp.repositories.SettingsRepository

/**
 * Created by Julio.
 */
class SettingsViewModelFactory(private val settingsRepository: SettingsRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingsViewModel(settingsRepository) as T
    }
}