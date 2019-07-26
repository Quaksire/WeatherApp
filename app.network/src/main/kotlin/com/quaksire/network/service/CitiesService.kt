package com.quaksire.network.service

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Julio.
 */
interface CitiesService {
    @GET("v1/geo/cities")
    fun findCitiesByPrefix(@Query("limit") limit: Int = 5,
                   @Query("offset") offset: Int = 0,
                   @Query("namePrefix") namePrefix: String)
}