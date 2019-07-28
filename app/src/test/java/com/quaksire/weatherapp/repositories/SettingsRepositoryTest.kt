package com.quaksire.weatherapp.repositories

import com.quaksire.database.dao.SettingsDao
import com.quaksire.database.entity.SettingsEntity
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

/**
 * Created by Julio.
 */
class SettingsRepositoryTest {

    private lateinit var settingsRepository: SettingsRepository

    private lateinit var settingsDao: SettingsDao

    @Before
    fun setUp() {
        this.settingsDao = Mockito.mock(SettingsDao::class.java)
        this.settingsRepository = SettingsRepository(settingsDao)
    }

    @Test
    fun canGetCurrentSettings() {
        this.settingsRepository.getSettings()
        verify(this.settingsDao, times(1)).getSettings()
    }

    @Test
    fun canGetSettingsSync() {
        this.settingsRepository.getSettingsSync()
        verify(this.settingsDao, times(1)).getSettingsSync()
    }

    @Test
    fun canUpdateSettings() {
        runBlocking {
            val settingsEntity = SettingsEntity(1, false)
            settingsRepository.saveSettings(settingsEntity)
            verify(settingsDao, times(1)).createSettings(settingsEntity)
        }
    }
}