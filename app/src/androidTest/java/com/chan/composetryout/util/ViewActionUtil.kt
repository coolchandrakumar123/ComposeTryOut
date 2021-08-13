package com.chan.composetryout.util

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.test.espresso.UiController

import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

import javax.annotation.Nonnull




/**
 * Created by chandra-1765$ on 13/08/21$.
 */
fun customReplaceText(@Nonnull stringToBeSet: String?): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View>? {
            return ViewMatchers.isAssignableFrom(TextView::class.java)
        }


        override fun getDescription(): String {
            return "Click on a child view with specified id." //no i18n
        }

        override fun perform(uiController: UiController?, view: View) {
            //val clickedView: TextView = view.findViewById(id)
            (view as? TextView?)?.text = stringToBeSet
            Log.d("ChanLog", "perform: InsideTextSet")
        }
    }
}