package com.quaksire.network.model

/**
 * Created by Julio.
 */
data class Response(
    val cod: String,
    val message: String,
    val city: City,
    val cnt: Int,
    val list: List<Forecast>
)