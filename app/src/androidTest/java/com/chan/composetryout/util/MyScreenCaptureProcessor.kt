package com.chan.composetryout.util

import android.os.Environment
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.screenshot.BasicScreenCaptureProcessor
import java.io.File


/**
 * Created by chandra-1765$ on 12/08/21$.
 */
class MyScreenCaptureProcessor : BasicScreenCaptureProcessor() {

    init {
        Log.d("ChanLog", "MyScreenCaptureProcessor: Init")
        InstrumentationRegistry.getInstrumentation().targetContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.let {
            val folder = File(it.absolutePath + "/screenshots/")
            if (!folder.exists()) {
                folder.mkdirs()
            }
            this.mDefaultScreenshotPath = folder
            Log.d("ChanLog", "MyScreenCaptureProcessor: Path- ${folder.absolutePath}")
        }
        /*this.mDefaultScreenshotPath = File(
            File(
                getExternalStoragePublicDirectory(DIRECTORY_PICTURES),
                "${BuildConfig.APPLICATION_ID}/${BuildConfig.BUILD_TYPE}"
            ).absolutePath,
            SCREENSHOTS_FOLDER_NAME
        )*/
    }

    override fun getFilename(prefix: String): String = prefix

    companion object {
        const val SCREENSHOTS_FOLDER_NAME = "screenshots"
    }
}