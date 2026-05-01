package com.example.agricapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class PestRiskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pest_risk)

        findViewById<ImageView>(R.id.backBtn).setOnClickListener {
            finish()
        }
    }
}
