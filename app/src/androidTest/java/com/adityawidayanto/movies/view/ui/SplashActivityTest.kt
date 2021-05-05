package com.adityawidayanto.movies.view.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adityawidayanto.core.utils.test.EspressoIdlingResource
import com.adityawidayanto.movies.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(SplashActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadSplashAndMovieList() {
        onView(withId(R.id.splash_text_view))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.splash_text_view)).check(matches(withText("Movie Tv SHow")))
    }


    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

}