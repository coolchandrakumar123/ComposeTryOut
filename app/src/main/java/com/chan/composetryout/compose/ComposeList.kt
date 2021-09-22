package com.chan.composetryout.compose

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
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
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowRow

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
fun NestedScrollList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
            .verticalScroll(state = rememberScrollState())
    ) {
        noteList.forEach {
            Text(text = "Column-${it.content}")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Text(text = "SecondList")
            noteList.forEach {
                Text(text = "Column-${it.content}")
            }
        }
        //SimpleLazyColumnList()
    }
}

private val noteList = arrayListOf(Note(1, "One"), Note(2,"Two"), Note(3,"Three"),
    Note(4,"Four"), Note(5,"Five"))

@Composable
fun SimpleLazyColumnList() {
    val data = remember { mutableStateListOf<Note>() }
    for (i in 6..20) {
        noteList.add(Note(i, "Item-$i"))
    }
    data.addAll(noteList)
    var id = 6
    Column {
        Row(modifier = Modifier.padding(all = 16.dp)) {
            Text(text = "Add",
                color = Color.White,
                modifier = Modifier
                    .background(color = Color.Blue)
                    .clickable {
                        //data.add(Note(6,"Added on Click:}"))
                        data.add(3, Note(id++, "Added on Click:}"))
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
    val notes by remember {
        mutableStateOf(value)
    }
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 16.dp),
        text = notes,
        color = Color.Blue,
        fontSize = 22.sp
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SimpleFlowRowList() {
    val listState = rememberLazyListState()
    val notes by remember {
        mutableStateOf(noteList)
    }
    Column(
        modifier = Modifier.verticalScroll(state = rememberScrollState())
    ) {
        Text(text = "LazyRow-OverLap")
        /*LazyRow {
            itemsIndexed(notes) { index, item ->
                val modifier = if(index == 0) {
                    Modifier
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .padding(all = 16.dp)
                } else {
                    Modifier
                        .offset(x = ((-20) * index).dp)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .border(width = 1.dp, color = Color.Red)
                        .padding(all = 16.dp)
                }
                Text(
                    modifier = modifier,
                    text = item.content,
                    color = Color.Blue,
                    fontSize = 22.sp
                )
            }
        }*/
        OverLapLazyRow(
            items = notes
        ) { item ->
            Text(
                modifier = Modifier
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(size = 20.dp))
                    .padding(all = 16.dp),
                text = item.content,
                color = Color.Blue,
                fontSize = 22.sp
            )
        }
        Text(text = "FlowRow")
        FlowRow {
            notes.forEach { item ->
                Text(
                    modifier = Modifier
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .padding(all = 16.dp),
                    text = item.content,
                    color = Color.Blue,
                    fontSize = 22.sp
                )
            }
        }
        Text(text = "GridView")
        /*LazyVerticalGrid(cells = GridCells.Fixed(count = 3) ) {
            items(notes) { item ->
                Text(
                    modifier = Modifier
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .padding(all = 16.dp),
                    text = item.content,
                    color = Color.Blue,
                    fontSize = 22.sp
                )
            }
        }*/
        VerticalGrid(items = notes, count = 3) { item ->
            Text(
                modifier = Modifier
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(size = 20.dp)
                    )
                    .padding(all = 16.dp),
                text = item.content,
                color = Color.Blue,
                fontSize = 22.sp
            )
        }
    }
}

@Composable
private fun <T> OverLapLazyRow(
    modifier: Modifier = Modifier,
    items: List<T>,
    content: @Composable (BoxScope.(item: T) -> Unit)
) {
    LazyRow(modifier = modifier) {
        itemsIndexed(items) { index, item ->
            Box(
                modifier = if(index == 0) Modifier else Modifier.offset(x = ((-20) * index).dp)
            ) {
                content(item)
            }
        }
    }
}

@Composable
private fun <T> VerticalGrid(
    modifier: Modifier = Modifier,
    items: List<T>,
    count: Int,
    content: @Composable (BoxScope.(item: T) -> Unit)
) {
    val totalSize = items.size
    val rows = (totalSize + count - 1) / count
    Column(
        modifier = modifier
    ){
        for (rowIndex in 0 until rows) {
            Row {
                for (columnIndex in 0 until count) {
                    val itemIndex = rowIndex * count + columnIndex
                    if (itemIndex < totalSize) {
                        Box(
                            modifier = Modifier.weight(1f, fill = true),
                            propagateMinConstraints = true
                        ) {
                            content(items[itemIndex])
                        }
                    } else {
                        Spacer(Modifier.weight(1f, fill = true))
                    }
                }
            }
        }
    }
}