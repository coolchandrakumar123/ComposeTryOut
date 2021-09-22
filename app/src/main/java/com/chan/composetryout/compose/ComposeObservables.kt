package com.chan.composetryout.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData

/**
 * Created by chandra-1765$ on 22/09/21$.
 */

@Composable
fun CheckObserverCase() {
    Column(
        modifier = Modifier.padding(all = 16.dp)
    ) {
        var count = 1
        val liveDataMap = HashMap<String, MutableLiveData<String>>()
        val stateDataMap = HashMap<String, MutableState<String>>()
        Button(
            onClick = {
                liveDataMap[count.toString()]?.postValue("Update via LiveData : ${System.currentTimeMillis()}")
                stateDataMap[count.toString()]?.value = "Update via MutableState : ${System.currentTimeMillis()}"
                count++
            }
        ) {
            Text(text = "Update Data")
        }
        TextWithLiveDataObserver("1") { key, liveData ->
            liveDataMap[key] = liveData
        }
        TextWithLiveDataObserver("2") { key, liveData ->
            liveDataMap[key] = liveData
        }
        val one = mutableStateOf("One")
        stateDataMap["1"] = one
        TextWithStateObserver(one)
        val two = mutableStateOf("Two")
        TextWithStateObserver(two)
        stateDataMap["2"] = two
    }
}

@Composable
fun TextWithLiveDataObserver(key: String, observer: (String, MutableLiveData<String>) -> Unit) {
    val valueObserver = MutableLiveData<String>()
    observer(key, valueObserver)
    var valueText by remember { mutableStateOf("Chan") }
    valueObserver.observe(LocalLifecycleOwner.current) {
        valueText = it
    }
    if(valueText.isNotEmpty()) {
        SingleText(valueText)
    }
}

@Composable
fun TextWithStateObserver(valueState: MutableState<String>) {
    val valueText by remember { valueState }
    if(valueText.isNotEmpty()) {
        SingleText(valueText)
    }
}