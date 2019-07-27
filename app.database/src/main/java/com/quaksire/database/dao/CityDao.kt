package com.quaksire.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.quaksire.database.entity.CityEntity

/**
 * Created by Julio.
 */
@Dao
interface CityDao {

    @Query("Select * From cities")
    fun getAllCities(): LiveData<List<CityEntity>>

    @Query("Select * From cities Where city_id = :cityId")
    fun getCity(cityId: Int): CityEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createCity(city: CityEntity): Long

    @Delete
    fun deleteCity(city: CityEntity)
}