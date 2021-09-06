package com.chan.composetryout.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by chandra-1765$ on 16/08/21$.
 */

@Preview
@Composable
fun SimpleLazyColumnDemo() {

    Column(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally) {
        val liveData = MutableLiveData<List<String>> (mutableListOf("One", "Two", "Three"))
        Text(text = "Click",
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Magenta, shape = RoundedCornerShape(size = 4.dp))
                .clickable {
                    val test: List<String> = liveData.value?.let {
                        val data = arrayListOf<String>()
                        data.addAll(it)
                        data.add("additional")
                        data
                    } ?: arrayListOf<String>("additional")
                    liveData.postValue(test)
                }
                .padding(all = 16.dp)
        )
        SimpleLazyColumnList(liveData = liveData)
    }

}

@Composable
fun SimpleLazyColumnList(liveData: LiveData<List<String>>) {
    val mutableLiveData by liveData.observeAsState()
    mutableLiveData?.let {
        SimpleLazyColumn(data = it)
    }
}

@Composable
fun SimpleLazyColumnList() {
    val data = remember { mutableStateListOf<Note>(Note(1, "One"), Note(2,"Two"), Note(3,"Three"),
        Note(4,"Four"), Note(5,"Five")) }
    var id = 6
    Column {
        Row(modifier = Modifier.padding(all = 16.dp)) {
            Text(text = "Add",
                color = Color.White,
                modifier = Modifier
                    .background(color = Color.Blue)
                    .clickable {
                        //data.add(Note(6,"Added on Click:}"))
                        data.add(3, Note(id++,"Added on Click:}"))
                    }
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.requiredWidth(width = 48.dp))
            Text(text = "Remove",
                color = Color.White,
                modifier = Modifier
                    .background(color = Color.Blue)
                    .clickable {
                        data.removeLastOrNull()
                    }
                    .padding(16.dp)
            )
        }
        SimpleLazyColumnList(data = data)
    }
}

@Composable
fun SimpleLazyColumn(data: List<String>) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp),
        state = listState
    ) {
        items(
            items = data
        ) { item ->
            SimpleListItem(item)
            Divider(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                color = Color.Black
            )
        }
    }

}

data class Note(val id: Int, val content: String)
@Composable
fun SimpleLazyColumnList(data: List<Note>) {
    val listState = rememberLazyListState()
    val notes by remember {
        mutableStateOf(data)
    }
    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp),
        state = listState
    ) {
        items(
            items = notes,
            key = {
                it.id
            }
        ) { item ->
            SimpleListItem(item.content)
            Divider(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                color = Color.Black
            )
        }
    }

}

@Composable
fun SimpleListItem(value: String) {
    Log.d("ChanLog", "SimpleListItem: $value")
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 16.dp),
        text = value,
        color = Color.Blue,
        fontSize = 22.sp
    )
}