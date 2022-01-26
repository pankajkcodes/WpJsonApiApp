package com.pankajkcodes.wpjsonapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast


import android.webkit.WebView


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val data = intent
        val title = data.getStringExtra("title")
        val content = data.getStringExtra("content")
        supportActionBar!!.setTitle(title)

        val contentView = findViewById<WebView>(R.id.webView)

        val mimeType = "text/html"
        val encoding = "UTF-8"

        contentView.loadDataWithBaseURL("", content!!, mimeType, encoding, "")
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
    }
}