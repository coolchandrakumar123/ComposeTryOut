package com.chan.composetryout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Recomposer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.ui.core.Modifier
import androidx.ui.core.drawShadow
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.dp
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