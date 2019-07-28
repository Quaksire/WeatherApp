package com.quaksire.database.worker

import android.content.Context
import android.util.Log
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

                    var cityEntities: List<CityEntity> = Gson().fromJson(jsonReader, citiesType)
                    cityEntities = cityEntities.map {
                        CityEntity(it.id, it.name, it.country, 0)
                    }

                    val cityDao = AppDatabase.getInstance(applicationContext).cityDao()
                    cityDao.insertAll(cityEntities)
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }
}