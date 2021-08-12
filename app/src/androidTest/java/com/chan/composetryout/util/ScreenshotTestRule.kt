package com.chan.composetryout.util

import android.util.Log
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Created by chandra-1765$ on 12/08/21$.
 */
class ScreenshotTestRule: TestWatcher() {
    override fun finished(description: Description?) {
        super.finished(description)
        Log.d("ChanLog", "finished: ")
        takeScreenshot("helloWorldNew")
    }
}