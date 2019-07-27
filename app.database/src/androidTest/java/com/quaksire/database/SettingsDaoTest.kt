package com.quaksire.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.quaksire.database.dao.SettingsDao
import com.quaksire.database.entity.SettingsEntity
import com.quaksire.database.utils.LiveDataTestUtil.getValue
import org.junit.*
import org.junit.runner.RunWith

/**
 * Created by Julio.
 */
@RunWith(AndroidJUnit4::class)
class SettingsDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var database: AppDatabase
    lateinit var settingsDao: SettingsDao

    private val settingsEntity = SettingsEntity(1, true)

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        this.database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        this.settingsDao = this.database.settingDao()
    }

    @After
    fun tearDown() {
        this.database.clearAllTables()
        this.database.close()
    }

    @Test
    fun canStoreValue() {
        this.settingsDao.createSettings(settingsEntity)

        val result = getValue(this.settingsDao.getSettings())

        Assert.assertEquals(settingsEntity, result[0])
    }

    @Test
    fun canReplaceValue() {
        this.settingsDao.createSettings(settingsEntity)
        val result = getValue(this.settingsDao.getSettings())
        Assert.assertEquals(settingsEntity, result[0])

        val settingsEntityNewValue = SettingsEntity(1, false)
        this.settingsDao.createSettings(settingsEntityNewValue)
        val resultNewValue = getValue(this.settingsDao.getSettings())
        Assert.assertEquals(settingsEntityNewValue, resultNewValue[0])

    }
}