package com.example.agricapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QrScannerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scanner)

        findViewById<ImageView>(R.id.backBtn).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.simulateScanBtn).setOnClickListener {
            // Simulated QR Scan Result
            Toast.makeText(this, "Product Verified: NPK Fertilizer 50kg (Authentic)", Toast.LENGTH_LONG).show()
        }
    }
}
