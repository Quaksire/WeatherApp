package com.quaksire.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Julio.
 */
@Entity(
    tableName = "temperatureSettings"
)
data class SettingsEntity(
    @PrimaryKey @ColumnInfo(name = "settingsId") val settingsId: Int,
    @ColumnInfo(name = "displayTemperatureInFahrenheit") val displayTemperatureInFahrenheit: Boolean
)