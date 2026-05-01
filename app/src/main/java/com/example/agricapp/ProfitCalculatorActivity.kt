package com.example.agricapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.NumberFormat
import java.util.*

class ProfitCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profit_calculator)

        val backBtn = findViewById<ImageView>(R.id.backBtn)
        backBtn.setOnClickListener { finish() }

        val cropSpinner = findViewById<Spinner>(R.id.cropSpinner)
        val landSizeInput = findViewById<EditText>(R.id.landSizeInput)
        val calculateBtn = findViewById<Button>(R.id.calculateBtn)
        
        val resultCard = findViewById<CardView>(R.id.resultCard)
        val yieldText = findViewById<TextView>(R.id.yieldResultText)
        val revenueText = findViewById<TextView>(R.id.revenueResultText)
        val costText = findViewById<TextView>(R.id.costResultText)
        val profitText = findViewById<TextView>(R.id.profitResultText)

        val crops = arrayOf("Maize", "Beans", "Rice", "Cassava")
        cropSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, crops)

        calculateBtn.setOnClickListener {
            val landSizeStr = landSizeInput.text.toString()
            if (landSizeStr.isEmpty()) {
                Toast.makeText(this, "Please enter land size", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val landSize = landSizeStr.toDouble()
            val selectedCrop = cropSpinner.selectedItem.toString()

            // Dummy Logic based on Ugandan averages
            // Yield per acre, Price per kg, Cost per acre
            val (yieldPerAcre, pricePerKg, costPerAcre) = when (selectedCrop) {
                "Maize" -> Triple(1000.0, 1200.0, 400000.0)
                "Beans" -> Triple(500.0, 3000.0, 350000.0)
                "Rice" -> Triple(1500.0, 2500.0, 800000.0)
                "Cassava" -> Triple(4000.0, 500.0, 500000.0)
                else -> Triple(0.0, 0.0, 0.0)
            }

            val totalYield = yieldPerAcre * landSize
            val totalRevenue = totalYield * pricePerKg
            val totalCost = costPerAcre * landSize
            val totalProfit = totalRevenue - totalCost

            val formatter = NumberFormat.getCurrencyInstance(Locale("en", "UG"))
            formatter.currency = Currency.getInstance("UGX")

            yieldText.text = String.format("%.0f Kg", totalYield)
            revenueText.text = formatter.format(totalRevenue).replace("UGX", "UGX ")
            costText.text = formatter.format(totalCost).replace("UGX", "UGX ")
            profitText.text = formatter.format(totalProfit).replace("UGX", "UGX ")

            resultCard.visibility = View.VISIBLE
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
