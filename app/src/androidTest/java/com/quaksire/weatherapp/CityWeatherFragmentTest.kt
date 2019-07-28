package com.quaksire.weatherapp

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Julio.
 */
class CityWeatherFragmentTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun jumpToCityWeatherFragment() {
        activityTestRule.activity.apply {
            runOnUiThread {
                val bundle = Bundle().apply {
                    putInt("cityId", 1)
                    putString("cityName", "City Test")
                }
                findNavController(R.id.citiesFragment).navigate(R.id.action_cities_to_city, bundle)
            }
        }
    }

    @Test
    fun verifyViewsAreDisplayed() {
        onView(withId(R.id.card)).check(matches(isDisplayed()))
        onView(withText("City Test")).check(matches(isDisplayed()))
    }
}