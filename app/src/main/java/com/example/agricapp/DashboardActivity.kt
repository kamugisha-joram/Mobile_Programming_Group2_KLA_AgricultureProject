package com.example.agricapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import java.util.*

class DashboardActivity : AppCompatActivity() {

    private val speechResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val spokenText: String? = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)
            spokenText?.let {
                handleVoiceCommand(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Feature 2: Offline Mode: Enable Offline Persistence for Firestore
        val db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        db.firestoreSettings = settings

        setupClickListeners()
        setupBottomNavigation()
    }

    private fun setupClickListeners() {
        // Feature 1: Local Language (handled via strings.xml and system locale)
        
        // Feature Grid
        findViewById<View>(R.id.cropInfoCard).setOnClickListener {
            startActivity(Intent(this, CropInfoActivity::class.java))
        }

        findViewById<View>(R.id.diseaseBtn).setOnClickListener {
            startActivity(Intent(this, DiseaseActivity::class.java))
        }

        findViewById<View>(R.id.marketBtn).setOnClickListener {
            startActivity(Intent(this, MarketplaceActivity::class.java))
        }

        // Seeds & Agro-Inputs
        findViewById<View>(R.id.agroInputsCard).setOnClickListener {
            startActivity(Intent(this, AgroInputsActivity::class.java))
        }

        // Crop Calendar
        findViewById<View>(R.id.cropCalendarCard).setOnClickListener {
            startActivity(Intent(this, CropCalendarActivity::class.java))
        }

        // Weather Updates
        findViewById<View>(R.id.weatherCard).setOnClickListener {
            startActivity(Intent(this, WeatherActivity::class.java))
        }

        // Profitability Calculator
        findViewById<View>(R.id.profitCalcCard).setOnClickListener {
            startActivity(Intent(this, ProfitCalculatorActivity::class.java))
        }

        // Expert Chat
        findViewById<View>(R.id.expertChatCard).setOnClickListener {
            startActivity(Intent(this, MessagesActivity::class.java))
        }

        // Top Icons
        findViewById<View>(R.id.menuIcon).setOnClickListener {
            Toast.makeText(this, "Menu coming soon", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.notificationsIconTop).setOnClickListener {
            startActivity(Intent(this, NotificationsActivity::class.java))
        }
    }

    private fun startVoiceRecognition() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "How can I help you today?")
        }
        try {
            speechResultLauncher.launch(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Voice search not supported on this device", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleVoiceCommand(command: String) {
        val lowerCommand = command.lowercase()
        when {
            lowerCommand.contains("crop") || lowerCommand.contains("ekirime") -> {
                startActivity(Intent(this, CropInfoActivity::class.java))
            }
            lowerCommand.contains("market") || lowerCommand.contains("akatale") -> {
                startActivity(Intent(this, MarketplaceActivity::class.java))
            }
            lowerCommand.contains("profit") || lowerCommand.contains("amagoba") -> {
                startActivity(Intent(this, ProfitCalculatorActivity::class.java))
            }
            lowerCommand.contains("price") || lowerCommand.contains("bbeeyi") -> {
                startActivity(Intent(this, MarketTrendsActivity::class.java))
            }
            lowerCommand.contains("risk") || lowerCommand.contains("maapu") -> {
                startActivity(Intent(this, PestRiskActivity::class.java))
            }
            lowerCommand.contains("govt") || lowerCommand.contains("buyambi") -> {
                startActivity(Intent(this, GovtAlertsActivity::class.java))
            }
            lowerCommand.contains("tip") || lowerCommand.contains("magezi") -> {
                startActivity(Intent(this, CommunityTipsActivity::class.java))
            }
            lowerCommand.contains("scan") || lowerCommand.contains("sikyana") -> {
                startActivity(Intent(this, QrScannerActivity::class.java))
            }
            lowerCommand.contains("expert") || lowerCommand.contains("omukugu") -> {
                startActivity(Intent(this, MessagesActivity::class.java))
            }
            else -> Toast.makeText(this, "You said: $command", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_home
        
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_messages -> {
                    startActivity(Intent(this, MessagesActivity::class.java))
                    finish()
                    true
                }
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
}
