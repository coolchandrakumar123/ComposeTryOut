package com.chan.composetryout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chan.composetryout.compose.*

class MainActivity : AppCompatActivity() {
    var liveData = MutableLiveData<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //SimpleTwoItems()
            /*SimpleTwoItemWithObserver(
                onClick = {
                    liveData.postValue("${liveData.value},Clicked")
                },
                observer = {
                    liveData = it
                }
            )*/
            //RowWithThreeItem()
            RowColumnThreeItem()
        }
    }

}