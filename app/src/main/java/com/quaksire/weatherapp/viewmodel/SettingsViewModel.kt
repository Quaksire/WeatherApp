package com.quaksire.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import com.quaksire.weatherapp.repositories.SettingsRepository

/**
 * Created by Julio.
 */
class SettingsViewModel(settingsRepository: SettingsRepository) : ViewModel() {

    val displayFarenheit = settingsRepository.getSettings()
}