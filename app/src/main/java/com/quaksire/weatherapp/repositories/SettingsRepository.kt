package com.quaksire.weatherapp.repositories

import androidx.lifecycle.LiveData
import com.quaksire.database.dao.SettingsDao
import com.quaksire.database.entity.SettingsEntity

/**
 * Created by Julio.
 */
class SettingsRepository(private val settingsDao: SettingsDao) {

    fun getSettings(): LiveData<List<SettingsEntity>> {
        return settingsDao.getSettings()
    }

    fun saveSettings(settingsEntity: SettingsEntity) {
        settingsDao.createSettings(settingsEntity)
    }
}