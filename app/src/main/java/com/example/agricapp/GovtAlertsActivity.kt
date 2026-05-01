package com.example.agricapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class GovtAlertsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_govt_alerts)

        findViewById<ImageView>(R.id.backBtn).setOnClickListener {
            finish()
        }
    }
}
