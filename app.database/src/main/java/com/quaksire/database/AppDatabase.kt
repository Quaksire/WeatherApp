package com.quaksire.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.quaksire.database.dao.CityDao
import com.quaksire.database.dao.SettingsDao
import com.quaksire.database.entity.CityEntity
import com.quaksire.database.entity.SettingsEntity
import com.quaksire.database.worker.CitiesSeed

/**
 * Created by Julio.
 */
@Database(entities = [CityEntity::class, SettingsEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun settingDao(): SettingsDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        private const val DATABASE_NAME: String = "CitiesDatabase2.db"

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context, DATABASE_NAME).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context, databaseName: String): AppDatabase {
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
}