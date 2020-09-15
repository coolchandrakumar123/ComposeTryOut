package com.chan.composetryout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.chan.composetryout.compose.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContent{
            Column(modifier = Modifier.drawShadow(shape = CircleShape, elevation = 1.dp)) {
                /*Row {
                    Button(onClick = {
                        navigateTo(Screen.HOME)
                    }, modifier = Modifier.padding(all = 4.dp)) {
                        Text(text = "Home")
                    }
                    Button(onClick = {
                        navigateTo(Screen.LIST)
                    }, modifier = Modifier.padding(all = 4.dp)) {
                        Text(text = "List")
                    }
                    Button(onClick = {
                        navigateTo(Screen.OBSERVE_PATTERN)
                    }, modifier = Modifier.padding(all = 4.dp)) {
                        Text(text = "Observe")
                    }
                }*/
                /*Row {
                    Button(onClick = {
                        navigateTo(Screen.ANDROIDVIEW)
                    }, modifier = Modifier.padding(all = 4.dp)) {
                        Text(text = "AndroidView")
                    }
                    Button(onClick = {
                        test.postValue("Modified Thread Content")
                    }, modifier = Modifier.padding(all = 4.dp)) {
                        Text(text = "Data-WebView")
                    }
                }*/
                navigateTo(Screen.OBSERVE_PATTERN)
                inflateScreens()
            }

        }
    }

    val test = MutableLiveData<Any>()
    val localVisibilityMap = HashMap<String, MutableLiveData<Boolean>>()
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
            Screen.ANDROIDVIEW -> MarkDownParser() {
                test
            }
            Screen.OBSERVE_PATTERN -> {
                Row {
                    Button(onClick = {
                        val key = "description-3"
                        localVisibilityMap[key]?.let {
                            it.postValue(!(it.value?:true))
                        }
                    }, modifier = Modifier.padding(all = 4.dp)) {
                        Text(text = "ExpandCollapse")
                    }
                    /*Button(onClick = {
                        localVisibilityMap["title-2"]?.postValue(false)
                    }, modifier = Modifier.padding(all = 4.dp)) {
                        Text(text = "Hide")
                    }*/
                }

                inflateObservePattern(1) {
                    val liveData = MutableLiveData<Boolean>()
                    localVisibilityMap[it] = liveData
                    liveData
                }
                inflateObservePattern(2) {
                    val liveData = MutableLiveData<Boolean>()
                    localVisibilityMap[it] = liveData
                    liveData
                }
                inflateObservePattern(3){
                    val liveData = MutableLiveData<Boolean>()
                    localVisibilityMap[it] = liveData
                    liveData
                }
                inflateObservePattern(4){
                    val liveData = MutableLiveData<Boolean>()
                    localVisibilityMap[it] = liveData
                    liveData
                }
            }
        }
    }
}