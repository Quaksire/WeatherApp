package com.quaksire.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.quaksire.database.dao.CityDao
import com.quaksire.database.entity.CityEntity
import com.quaksire.database.utils.LiveDataTestUtil.getValue
import org.junit.*
import org.junit.runner.RunWith

/**
 * Created by Julio.
 */
@RunWith(AndroidJUnit4::class)
class CityDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var cityDao: CityDao

    private val cityLondonEntity = CityEntity(1234, "London", "UK", 1)

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

        val result = this.cityDao.getCity(cityLondonEntity.id)

        Assert.assertEquals(null, result)
    }

    @Test
    fun canQueryCityById() {
        this.cityDao.createCity(cityLondonEntity)

        val result = this.cityDao.getCity(cityLondonEntity.id)
        Assert.assertEquals(cityLondonEntity, result)
    }

    @Test
    fun canQueryAllCities() {
        this.cityDao.createCity(cityLondonEntity)
        val result = getValue(this.cityDao.getAllCities())

        Assert.assertEquals(cityLondonEntity, result[0])
    }

    @Test
    fun canQueryAllCitiesReturnEmptyListIfNoCitiesExists() {
        val result = getValue(this.cityDao.getAllCities())
        Assert.assertEquals(0, result.size)
    }

    @Test
    fun canQueryAllNonSelectedCities() {
        cityLondonEntity.selected = 0
        this.cityDao.createCity(cityLondonEntity)
        val result = getValue(this.cityDao.getAllNonSelectedCities())
        Assert.assertEquals(1, result.size)
    }

    @Test
    fun selectedCityIsNotReturnedOnNonSelectedCities() {
        this.cityDao.createCity(cityLondonEntity)
        val result = getValue(this.cityDao.getAllNonSelectedCities())
        Assert.assertEquals(0, result.size)
    }
}