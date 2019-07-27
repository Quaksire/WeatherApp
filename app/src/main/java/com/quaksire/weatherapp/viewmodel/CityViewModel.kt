package com.quaksire.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import com.quaksire.weatherapp.repositories.SettingsRepository

/**
 * Created by Julio.
 */
class CityViewModel(settingsRepository: SettingsRepository) : ViewModel() {

    val settings = settingsRepository.getSettings()

}