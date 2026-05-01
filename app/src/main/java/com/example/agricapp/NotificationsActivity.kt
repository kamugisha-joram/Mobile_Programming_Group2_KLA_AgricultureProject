package com.example.agricapp

import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD
=======
import android.widget.LinearLayout
import android.widget.TextView
>>>>>>> Robert
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class NotificationsActivity : AppCompatActivity() {
<<<<<<< HEAD
=======

>>>>>>> Robert
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

<<<<<<< HEAD
=======
        setupBottomNavigation()
        setupDummyNotifications()
    }

    private fun setupBottomNavigation() {
>>>>>>> Robert
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_notifications

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_messages -> {
                    startActivity(Intent(this, MessagesActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_notifications -> true
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }
<<<<<<< HEAD
=======

    private fun setupDummyNotifications() {
        val container = findViewById<LinearLayout>(R.id.notificationsContainer)
        val notifications = listOf(
            "Weather Alert: Heavy rain expected tomorrow in your area.",
            "Market Update: Maize prices increased by 10% this week.",
            "Pest Alert: Fall Armyworm reported in neighboring district.",
            "Tips: It's time to apply top dressing to your maize crop."
        )

        for (notif in notifications) {
            val tv = TextView(this)
            tv.text = notif
            tv.setPadding(32, 32, 32, 32)
            tv.textSize = 16f
            container.addView(tv)
        }
    }
>>>>>>> Robert
}
