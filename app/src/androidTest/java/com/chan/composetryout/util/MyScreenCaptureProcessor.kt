package com.chan.composetryout.util

import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.runner.screenshot.BasicScreenCaptureProcessor
import java.io.File


/**
 * Created by chandra-1765$ on 12/08/21$.
 */
class MyScreenCaptureProcessor : BasicScreenCaptureProcessor() {

    init {
        val folder = File(
            getTargetContext().externalCacheDir!!.absolutePath + "/screenshots/"
        )
        if (!folder.exists()) {
            folder.mkdirs()
        }
        /*this.mDefaultScreenshotPath = File(
            File(
                getExternalStoragePublicDirectory(DIRECTORY_PICTURES),
                "${BuildConfig.APPLICATION_ID}/${BuildConfig.BUILD_TYPE}"
            ).absolutePath,
            SCREENSHOTS_FOLDER_NAME
        )*/
        this.mDefaultScreenshotPath = folder
    }

    override fun getFilename(prefix: String): String = prefix

    companion object {
        const val SCREENSHOTS_FOLDER_NAME = "screenshots"
    }
}