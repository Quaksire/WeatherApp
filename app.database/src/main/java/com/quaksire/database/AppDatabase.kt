package com.quaksire.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quaksire.database.dao.CityDao
import com.quaksire.database.entity.CityEntity

/**
 * Created by Julio.
 */
@Database(entities = [CityEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
}