package com.quaksire.database.module

import android.content.Context
import androidx.room.Room
import com.quaksire.database.AppDatabase
import com.quaksire.database.dao.CityDao
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Julio.
 */
@Module
class DatabaseModule(val context: Context) {

    @Provides
    @Named("DATABASE_NAME")
    fun provideDatabaseName() : String {
        return "CitiesDatabase.db"
    }

    @Provides
    fun provideCityDao(appDatabase: AppDatabase) : CityDao {
        return appDatabase.cityDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@Named("DATABASE_NAME") databaseName: String) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
            .build()
    }

}