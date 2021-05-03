package com.adityawidayanto.movies.view.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adityawidayanto.movies.R
import org.junit.Rule
import org.junit.Test

class SplashActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(SplashActivity::class.java)

    @Test
    fun loadSplashAndMovieList() {
        onView(withId(R.id.splash_text_view))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.splash_text_view)).check(matches(withText("Movie Tv SHow")))
        Thread.sleep(10000)
        onView(withId(R.id.rv_movie_list))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

    }

    @Test
    fun clickItem() {
        onView(withId(R.id.splash_text_view))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.splash_text_view)).check(matches(withText("Movie Tv SHow")))
        Thread.sleep(10000)
        onView(withId(R.id.rv_movie_list)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie_list)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
        onView(withId(R.id.rv_movie_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                10,
                ViewActions.click()
            )
        )
        Thread.sleep(1000)
        onView(withId(R.id.titleDetail)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.date)).check(matches(ViewMatchers.isDisplayed()))
    }
}