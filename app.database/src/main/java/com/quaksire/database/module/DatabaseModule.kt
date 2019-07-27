package com.quaksire.database.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.quaksire.database.AppDatabase
import com.quaksire.database.dao.CityDao
import com.quaksire.database.dao.SettingsDao
import com.quaksire.database.worker.CitiesSeed
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Julio.
 */
@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Named("DATABASE_NAME")
    fun provideDatabaseName() : String {
        return "CitiesDatabase.db"
    }

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
    fun provideAppDatabase(@Named("DATABASE_NAME") databaseName: String) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val request = OneTimeWorkRequestBuilder<CitiesSeed>().build()
                    WorkManager.getInstance(context).enqueue(request)
                }
            })
            .build()
    }

}