package com.quaksire.weatherapp.utils

import android.content.Context

/**
 * Created by Julio.
 */
object ResourcesUtils {
    @Throws(Exception::class)
    fun getStringFromFile(context: Context, filePath: String): String {
        return context.assets.open(filePath).bufferedReader().use{
            it.readText()
        }
    }
}