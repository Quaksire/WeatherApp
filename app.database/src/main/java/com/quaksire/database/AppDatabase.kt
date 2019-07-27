package com.quaksire.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quaksire.database.dao.CityDao
import com.quaksire.database.dao.SettingsDao
import com.quaksire.database.entity.CityEntity
import com.quaksire.database.entity.SettingsEntity

/**
 * Created by Julio.
 */
@Database(entities = [CityEntity::class, SettingsEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun settingDao(): SettingsDao
}