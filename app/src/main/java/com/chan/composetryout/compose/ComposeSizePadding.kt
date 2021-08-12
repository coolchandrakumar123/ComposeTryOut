package com.chan.composetryout.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.CombinedModifier
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
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
    val modifierLocal = modifier.then(Modifier.wrapContentWidth())
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

@Preview(showBackground = true)
@Composable
fun RowColumnThreeItem() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        verticalAlignment = Alignment.Top
    ) {
        //val modifier = Modifier.weight(weight = 1f, fill = false)
        ColumnWithThreeLine(this)
        Box(modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .background(color = Color.Yellow)
        ) {
            Text(
                text = "RightItem",
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(alignment = Alignment.TopCenter),
                softWrap = false,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ColumnWithThreeLine(scope: Any?) {
    Column(modifier = Modifier
        .fillMaxWidth().checkAndUpdateModifier(scope).applyBackground()) {
        SimpleText(
            scope = this,
            valueText = "First Item with Larger Content to allocate second Equal Split up "
        )
        SimpleText(
            scope = this,
            valueText = "Second Item Length Check-Length check to wrapping case to third line"
        )
        SimpleText(
            scope = this,
            valueText = "Third"
        )
    }
}

private fun Modifier.checkAndUpdateModifier(scope: Any?) = run {
    var modifier = this
    (scope as? RowScope?)?.apply {
        modifier = CombinedModifier(modifier, Modifier.weight(weight = 1f, fill = false))
    }?:(scope as? ColumnScope?)?.apply {
        modifier = CombinedModifier(modifier, Modifier.weight(weight = 1f, fill = false))
    }
    modifier
}

private fun Modifier.applyBackground() = this.then(Modifier.background(color = Color.Yellow))

private fun Modifier.applyWeight(scope: RowScope) = kotlin.run {
    with(scope) {
        this@applyWeight.then(Modifier.weight(weight = 1f, fill = false))
    }
}

@Composable
fun SimpleText(scope: Any?, valueText: String) {
    Text(
        text = valueText,
        style = TextStyle(fontSize = 20.sp),
        modifier = Modifier.fillMaxWidth().padding(16.dp).wrapContentHeight().checkAndUpdateModifier(scope),
        softWrap = true,
        overflow = TextOverflow.Ellipsis,
        color = Color.Blue,
    )
}
