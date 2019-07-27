package com.quaksire.weatherapp.repositories

import androidx.lifecycle.LiveData
import com.quaksire.database.dao.CityDao
import com.quaksire.database.entity.CityEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Julio.
 */
class CitiesRepository constructor(private val cityDao: CityDao) {

    fun getAllCities(): LiveData<List<CityEntity>> {
        return cityDao.getAllCities()
    }

    fun addCity(cityEntity: CityEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            cityDao.createCity(cityEntity)
        }
    }
}