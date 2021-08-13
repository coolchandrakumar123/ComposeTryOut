package com.chan.composetryout

import android.util.Log
import androidx.test.rule.ActivityTestRule
import org.junit.Rule

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText

import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chan.composetryout.util.ScreenshotTestRule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by chandra-1765$ on 12/08/21$.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    val screenshotTestRule = ScreenshotTestRule()

    /*@get:Rule
    val chainRule: TestRule = RuleChain
        .outerRule(mActivityRule)
        .around(ScreenshotTestRule())*/

    @Test
    fun ensureTextChangesWork() {
        // Type text and then press the button.
        Log.d("ChanLog", "ensureTextChangesWork: Starting")
        onView(withId(R.id.helloWorldLabel))
            .perform(typeText("ENTER CHANDRAKUMAR"))
        Log.d("ChanLog", "ensureTextChangesWork: NextLine")
        Thread.sleep(1000)
        Log.d("ChanLog", "ensureTextChangesWork: Completed")
        onView(withId(R.id.helloWorldLabel))
            .perform(clearText())
        onView(withId(R.id.helloWorldLabel))
            .perform(typeText("EXIT CHANDRAKUMAR"))
        Thread.sleep(1000)
        // Check that the text was changed.
        onView(withId(R.id.helloWorldLabel)).check(matches(withText("EXIT CHANDRAKUMAR")))
    }
}