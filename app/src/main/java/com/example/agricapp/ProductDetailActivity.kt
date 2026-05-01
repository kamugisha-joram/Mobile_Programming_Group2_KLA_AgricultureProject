package com.example.agricapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val name = intent.getStringExtra("productName") ?: "Product Details"
        val price = intent.getStringExtra("productPrice") ?: "Price on Request"
        val desc = intent.getStringExtra("productDesc") ?: "No description available."
        val imageRes = intent.getIntExtra("productImage", R.drawable.ic_launcher_background)

        findViewById<TextView>(R.id.detailProductName).text = name
        findViewById<TextView>(R.id.detailProductPrice).text = price
        findViewById<TextView>(R.id.detailProductDesc).text = desc
        findViewById<ImageView>(R.id.detailProductImage).setImageResource(imageRes)

        findViewById<ImageView>(R.id.backBtn)?.setOnClickListener { finish() }
        
        findViewById<com.google.android.material.button.MaterialButton>(R.id.contactSellerBtn).setOnClickListener {
            Toast.makeText(this, "Opening chat with seller...", Toast.LENGTH_SHORT).show()
        }
    }
}
