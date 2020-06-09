package com.chan.composetryout.compose

import android.content.Context
import android.util.Log
import android.webkit.WebView
import android.widget.TextView
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.tooling.preview.Preview
import androidx.ui.viewinterop.AndroidView

@Composable
fun inflateAndroidView() {
    Text(text = "Hello World")
}

@Preview(showDecoration = true)
@Composable
fun MarkDownParser() {
    val kotlin = """
   # Hello World
   **I'm your boss**
   ![haha](//hello.com)
   """.trimIndent()
    Log.d("ChanLog", "MarkDownParser: Inside")
    val ctx:Context? = ContextAmbient.current
    //val html = parseMd(kotlin)
    Column {
        Text(text = kotlin)
        ctx?.let {
            Log.d("ChanLog", "Context: NonNull")
            //AndroidView(view = textview(ctx, kotlin))
            AndroidView(view = webview(ctx, kotlin))
        }?: kotlin.run {
            Log.d("ChanLog", "Context: Null")
        }
    }
}
fun webview(ctx: Context, html: String) = WebView(ctx).apply { loadData(html, "text/html", "utf-8") }

fun textview(ctx: Context, value: String) = TextView(ctx).apply { text = value }