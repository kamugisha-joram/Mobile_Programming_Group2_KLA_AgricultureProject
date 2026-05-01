package com.example.agricapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class CropInfoActivity : AppCompatActivity() {

    private lateinit var cropListContainer: LinearLayout
    private lateinit var searchEditText: EditText
    private var allCrops = listOf<Crop>()
    private var currentCategory = "All Crops"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_info)

        cropListContainer = findViewById(R.id.cropListContainer)
        searchEditText = findViewById(R.id.searchEditText)
        val backBtn = findViewById<ImageView>(R.id.backBtn)
        
        backBtn.setOnClickListener { finish() }

        // Initialize Data
        allCrops = listOf(
            Crop("Maize", "A cereal crop widely grown in Uganda.", R.drawable.maize, "Cereals"),
            Crop("Rice", "Important cereal grown in swampy areas.", R.drawable.rice, "Cereals"),
            Crop("Beans", "High in protein and well grown in Uganda.", R.drawable.bean_pods, "Legumes"),
            Crop("Soya Beans", "Versatile legume for oil and protein.", R.drawable.soya_beans, "Legumes"),
            Crop("Cassava", "Grows well in warm climates.", R.drawable.cassava, "Root Crops"),
            Crop("Sweet Potatoes", "Staple root crop in many regions.", R.drawable.sweet_potatoes, "Root Crops"),
            Crop("Banana", "Source of food and income.", R.drawable.banana, "Vegetables"),
            Crop("Tomato", "High value vegetable crop.", R.drawable.tomato, "Vegetables")
        )

        setupFilters()
        setupSearch()
        setupBottomNavigation()
        filterAndDisplayCrops()
    }

    private fun setupSearch() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterAndDisplayCrops()
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupFilters() {
        val chipAll = findViewById<TextView>(R.id.chipAll)
        val chipCereals = findViewById<TextView>(R.id.chipCereals)
        val chipLegumes = findViewById<TextView>(R.id.chipLegumes)
        val chipRootCrops = findViewById<TextView>(R.id.chipRootCrops)
        val chipVegetables = findViewById<TextView>(R.id.chipVegetables)

        val chips = listOf(chipAll, chipCereals, chipLegumes, chipRootCrops, chipVegetables)
        val categories = listOf("All Crops", "Cereals", "Legumes", "Root Crops", "Vegetables")

        for (i in chips.indices) {
            val chip = chips[i]
            val category = categories[i]
            chip.setOnClickListener {
                currentCategory = category
                updateChipStyles(chip, chips)
                filterAndDisplayCrops()
            }
        }
    }

    private fun updateChipStyles(selectedChip: TextView, allChips: List<TextView>) {
        for (chip in allChips) {
            if (chip == selectedChip) {
                chip.setBackgroundResource(R.drawable.bg_chip_selected)
                chip.setTextColor(ContextCompat.getColor(this, android.R.color.white))
            } else {
                chip.setBackgroundResource(R.drawable.bg_chip_unselected)
                chip.setTextColor(ContextCompat.getColor(this, R.color.gray))
            }
        }
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
                    true
                }
                R.id.nav_notifications -> {
                    startActivity(Intent(this, NotificationsActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun filterAndDisplayCrops() {
        val query = searchEditText.text.toString().lowercase()
        val filtered = allCrops.filter { crop ->
            (currentCategory == "All Crops" || crop.category == currentCategory) &&
            crop.name.lowercase().contains(query)
        }

        displayCrops(filtered)
    }

    private fun displayCrops(crops: List<Crop>) {
        cropListContainer.removeAllViews()
        for (crop in crops) {
            val itemView = layoutInflater.inflate(R.layout.item_crop, cropListContainer, false)
            itemView.findViewById<TextView>(R.id.cropName).text = crop.name
            itemView.findViewById<TextView>(R.id.cropDesc).text = crop.description
            itemView.findViewById<ImageView>(R.id.cropImage).setImageResource(crop.imageRes)
            
            // Functionality for the "arrow" or item click
            itemView.setOnClickListener {
                val intent = Intent(this, ProductDetailActivity::class.java)
                intent.putExtra("PRODUCT_NAME", crop.name)
                intent.putExtra("PRODUCT_IMAGE", crop.imageRes)
                startActivity(intent)
            }

            cropListContainer.addView(itemView)
        }
    }

    data class Crop(val name: String, val description: String, val imageRes: Int, val category: String)
}
