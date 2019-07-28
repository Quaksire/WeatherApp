package com.quaksire.weatherapp

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

/**
 * Created by Julio.
 */
class NavigationScenarioTest : BaseTest() {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)


    /**
     * This is a full integration test that cover all screens in a happy path scenario
     * Animations must be disabled on the device where you run this test.
     */
    @Test
    fun canDisplayWeatherInLondonTest() {
        setResponse(200, "response_metric_200.json")

        activityTestRule.launchActivity(Intent())

        onView(withText(R.string.locations)).check(matches(isDisplayed()))
        onView(withId(R.id.fab)).perform(click())

        // Navigation to add location
        onView(withText(R.string.add_location)).check(matches(isDisplayed()))
        onView(withText(R.id.city_auto_complete_text_view)).check(matches(isDisplayed()))
        onView(withText(R.id.city_auto_complete_text_view)).perform(replaceText("london"))
        onView(withText("City of London")).perform(click())
        onView(withId(R.id.add_location_menu_save)).perform(click())

        // Navigation to Locations
        onView(withText("City of London")).check(matches(isDisplayed()))
        onView(withText("City of London")).perform(click())

        // Navigation to Weather
        onView(withText("City of London")).check(matches(isDisplayed()))
        onView(withId(R.id.card)).check(matches(isDisplayed()))

        onView(withId(R.id.currentTemperature)).check(matches(isDisplayed()))
        onView(withId(R.id.currentTemperature)).check(matches(withText("10")))

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())

        // Navigate to Locations
        onView(withId(R.id.cities_recycler_view)).check(matches(isDisplayed()))

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        onView(withText(R.id.settingsFragment)).perform(click())

        // Navigation to Settings
        onView(withId(R.id.temperatureSwitch)).check(matches(isDisplayed()))
        onView(withId(R.id.temperatureSwitch)).perform(click())

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())

        // Navigation to Weather
        setResponse(200, "response_imperial_200.json")
        onView(withText("City of London")).check(matches(isDisplayed()))
        onView(withId(R.id.card)).check(matches(isDisplayed()))

        onView(withId(R.id.currentTemperature)).check(matches(isDisplayed()))
        onView(withId(R.id.currentTemperature)).check(matches(withText("280")))

    }
}