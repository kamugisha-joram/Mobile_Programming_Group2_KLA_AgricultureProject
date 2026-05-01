package com.example.agricapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MarketTrendsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_trends)

        findViewById<ImageView>(R.id.backBtn).setOnClickListener {
            finish()
        }
    }
}
