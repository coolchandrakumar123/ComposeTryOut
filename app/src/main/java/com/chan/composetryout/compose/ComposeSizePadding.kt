package com.chan.composetryout.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by chandra-1765$ on 06/08/21$.
 */

@Preview
@Composable
fun RowWithThreeItem() {
    Row(modifier = Modifier.fillMaxWidth()) {
        val modifier = Modifier.weight(weight = 1f, fill = false)
        TextWithSize(
            valueText = "First Item with Larger Content to allocate second Equal Split up",
            modifier = modifier
        )
        TextWithSize(
            valueText = "Second",
            modifier = modifier
        )
        TextWithSize(
            valueText = "Third",
            modifier = modifier
        )
    }
}

@Composable
fun TextWithSize(modifier: Modifier, valueText: String) {
    val modifierLocal = modifier.then(Modifier.getWidthModifier())
    Text(
        text = valueText,
        style = TextStyle(fontSize = 20.sp),
        modifier = modifierLocal,
        softWrap = false,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview
@Composable
fun ColumnWithTwoItem() {
    Column(modifier = Modifier.fillMaxWidth()) {
        val modifier = Modifier.weight(weight = 1f, fill = false)
        TextWithSize(
            valueText = "First Item with Larger Content to allocate second Equal Split up",
            modifier = modifier
        )
        TextWithSize(
            valueText = "Second",
            modifier = modifier
        )
        TextWithSize(
            valueText = "Third",
            modifier = modifier
        )
    }
}

fun Modifier.getWidthModifier() = kotlin.run {
    this.then(Modifier.wrapContentWidth())
}