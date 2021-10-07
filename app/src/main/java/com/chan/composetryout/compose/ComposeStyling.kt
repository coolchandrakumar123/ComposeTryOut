package com.chan.composetryout.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

/**
 * Created by chandra-1765$ on 11/08/21$.
 */
@Preview(showSystemUi = true)
@Composable
fun RowWithBg() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextWithBorder()
        RowWithBgColor()
        RowWithGradient()
        RowWithBorder()
        RowWithBorderAndGradient()
        RowWithShadow()
    }
}

@Composable
fun RowWithBgColor(value: String = "WithBgColor") {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(64.dp)
                .background(color = Color.Red, shape = RoundedCornerShape(60.dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = value, color = Color.White, fontSize = 22.sp)
        }
    }
}

@Composable
fun RowWithBorder(value: String = "WithBorder") {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(64.dp)
                .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(60.dp))
                .background(color = Color.Yellow, shape = RoundedCornerShape(60.dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = value, color = Color.Blue, fontSize = 22.sp)
        }
    }
}

@Composable
fun RowWithGradient(value: String = "WithGradient") {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(64.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue)),
                    shape = RoundedCornerShape(60.dp)
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = value, color = Color.White, fontSize = 22.sp)
        }
    }
}

@Composable
fun RowWithBorderAndGradient(value: String = "BgOverrideGradient") {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(64.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue)),
                    shape = RoundedCornerShape(60.dp)
                )
                .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(60.dp))
                .background(color = Color.Transparent, shape = RoundedCornerShape(60.dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = value, color = Color.White, fontSize = 22.sp)
        }
    }
}

@Composable
fun TextWithBorder(value: String = "TextWithBorder") {
    var modifier = Modifier
        .fillMaxWidth()
    modifier = modifier.composed {
        Modifier.border(width = 2.dp, color = Color.Red)
    }
    Text(
        modifier = modifier.padding(all = 16.dp),
        text = value,
        color = Color.Blue,
        fontSize = 22.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun RowWithShadow(value: String = "WithShadow") {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(64.dp)
                .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(60.dp))
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(60.dp), clip = false)
                .background(color = Color.White, shape = RoundedCornerShape(60.dp))
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = value, color = Color.Black, fontSize = 22.sp)
        }
        /*Shadow(
            color = Color(0x99000000),
            offset = Offset(100f, 10f)
        )*/
        /*Shadow(
            color: Color = Color(0xFF000000),
        offset: Offset = Offset.Zero,
        blurRadius: Float = 0.0f
        )*/
    }
}