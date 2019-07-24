package com.quaksire.database

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.quaksire.database.dao.CityDao
import com.quaksire.database.entity.CityEntity
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Julio.
 */
@RunWith(AndroidJUnit4::class)
class CityDaoTest {
    lateinit var database: AppDatabase
    lateinit var cityDao: CityDao

    private val cityLondonEntity = CityEntity("CityId", "London", "UK")

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        this.database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        this.cityDao = this.database.cityDao()
    }

    @After
    fun tearDown() {
        this.database.clearAllTables()
        this.database.close()
    }

    @Test
    fun canInsertCity() {
        val result = this.cityDao.createCity(cityLondonEntity)

        Assert.assertTrue(result > 0)
    }

    @Test
    fun canDeleteCity() {
        this.cityDao.createCity(cityLondonEntity)

        this.cityDao.deleteCity(cityLondonEntity)

        val result = this.cityDao.getCity(cityLondonEntity.cityId)

        Assert.assertEquals(null, result)
    }

    @Test
    fun canQueryCityById() {
        this.cityDao.createCity(cityLondonEntity)

        val result = this.cityDao.getCity(cityLondonEntity.cityId)
        Assert.assertEquals(cityLondonEntity, result)
    }

    @Test
    fun canQueryAllCities() {
        this.cityDao.createCity(cityLondonEntity)
        val result = this.cityDao.getAllCities()
        Assert.assertEquals(cityLondonEntity, result[0])
    }

    @Test
    fun canQueryAllCitiesReturnEmptyListIfNoCitiesExists() {
        val result = this.cityDao.getAllCities()
        Assert.assertEquals(0, result.size)
    }
}