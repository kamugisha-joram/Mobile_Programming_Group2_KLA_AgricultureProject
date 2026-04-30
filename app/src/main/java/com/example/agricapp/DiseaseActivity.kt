package com.example.agricapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class DiseaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease)

        val backBtn = findViewById<ImageView>(R.id.backBtn)
        backBtn.setOnClickListener {
            finish()
        }

        val cropSpinner = findViewById<Spinner>(R.id.cropSpinner)
        val crops = arrayOf("Maize", "Beans", "Cassava", "Banana")
        cropSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, crops)

        val checkBtn = findViewById<Button>(R.id.checkBtn)
        checkBtn.setOnClickListener {
            val intent = Intent(this, TreatmentActivity::class.java)
            intent.putExtra("result", "Possible Disease: Leaf Blight")
            startActivity(intent)
        }

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_messages -> {
                    Toast.makeText(this, "Messages coming soon", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_notifications -> {
                    Toast.makeText(this, "Notifications coming soon", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
