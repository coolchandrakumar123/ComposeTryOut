package com.chan.composetryout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import com.chan.composetryout.compose.MarkDownParser
import com.chan.composetryout.compose.inflateAdapterList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val context = this
        setContent {
            //inflateAndroidView()
            MarkDownParser()
            //inflateAdapterList()
        }
    }
}