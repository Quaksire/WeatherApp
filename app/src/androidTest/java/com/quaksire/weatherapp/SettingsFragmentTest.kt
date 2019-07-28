package com.quaksire.weatherapp

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Julio.
 */
class SettingsFragmentTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun jumpToSettingsFragment() {
        activityTestRule.activity.apply {
            runOnUiThread {
                findNavController(R.id.citiesFragment).navigate(R.id.action_cities_to_settings)
            }
        }
    }

    @Test
    fun verifyViewsAreDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.temperatureSwitch)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}