package com.example.btvn2.activity

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.btvn2.R

class WebviewActivity : AppCompatActivity() {
    var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        webView = findViewById<WebView>(R.id.web_view)

        val intent = intent
        val href = intent.getStringExtra("href")

        with(webView) {
            this?.setWebViewClient(WebViewClient())
            this?.loadUrl(href!!)
        }
    }
}