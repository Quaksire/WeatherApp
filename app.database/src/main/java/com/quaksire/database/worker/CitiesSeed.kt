package com.quaksire.database.worker

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.quaksire.database.AppDatabase
import com.quaksire.database.entity.CityEntity
import kotlinx.coroutines.coroutineScope

/**
 * Created by Julio.
 */
class CitiesSeed(context: Context, workerParams: WorkerParameters)
    : CoroutineWorker(context, workerParams) {

    private val TAG by lazy { CitiesSeed::class.java.simpleName }

    override suspend fun doWork(): Result = coroutineScope {

        try {
            applicationContext.assets.open("cities.json").use { inputStream ->
                inputStream.reader().use { jsonReader ->
                    val citiesType = object : TypeToken<List<CityEntity>>() {}.type

                    val cityEntities: List<CityEntity> = Gson().fromJson(jsonReader, citiesType)

                    val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "CitiesDatabase.db")
                        .build()

                    database.cityDao().insertAll(cityEntities)
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }
}