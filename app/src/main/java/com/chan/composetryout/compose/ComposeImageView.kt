package com.chan.composetryout.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap

/**
 * Created by chandra-1765$ on 11/08/21$.
 */
@Preview
@Composable
fun ImageWithPadding() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        context.getDrawable(android.R.drawable.ic_menu_search)?.let { drawable ->
            Image(modifier = Modifier
                .requiredHeight(16.dp)
                .requiredWidth(28.dp)
                .padding(
                    start = 0.dp,
                    top = 0.dp,
                    end = 12.dp,
                    bottom = 0.dp
                ),
                bitmap = drawable.toBitmap().asImageBitmap(),
                contentDescription = null
            )
        }
        Text(text = "Next Line")
    }
}

@Preview
@Composable
fun DotSeparator() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.White),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = "First Item")
        context.getDrawable(android.R.drawable.ic_menu_search)?.let { drawable ->
            Image(modifier = Modifier
                .requiredHeight(16.dp)
                .requiredWidth(40.dp)
                .padding(
                    start = 12.dp,
                    top = 0.dp,
                    end = 12.dp,
                    bottom = 0.dp
                ),
                bitmap = drawable.toBitmap().asImageBitmap(),
                contentDescription = null
            )
        }
        Box(modifier = Modifier
            .size(12.dp)
            .padding(horizontal = 4.dp)
            .background(color = Color.Yellow)) {
            Canvas(modifier = Modifier.size(12.dp)) {
                this.drawCircle(color = Color.Blue)
            }
        }
        Text(text = "Second Item")
    }
}