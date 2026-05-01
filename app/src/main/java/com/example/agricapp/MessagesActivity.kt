package com.example.agricapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MessagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

        setupBottomNavigation()
        setupDummyMessages()
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_messages

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_messages -> true
                R.id.nav_notifications -> {
                    startActivity(Intent(this, NotificationsActivity::class.java))
                    finish()
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

    private fun setupDummyMessages() {
        val container = findViewById<LinearLayout>(R.id.messagesContainer)
        val messages = listOf(
            Message("Agro-Store", "Your order is ready for pickup!", "10:30 AM"),
            Message("Farming Support", "How can we help you today?", "Yesterday"),
            Message("Village Group", "New discussion in 'Maize Farming'", "Monday")
        )

        container.removeAllViews()
        val inflater = LayoutInflater.from(this)

        for (msg in messages) {
            val itemView = inflater.inflate(R.layout.item_message, container, false)
            itemView.findViewById<TextView>(R.id.senderName).text = msg.sender
            itemView.findViewById<TextView>(R.id.messageSnippet).text = msg.snippet
            itemView.findViewById<TextView>(R.id.messageTime).text = msg.time
            
            itemView.setOnClickListener {
                Toast.makeText(this, "Chat with ${msg.sender} coming soon", Toast.LENGTH_SHORT).show()
            }
            
            container.addView(itemView)
        }
    }

    data class Message(val sender: String, val snippet: String, val time: String)
}
