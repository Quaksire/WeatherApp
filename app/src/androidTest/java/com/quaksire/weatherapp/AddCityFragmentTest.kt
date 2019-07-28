package com.quaksire.weatherapp

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Julio.
 */
class AddCityFragmentTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun jumpToAddCityFragment() {
        activityTestRule.activity.apply {
            runOnUiThread {
                findNavController(R.id.citiesFragment).navigate(R.id.action_cities_to_add_city)
            }
        }
    }

    @Test
    fun testAddScreenIsDisplayed() {
        onView(withId(R.id.city_auto_complete_text_view)).check(matches(isDisplayed()))
    }
}