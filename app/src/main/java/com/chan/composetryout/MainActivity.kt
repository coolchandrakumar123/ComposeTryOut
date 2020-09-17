package com.chan.composetryout

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.setPadding
import androidx.lifecycle.MutableLiveData
import com.chan.composetryout.compose.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContent {
            //inflateNavigateScreen()
            //helloWorld()
            //androidViewColor()
            MarkDownParser()
        }
    }

    val themeColor = Colors(
        primary = Color.Red,
        primaryVariant = Color.Red,
        secondary = Color.Red,
        secondaryVariant = Color.Red,
        background = Color.Red,
        surface = Color.Red,
        error = Color.Red,
        onPrimary = Color.Red,
        onSecondary = Color.Red,
        onBackground = Color.Red,
        onSurface = Color.Red,
        onError = Color.Red,
        isLight = true
    )

    @Composable
    private fun androidViewColor() {
        AndroidView(viewBlock = { context ->
            android.widget.EditText(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }
        }, update = { editText ->
            //editText.setBackgroundColor(themeColor.background.toArgb())
            editText.setTextColor(themeColor.background.toArgb())
        })
        /*MaterialTheme(colors = themeColor) {

        }*/
    }

    @Composable
    private fun helloWorld() {
        MaterialTheme(colors = themeColor) {
            Column {
                Column(
                    modifier = Modifier.drawShadow(shape = CircleShape, elevation = 1.dp)
                        .background(color = MaterialTheme.colors.background)
                        .padding(16.dp)
                ) {
                    Text(text = "Hello World!!!")
                }

                AndroidView(viewBlock = { context ->
                    LinearLayout(context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        orientation = LinearLayout.VERTICAL
                        this.addView(android.widget.EditText(context).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                            setHint("Edit Now")
                            isCursorVisible = true
                        })
                        val checkBox = android.widget.CheckBox(context).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                            setPadding(40)
                        }

                        this.addView(android.widget.Button(context).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                            text = "Clickable"
                            setOnClickListener {
                                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                                checkBox.isChecked = true
                            }
                            //Color. MaterialTheme.colors.background.value
                            setBackgroundColor(android.graphics.Color.BLUE)
                            //setBackgroundColor(android.graphics.Color.parseColor("#${MaterialTheme.colors.background.value}"))
                        })

                        this.addView(checkBox)

                        this.addView(android.widget.CheckBox(context).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                            setPadding(40)
                        })

                    }

                }, modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colors.background))
            }
        }


    }

    @Composable
    private fun inflateNavigateScreen() {
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

    val test = MutableLiveData<Any>()
    val localVisibilityMap = HashMap<String, MutableLiveData<Boolean>>()

    @Composable
    private fun inflateScreens() {
        when (AppStatus.currentScreen) {
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
                            it.postValue(!(it.value ?: true))
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
                inflateObservePattern(3) {
                    val liveData = MutableLiveData<Boolean>()
                    localVisibilityMap[it] = liveData
                    liveData
                }
                inflateObservePattern(4) {
                    val liveData = MutableLiveData<Boolean>()
                    localVisibilityMap[it] = liveData
                    liveData
                }
            }
        }
    }
}