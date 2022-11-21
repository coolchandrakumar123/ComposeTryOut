package com.chan.composetryout.compose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by chandra-1765$ on 16/08/21$.
 */

@Preview
@Composable
fun LazyColumnItemUpdateDemo() {

    Column(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        val subject = ZPlatformViewData("subject").apply {
            setData(data = "This is Ticket Subject")
        }
        val ticketNo = ZPlatformViewData("ticketNo").apply {
            setData(data = "#12245")
        }
        val dueDate = ZPlatformViewData("dueDate").apply {
            setData(data = "12Jan2022")
        }
        val liveData = arrayListOf<ZPlatformViewData>(subject, ticketNo , dueDate)
        Text(text = "Click",
            color = Color.White,
            modifier = Modifier
                .background(color = Color.Magenta, shape = RoundedCornerShape(size = 4.dp))
                .clickable {
                    subject.setData(data = "Subject Item-Updated")
                    subject.setDataColor(Color.Red)
                }
                .padding(all = 16.dp)
        )
        LazyColumnViewData(data = liveData)
    }

}

@Stable
private class ZPlatformViewData(var key: String) {

    var dataValue: DataValue? = DataValue()
    private set

    var isHide: Boolean = false

    var dataColor: MutableState<Color?> = mutableStateOf(null)
    private set

    data class DataValue(
        var data: MutableState<String?> = mutableStateOf(null)
        //var data: String? = null
    )

    fun setData(data: String? = null): ZPlatformViewData  {
        dataValue?.data?.value = data
        stableDataClass.data = data
        //dataValue?.data = data
        return this
    }

    fun setDataColor(dataColor: Color?): ZPlatformViewData  {
        this.dataColor.value = dataColor
        stableDataClass.dataColor = dataColor
        return this
    }

    val stableDataClass = StableDataClass()
    //private set

    @Stable
    data class StableDataClass(
        var data: String? = null,
        var dataColor: Color? = null
    )
}

@Composable
private fun LazyColumnViewData(data: List<ZPlatformViewData>) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp),
        state = listState
    ) {
        items(
            items = data
        ) { item ->
            ListItemViewData(item)
            Divider(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                color = Color.Black
            )
        }
    }
}

@Composable
private fun ListItemViewData(viewData: ZPlatformViewData) {
    val data = remember {
        viewData.dataValue
    }

    val isHide = remember {
        viewData.isHide
    }

    val dataColor = remember {
        viewData.dataColor
    }

    /*data?.data?.value?.let {

    }*/
    ZDText(viewData)
    //ZDText(value = viewData.stableDataClass.data, dataColor = viewData.stableDataClass.dataColor)
    /*if(data?.value?.isNotEmpty() == true && isHide == false) {
    }*/
}

@Composable
private fun ZDText(viewData: ZPlatformViewData) {
    ZDText(value = viewData.stableDataClass.data, dataColor = viewData.stableDataClass.dataColor)
}

@Composable
private fun ZDText(value: String?, dataColor: Color?) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 16.dp),
        text = value?:"Empty",
        color = dataColor?:Color.Blue,
        fontSize = 22.sp
    )
}