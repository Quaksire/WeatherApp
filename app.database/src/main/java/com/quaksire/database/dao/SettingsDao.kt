package com.quaksire.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.quaksire.database.entity.SettingsEntity

/**
 * Created by Julio.
 */
@Dao
interface SettingsDao {
    @Query("Select * From temperatureSettings")
    fun getSettings(): LiveData<List<SettingsEntity>>

    @Query("Select * From temperatureSettings")
    fun getSettingsSync(): List<SettingsEntity>

    @Insert(onConflict = REPLACE)
    fun createSettings(settings: SettingsEntity): Long
}