package com.samuel.vocatechno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class AboutUsActivity : AppCompatActivity() {
    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        supportActionBar?.hide()

        webView = findViewById<WebView>(R.id.abtus) as WebView
        webView.loadUrl("https://vocatechno.smk-ananda.sch.id")

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
