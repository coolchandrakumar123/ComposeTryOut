package com.chan.composetryout

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.chan.composetryout.compose.SimpleTwoItems
import com.chan.composetryout.util.takeScreenshot
import org.junit.Rule
import org.junit.Test

/**
 * Created by chandra-1765$ on 18/08/21$.
 */
class ComposeMainActivityTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    /*@get:Rule
    val composeTestRule = createAndroidComposeRule<ComposeMainActivity>()*/
    // an activity

    @Test
    fun myTest() {
        composeTestRule.setContent {
            SimpleTwoItems()
        }
        composeTestRule.onNodeWithText("Click").assertExists()
        //composeTestRule.onNodeWithText("Chan").performTextReplacement("ViaTesting")
        composeTestRule.onNodeWithText("Click").performClick()
        composeTestRule.onNodeWithTag("singleText").assertTextContains("ChanClicked")
        takeScreenshot()
        Thread.sleep(2000)
    }
}