package com.samuel.vocatechno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class vieworderActivity : AppCompatActivity() {
    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vieworder)
        webView = findViewById<WebView>(R.id.vieworderr) as WebView
        webView.loadUrl("https://bit.ly/47TKJY4")
        supportActionBar?.hide()

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.settings.setSupportZoom(true)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            finish()
        }
    }
}