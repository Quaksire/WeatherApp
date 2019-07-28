package com.quaksire.database.module

import android.content.Context
import com.quaksire.database.AppDatabase
import com.quaksire.database.dao.CityDao
import com.quaksire.database.dao.SettingsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Julio.
 */
@Module
class DatabaseModule(private val context: Context) {

    @Provides
    fun provideCityDao(appDatabase: AppDatabase) : CityDao {
        return appDatabase.cityDao()
    }

    @Provides
    fun provideSettingsDao(appDatabase: AppDatabase) : SettingsDao {
        return appDatabase.settingDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase() : AppDatabase {
        return AppDatabase.getInstance(context)
    }
}