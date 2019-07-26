package com.quaksire.network.module

import com.google.gson.Gson
import com.quaksire.network.service.WeatherService

import java.util.concurrent.TimeUnit

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

/**
 * Created by Julio.
 */
@Module
class NetworkModule(private val url: String, private val key: String) {

    @Provides
    @Named("WEATHER_KEY")
    fun provideKey(): String {
        return key
    }

    /**
     * Create new RxJavaCallAdapter
     * @return RxJavaCallAdapter instance
     */
    @Provides
    fun provideRxJavaCallAdapterFactory(): RxJavaCallAdapterFactory {
        return RxJavaCallAdapterFactory.create()
    }

    /**
     * Create new Gson object to be injected
     * @return new Gson instance
     */
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    /**
     * Create new GsonConverterFactory
     * @param gson Gson instance
     * @return new GsonConverterFactory
     */
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    /**
     * Create Http client with the next specifications
     * Connection timeout: 30 seconds
     * Read timeout: 5 seconds
     * Write timeout: 5 seconds
     * @return Http client
     */
    @Provides
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Create new Retrofit client
     * @param client Http client
     * @param gsonConverterFactory Json converter to model
     * @param rxJavaCallAdapterFactory rxJava adapter
     * @return Retrofit client
     */
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJavaCallAdapterFactory: RxJavaCallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(this.url)
            .addCallAdapterFactory(rxJavaCallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideWeatherServices(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }
}