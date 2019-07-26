package com.quaksire.network.model

/**
 * Created by Julio.
 */
data class City(
    val id: Long,
    val name: String,
    val coord: Coord,
    val country: String,
    val timezone: Int

)