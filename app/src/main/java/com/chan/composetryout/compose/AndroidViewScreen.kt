package com.chan.composetryout.compose

import android.content.Context
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
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
    val ctx:Context = ContextAmbient.current
    //val html = parseMd(kotlin)
    Column {
        Text(text = "Title")
        AndroidView(view = webview(ctx, kotlin+"WebView"))
        AndroidView(view = textview(ctx, kotlin))
    }
}

fun webview(ctx: Context, html: String) = WebView(ctx).apply {
    this.webViewClient = WebViewClient()
    loadData("<html><body><h1>Chandran-WebView</h1></body></html>", "text/html", "utf-8")
    //loadUrl("https://www.google.com/")
}

fun textview(ctx: Context, value: String) = TextView(ctx).apply { text = value }