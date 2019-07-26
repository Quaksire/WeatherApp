package com.quaksire.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Julio.
 */
@Entity(
    tableName = "cities"
)
data class CityEntity(
    @PrimaryKey @ColumnInfo(name = "city_id") val cityId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "country") val country: String
)