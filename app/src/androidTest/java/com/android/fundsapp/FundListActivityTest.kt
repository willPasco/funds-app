package com.android.fundsapp

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.fundsapp.fundslist.FundsListActivity
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FundListActivityTest {
    private lateinit var idlingResource: IdlingResource

    @get:Rule
    val activityIntentTestRule = IntentsTestRule(FundsListActivity::class.java)

    @Before
    fun registerIdleResource() {
        idlingResource = activityIntentTestRule.activity.getIdlingResource()
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun unregisterIdleResource() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }


    @Test
    fun checkInitialRequest() {

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))

        val recyclerView =
            activityIntentTestRule.activity.findViewById<RecyclerView>(R.id.recycler_view)
        val count = recyclerView.adapter!!.itemCount

        assert(count > 0)
    }

    @Test
    fun checkItemClick() {

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))

        val constraintLayout = onView(
            Matchers.allOf(
                withId(R.id.layout_container),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recycler_view),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        constraintLayout.perform(ViewActions.click())

        onView(withText("Fake open detail.")).inRoot(withDecorView(not(`is`(activityIntentTestRule.activity.window.decorView)))).check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    @Test
    fun checkNoConnectionRequest() {
        onView(withText(R.string.error_no_connection)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

}
