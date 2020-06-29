package com.chan.composetryout.compose

import android.content.Context
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.layout.Column
import androidx.ui.livedata.observeAsState
import androidx.ui.viewinterop.AndroidView

@Composable
fun inflateAndroidView() {
    Text(text = "Hello World")
}
//@Preview(showDecoration = true)
@Composable
fun MarkDownParser(valueObserver: (String)-> LiveData<Any>?) {
    val kotlin = """
   # Hello World
   **I'm your boss**
   ![haha](//hello.com)
   """.trimIndent()
    val ctx:Context = ContextAmbient.current
    //val html = parseMd(kotlin)
    Column(modifier = Modifier.clickable(onClick = {
        Log.d("ChanLog", "MarkDownParser: Clicked")
    })) {
        //Text(text = "Title")
        val test = valueObserver.invoke("threadWebView")?.observeAsState("Initial Content")
        //Text(text = "Title-${test?.value}")
        val webView = emitWebView(ctx)
        AndroidView(view = webView)
        (test?.value as? String?)?.let {
            webView.setWebContent(it)
        }
        //AndroidView(view = textview(ctx, kotlin))
    }
}

private fun emitWebView(ctx: Context) = CustomWebView(ctx)

fun webview(ctx: Context, html: String) = WebView(ctx).apply {
    Log.d("ChanLog", "webview: $html")
    this.webViewClient = WebViewClient()
    loadData("<html><body><h1>Ch($html)</h1></body></html>", "text/html", "utf-8")
    //loadUrl("https://www.google.com/")
}

private class CustomWebView(context: Context): WebView(context) {
    init {
        this.webViewClient = WebViewClient()
    }
    fun setWebContent(html: String) {
        loadData("<html><body><h1>Ch($html)</h1></body></html>", "text/html", "utf-8")
    }
}

fun textview(ctx: Context, value: String) = TextView(ctx).apply { text = value }

//fun emitWebView(ctx: Context, html: String) =