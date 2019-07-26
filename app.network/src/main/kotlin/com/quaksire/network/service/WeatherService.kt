package com.quaksire.network.service

import com.quaksire.network.model.Response
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Single

/**
 * Created by Julio.
 */
interface WeatherService {
    @GET("data/2.5/forecast")
    fun getWeather(
        @Query("id") cityId: Int,
        @Query("mode") mode: String,
        @Query("units") units: String,
        @Query("APPID") key: String
    ): Single<Response>
}