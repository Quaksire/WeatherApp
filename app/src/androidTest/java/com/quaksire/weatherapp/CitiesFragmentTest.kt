package com.quaksire.weatherapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

/**
 * Created by Julio.
 */
class CitiesFragmentTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun verifyViewsAreDisplayed() {
        onView(withId(R.id.cities_recycler_view)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.fab)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}