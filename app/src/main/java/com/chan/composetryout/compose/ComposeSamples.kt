package com.chan.composetryout.compose

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

/**
 * Created by chandra-1765$ on 05/08/21$.
 */
@Preview
@Composable
fun SingleText() {
    Text(text = "Chandrakumar", style = TextStyle(fontSize = 20.sp))
}