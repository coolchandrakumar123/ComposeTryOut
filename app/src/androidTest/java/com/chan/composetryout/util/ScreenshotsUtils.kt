package com.chan.composetryout.util

import android.util.Log
import androidx.test.runner.screenshot.Screenshot
import java.io.IOException

/**
 * Created by chandra-1765$ on 12/08/21$.
 */

fun takeScreenshot(screenShotName: String) {
    Log.d("ChanLog", "Taking screenshot of '$screenShotName'")
    val screenCapture = Screenshot.capture()
    val processors = setOf(MyScreenCaptureProcessor())
    try {
        screenCapture.apply {
            name = screenShotName
            process(processors)
        }
        Log.d("ChanLog", "Screenshot taken")
    } catch (ex: IOException) {
        Log.e("ChanLog", "Could not take a screenshot", ex)
    }
}