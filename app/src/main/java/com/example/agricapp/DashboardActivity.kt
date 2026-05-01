package com.example.agricapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
<<<<<<< HEAD
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
=======
>>>>>>> Robert

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

<<<<<<< HEAD
        // Enable Offline Persistence for Firestore
        val db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        db.firestoreSettings = settings

=======
>>>>>>> Robert
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

<<<<<<< HEAD
        findViewById<View>(R.id.profitCalcCard).setOnClickListener {
            startActivity(Intent(this, ProfitCalculatorActivity::class.java))
        }

        findViewById<View>(R.id.expertChatCard).setOnClickListener {
            // Reusing MessagesActivity for now or creating a specific one later
            startActivity(Intent(this, MessagesActivity::class.java))
        }

        // Top Icons
        findViewById<View>(R.id.menuIcon).setOnClickListener {
            Toast.makeText(this, "Menu coming soon", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.notificationsIconTop).setOnClickListener {
            startActivity(Intent(this, NotificationsActivity::class.java))
        }

=======
>>>>>>> Robert
        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_home
        
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_messages -> {
<<<<<<< HEAD
                    startActivity(Intent(this, MessagesActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_notifications -> {
                    startActivity(Intent(this, NotificationsActivity::class.java))
                    finish()
=======
                    Toast.makeText(this, "Messages coming soon", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_notifications -> {
                    Toast.makeText(this, "Notifications coming soon", Toast.LENGTH_SHORT).show()
>>>>>>> Robert
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
<<<<<<< HEAD
                    finish()
                    true
=======
                    false // Don't highlight here, we're moving to a new activity
>>>>>>> Robert
                }
                else -> false
            }
        }
    }
}
