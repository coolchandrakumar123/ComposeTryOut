package com.chan.composetryout.compose

import androidx.compose.Composable
import androidx.compose.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.livedata.observeAsState
import androidx.ui.text.TextStyle
import androidx.ui.unit.dp
import androidx.ui.unit.sp

/**
 * Created by chandra-1765$ on 09/07/20$.
 */

val visibility = MutableLiveData<Boolean>()

@Composable
fun inflateObservePattern1(localVisibility: LiveData<Pair<String,Boolean>>) {
    Column(Modifier.padding(16.dp)) {
        val data = localVisibility.observeAsState()
        data.value?.let {
            if(it.first == "title") {
                if(it.second) {
                    Text(text = "Title", style = TextStyle(fontSize = 20.sp))
                }
            } else {
                Text(text = "Title", style = TextStyle(fontSize = 20.sp))
            }
        }?: run {
            Text(text = "Title", style = TextStyle(fontSize = 20.sp))
        }
        Description(localVisibility)
    }
}

@Composable
private fun Description(localVisibility: LiveData<Pair<String,Boolean>>) {
    val data = localVisibility.observeAsState()
    data.value?.let {
        if(it.first == "description") {
            if(it.second) {
                Text(text = "Description", style = TextStyle(fontSize = 20.sp))
            }
        } else {
            Text(text = "Description", style = TextStyle(fontSize = 20.sp))
        }
    }?: run {
        Text(text = "Description", style = TextStyle(fontSize = 20.sp))
    }
}

@Composable
fun inflateObservePattern(index: Int, observer: (String) -> LiveData<Boolean>) {
    Column(Modifier.padding(16.dp)) {
        TextData(index, "title", "Title-$index", observer)
        TextData(index, "description", "Description", observer)
    }
}

@Composable
private fun TextData(index: Int, key: String, value: String, observer: (String) -> LiveData<Boolean>) {
    val data: State<Boolean?> = observer("$key-$index").observeAsState()
    data.checkData {
        Text(text = value, style = TextStyle(fontSize = 20.sp))
    }
}

@Composable
private fun State<Boolean?>.checkData(renderChildren: @Composable() () -> Unit) {
    this.value?.let {
        if(it) {
            renderChildren()
        }
    }?: run {
        renderChildren()
    }
}