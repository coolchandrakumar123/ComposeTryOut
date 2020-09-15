package com.chan.composetryout.compose

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.runtime.state
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class AdapterValueList(
    val data: ArrayList<UIValue>,
    var isUpdated: Boolean
)

data class UIValue(
    var position: Int = -1,
    val value: String
)

private val itemList: AdapterValueList = AdapterValueList(isUpdated = true, data = arrayListOf(UIValue(0,"Item-0")))

@Composable
fun inflateAdapterList() {
    for(i in 1..15) {
        itemList.data.add(UIValue(position = i, value = "Item-$i"))
    }
    BodyContent(itemList = itemList) {
        val size = itemList.data.size
        for (i in size..(size+15) ) {
            itemList.data.add(UIValue(position = i, value = "Item-$i"))
        }
        itemList.isUpdated = true
    }
}

@Composable
fun BodyContent(itemList: AdapterValueList, onLoadMore: () -> Unit) {
    val updateStatus = state { itemList.isUpdated }
    if (updateStatus.value) {
        LoadMoreAdapterList(
            itemList = itemList,
            onLoadMore = onLoadMore
        )
    }
}

@Composable
internal fun LoadMoreAdapterList(
    itemList: AdapterValueList,
    onLoadMore: () -> Unit
) {
    /*AdapterList(data = itemList.data) { data ->
        ItemViewHolder(
            data = data,
            positionObserver = { position ->
                val viewItemOffset = 6
                if(position + viewItemOffset  > itemList.data.size) {
                    onLoadMore()
                }
            }
        )
    }*/
}

@Composable
private fun ItemViewHolder(
    data: UIValue,
    positionObserver: (Int) -> Unit
) {
    /*key(inputs = *arrayOf(data.position)) {
    }*/
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        Text(text = data.value,
            modifier = Modifier.padding(all = 16.dp),
            style = TextStyle(fontSize = 16.sp)
        )
        Divider(color = Color.LightGray, thickness = 1.dp)
        onActive(callback = {
            positionObserver(data.position)
        })
    }
}
