package com.globant.counter.instrumentedTest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.globant.counter.activity.CountActivity
import com.globant.counter.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@LargeTest
@Config(sdk = [29])
class CountActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(CountActivity::class.java)

    @Test
    fun onPressIncUpdateLabel() {
        onView(withId(R.id.count_btn_inc)).perform(click())
        onView(withId(R.id.count_label)).check(matches(withText(ONE)))
    }

    @Test
    fun onPressDecUpdateLabel() {
        onView(withId(R.id.count_btn_dec)).perform(click())
        onView(withId(R.id.count_label)).check(matches(withText(MINUS_ONE)))
    }

    @Test
    fun onPressResetUpdateLabel() {
        onView(withId(R.id.reset_btn)).perform(click())
        onView(withId(R.id.count_label)).check(matches(withText(ZERO)))
    }

    companion object {
        private const val ZERO = "0"
        private const val MINUS_ONE = "-1"
        private const val ONE = "1"
    }
}
