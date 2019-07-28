package com.quaksire.weatherapp.repositories

import com.quaksire.database.dao.CityDao
import com.quaksire.database.entity.CityEntity
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

/**
 * Created by Julio.
 */
class CitiesRepositoryTest {

    private lateinit var citiesRepository: CitiesRepository
    private lateinit var cityDao: CityDao

    @Before
    fun setUp() {
        this.cityDao = Mockito.mock(CityDao::class.java)
        this.citiesRepository = CitiesRepository(cityDao)
    }

    @Test
    fun callSearchCityCallsDao() {
        this.citiesRepository.searchCityByName("name")
        verify(cityDao, times(1)).searchCityByName("name")
    }

    @Test
    fun canGetAllCities() {
        this.citiesRepository.getAllCities()
        verify(cityDao, times(1)).getAllCities()
    }

    @Test
    fun canGetAllNonSelectedCities() {
        this.citiesRepository.getAllNonSelectedCities()
        verify(cityDao, times(1)).getAllNonSelectedCities()
    }

    @Test
    fun canInsertNewCityEntity() {
        runBlocking {
            val cityEntity = CityEntity(1, "","",0)
            citiesRepository.addCity(cityEntity)
            verify(cityDao, times(1)).createCity(cityEntity)
        }
    }
}