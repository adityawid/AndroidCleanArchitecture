package com.adityawidayanto.movies.view.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adityawidayanto.core.utils.test.EspressoIdlingResource
import com.adityawidayanto.movies.R
import junit.framework.AssertionFailedError
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun clickItemMovieAndFavorite() {
        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_list)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
        onView(withId(R.id.rv_movie_list)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                10,
                click()
            )
        )
        onView(withId(R.id.titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.titleOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.titleOverview)).check(matches(withText("Overview")))
        try {
            onView(withId(R.id.btn_favorite)).check(matches(isNotChecked()))
            onView(withId(R.id.btn_favorite)).perform(click())
        } catch (e: AssertionFailedError) {
            onView(withId(R.id.btn_favorite)).check(matches(isChecked()))
        }

    }

    @Test
    fun getFavoriteDetailStatusFromApiList() {
        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_list)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
        onView(withId(R.id.rv_movie_list)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                10,
                click()
            )
        )
        onView(withId(R.id.titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.titleOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.titleOverview)).check(matches(withText("Overview")))
        onView(withId(R.id.btn_favorite)).check(matches(isChecked()))

    }

    @Test
    fun swipeLeftAndClickItemTvShow() {
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        onView(withId(R.id.rv_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
        onView(withId(R.id.rv_list)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                10,
                click()
            )
        )
        onView(withId(R.id.titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.titleOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.titleOverview)).check(matches(withText("Overview")))
    }


    @Test
    fun getFavoriteDetailStatusFromLocal() {
        onView(withId(R.id.nav_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withId(R.id.rv_fav_movie_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movie_list)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
        onView(withId(R.id.rv_fav_movie_list)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.titleOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.titleOverview)).check(matches(withText("Overview")))
        onView(withId(R.id.btn_favorite)).check(matches(isChecked()))

    }


    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
}