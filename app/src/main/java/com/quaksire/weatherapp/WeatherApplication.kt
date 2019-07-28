package com.quaksire.weatherapp

import android.app.Application
import com.quaksire.database.module.DatabaseModule
import com.quaksire.network.module.NetworkModule
import com.quaksire.weatherapp.di.AppComponent
import com.quaksire.weatherapp.di.DaggerAppComponent
import com.quaksire.weatherapp.di.RepositoryModule

/**
 * Created by Julio.
 */
class WeatherApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .databaseModule(DatabaseModule(this))
            .networkModule(NetworkModule(Constants.weatherUrl, Constants.weatherKey))
            .repositoryModule(RepositoryModule())
            .build()
    }
}