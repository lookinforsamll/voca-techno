package com.samuel.vocatechno

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class OrderActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        supportActionBar?.hide()

        webView = findViewById(R.id.web)
        webView.loadUrl("https://bit.ly/3RjmtYL")

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.settings.setSupportZoom(true)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("OrderActivity", "Back pressed")

                if (webView.canGoBack()) {
                    Log.d("OrderActivity", "WebView can go back")
                    startActivity(Intent(this@OrderActivity, ThanksActivity::class.java))
                    finish()
                } else {
                    Log.d("OrderActivity", "WebView cannot go back, navigating to ThanksActivity")
                    startActivity(Intent(this@OrderActivity, ThanksActivity::class.java))
                    finish()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }
}