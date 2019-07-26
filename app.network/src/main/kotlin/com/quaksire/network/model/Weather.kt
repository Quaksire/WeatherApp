package com.quaksire.network.model

/**
 * Created by Julio.
 */
data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)