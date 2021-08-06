package com.chan.composetryout.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by chandra-1765$ on 06/08/21$.
 */

@Preview
@Composable
fun RowWithTwoItem() {
    Row(modifier = Modifier.fillMaxWidth()) {
        TextWithSize(
            valueText = "First Item with Larger Content to allocate second Equal Split up"
        )
        TextWithSize(
            valueText = "Second"
        )
    }
}

@Composable
fun RowScope.TextWithSize(valueText: String) {
    Text(
        text = valueText,
        style = TextStyle(fontSize = 20.sp),
        modifier = Modifier
            .wrapContentWidth().weight(weight = 1f, fill = false)
    )
}