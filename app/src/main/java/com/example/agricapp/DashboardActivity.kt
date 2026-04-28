package com.example.agricapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Quick Access Grid clicks
        findViewById<View>(R.id.diseaseBtn).setOnClickListener {
            startActivity(Intent(this, DiseaseActivity::class.java))
        }

        findViewById<View>(R.id.marketBtn).setOnClickListener {
            startActivity(Intent(this, MarketplaceActivity::class.java))
        }

        findViewById<View>(R.id.cropInfoCard).setOnClickListener {
            startActivity(Intent(this, CropInfoActivity::class.java))
        }

        findViewById<View>(R.id.agroInputsCard).setOnClickListener {
            startActivity(Intent(this, AgroInputsActivity::class.java))
        }

        findViewById<View>(R.id.cropCalendarCard).setOnClickListener {
            startActivity(Intent(this, CropCalendarActivity::class.java))
        }

        findViewById<View>(R.id.weatherCard).setOnClickListener {
            startActivity(Intent(this, WeatherActivity::class.java))
        }

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_home
        
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
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
                    false // Don't highlight here, we're moving to a new activity
                }
                else -> false
            }
        }
    }
}
