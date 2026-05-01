package com.example.agricapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class AgroInputsActivity : AppCompatActivity() {

    private lateinit var inputsListContainer: LinearLayout
    private lateinit var searchEditText: EditText
    private var allInputs = listOf<AgroInput>()
    private var currentCategory = "Seeds"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agro_inputs)

        inputsListContainer = findViewById(R.id.inputsListContainer)
        searchEditText = findViewById(R.id.searchEditText)
        val backBtn = findViewById<ImageView>(R.id.backBtn)

        backBtn.setOnClickListener { finish() }

        // Initialize Data
        allInputs = listOf(
            AgroInput("Maize Seeds (Longe 10H)", "Certified hybrid seeds", "UGX 45,000 / 2kg", R.drawable.maize_seeds, "Seeds"),
            AgroInput("Beans Seeds (NABE 15)", "High yielding variety", "UGX 40,000 / 1kg", R.drawable.bean_seeds, "Seeds"),
            AgroInput("NPK Fertilizer 17:17:17", "For all crop types", "UGX 70,000 / 50kg", R.drawable.npk, "Fertilizers"),
            AgroInput("Urea Fertilizer", "Rich in Nitrogen", "UGX 150,000 / 50kg", R.drawable.urea, "Fertilizers"),
            AgroInput("Ambush Top (Fungicide)", "Controls many fungal diseases", "UGX 22,000 / 100ml", R.drawable.ambush, "Pesticides"),
            AgroInput("Dudu-Cyper", "Effective against fall armyworm", "UGX 15,000 / 100ml", R.drawable.dudu, "Pesticides"),
            AgroInput("Hand Hoe", "Durable steel head", "UGX 15,000", R.drawable.hoe, "Tools"),
            AgroInput("Knapsack Sprayer", "16L manual sprayer", "UGX 120,000", R.drawable.sprayer, "Tools")
        )

        setupFilters()
        setupSearch()
        setupBottomNavigation()
        filterAndDisplayInputs()
    }

    private fun setupSearch() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterAndDisplayInputs()
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupFilters() {
        val chips = mapOf(
            R.id.chipSeeds to "Seeds",
            R.id.chipFertilizers to "Fertilizers",
            R.id.chipPesticides to "Pesticides",
            R.id.chipTools to "Tools"
        )

        val chipViews = chips.keys.map { findViewById<TextView>(it) }

        for ((id, category) in chips) {
            val chip = findViewById<TextView>(id)
            chip.setOnClickListener {
                currentCategory = category
                updateChipStyles(chip, chipViews)
                filterAndDisplayInputs()
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

    private fun filterAndDisplayInputs() {
        val query = searchEditText.text.toString().lowercase()
        val filtered = allInputs.filter { input ->
            input.category == currentCategory &&
            (input.name.lowercase().contains(query) || input.description.lowercase().contains(query))
        }
        displayInputs(filtered)
    }

    private fun displayInputs(inputs: List<AgroInput>) {
        inputsListContainer.removeAllViews()
        for (input in inputs) {
            val itemView = layoutInflater.inflate(R.layout.item_agro_input, inputsListContainer, false)
            itemView.findViewById<TextView>(R.id.inputName).text = input.name
            itemView.findViewById<TextView>(R.id.inputDesc).text = input.description
            itemView.findViewById<TextView>(R.id.inputPrice).text = input.price
            itemView.findViewById<ImageView>(R.id.inputImage).setImageResource(input.imageRes)
            
            itemView.setOnClickListener {
                val intent = Intent(this, ProductDetailActivity::class.java)
                intent.putExtra("PRODUCT_NAME", input.name)
                intent.putExtra("PRODUCT_IMAGE", input.imageRes)
                startActivity(intent)
            }

            inputsListContainer.addView(itemView)
        }
    }

    data class AgroInput(val name: String, val description: String, val price: String, val imageRes: Int, val category: String)
}
