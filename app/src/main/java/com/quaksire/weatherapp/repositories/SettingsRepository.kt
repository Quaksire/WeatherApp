package com.quaksire.weatherapp.repositories

import androidx.lifecycle.LiveData
import com.quaksire.database.dao.SettingsDao
import com.quaksire.database.entity.SettingsEntity
import kotlinx.coroutines.*

/**
 * Created by Julio.
 */
class SettingsRepository(private val settingsDao: SettingsDao) {

    fun getSettings(): LiveData<List<SettingsEntity>> {
        return settingsDao.getSettings()
    }

    fun getSettingsSync(): List<SettingsEntity> {
        return settingsDao.getSettingsSync()
    }

    fun saveSettings(settingsEntity: SettingsEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            settingsDao.createSettings(settingsEntity)
        }
    }
}