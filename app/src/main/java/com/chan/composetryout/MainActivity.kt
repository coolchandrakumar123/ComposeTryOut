package com.chan.composetryout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.unit.dp
import com.chan.composetryout.compose.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContent {
            Column {
                Row {
                    Button(onClick = {
                        navigateTo(Screen.LIST)
                    }, modifier = Modifier.padding(all = 4.dp)) {
                        Text(text = "List")
                    }
                    Button(onClick = {
                        navigateTo(Screen.ANDROIDVIEW)
                    }, modifier = Modifier.padding(all = 4.dp)) {
                        Text(text = "AndroidView")
                    }
                    Button(onClick = {
                        navigateTo(Screen.HOME)
                    }, modifier = Modifier.padding(all = 4.dp)) {
                        Text(text = "Home")
                    }
                }

                inflateScreens()
            }
        }
    }

    @Composable
    private fun inflateScreens() {
        when(AppStatus.currentScreen) {
            Screen.HOME -> {
                inflateAdapterList()
            }
            Screen.LIST -> {
                //inflateAndroidView()
                inflateAdapterList()
            }
            Screen.ANDROIDVIEW -> MarkDownParser()
        }
    }
}