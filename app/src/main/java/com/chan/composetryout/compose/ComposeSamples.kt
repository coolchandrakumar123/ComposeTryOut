package com.chan.composetryout.compose

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData

/**
 * Created by chandra-1765$ on 05/08/21$.
 */

@Composable
fun SimpleTwoItemWithObserver(observer: (MutableLiveData<String>) -> Unit, onClick: () -> Unit) {
    Column {
        //var valueText = "Chan"
        SingleButton(onClick)
        SingleTextWithObserver(observer)
    }
}

@Composable
fun SingleTextWithObserver(observer: (MutableLiveData<String>) -> Unit) {
    val valueObserver = MutableLiveData<String>()
    observer(valueObserver)
    var valueText by remember { mutableStateOf("Chan") }
    valueObserver.observe(LocalLifecycleOwner.current) {
        valueText = it
    }
    if(valueText.isNotEmpty()) {
        SingleText(valueText)
    }
}

@Composable
fun SingleText(valueText: String) {
    Text(
        text = valueText,
        style = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .testTag("singleText")
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Preview
@Composable
fun SimpleTwoItems() {
    Column {
        var valueText by remember { mutableStateOf("Chan") }
        //var valueText = "Chan"
        val context = LocalContext.current
        SingleButton {
            Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show()
            valueText += "Clicked"
        }
        if (valueText.isNotEmpty()) {
            SingleText(valueText)
        }
    }
}

@Composable
fun SingleButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Click",
            style = TextStyle(fontSize = 20.sp, color = Color.White),
            modifier = Modifier
                .background(color = Color.Blue, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
                .clickable {
                    Log.d("ChanLog", "SingleButton: Clicked")
                    onClick()
                }
        )
    }
}