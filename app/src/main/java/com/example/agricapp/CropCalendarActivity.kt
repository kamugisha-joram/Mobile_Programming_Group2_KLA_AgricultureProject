package com.example.agricapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class CropCalendarActivity : AppCompatActivity() {

    private lateinit var plantingIcon: ImageView
    private lateinit var harvestIcon: ImageView
    private lateinit var stagesContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_calendar)

        val backBtn = findViewById<ImageView>(R.id.backBtn)
        backBtn.setOnClickListener { finish() }

        plantingIcon = findViewById(R.id.plantingIcon)
        harvestIcon = findViewById(R.id.harvestIcon)
        stagesContainer = findViewById(R.id.stagesContainer)

        val cropSpinner = findViewById<Spinner>(R.id.cropSpinner)
        val crops = arrayOf("Maize", "Beans", "Cassava", "Banana")
        cropSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, crops)

        val plantingPeriodText = findViewById<TextView>(R.id.plantingPeriodText)
        val harvestPeriodText = findViewById<TextView>(R.id.harvestPeriodText)

        cropSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val crop = crops[position]
                updateCropData(crop, plantingPeriodText, harvestPeriodText)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        setupBottomNavigation()
    }

    private fun updateCropData(crop: String, plantingText: TextView, harvestText: TextView) {
        val imageRes = when (crop) {
            "Maize" -> R.drawable.maize
            "Beans" -> R.drawable.bean_pods
            "Cassava" -> R.drawable.cassava
            "Banana" -> R.drawable.banana
            else -> R.drawable.ic_launcher_background
        }

        plantingIcon.setImageResource(imageRes)
        harvestIcon.setImageResource(imageRes)

        when (crop) {
            "Maize" -> {
                plantingText.text = "March - April"
                harvestText.text = "July - August"
                setupStages(listOf(Stage("Land Prep", "2 Weeks", imageRes), Stage("Planting", "1 Week", imageRes), Stage("Weeding", "4 Weeks", imageRes)))
            }
            "Beans" -> {
                plantingText.text = "March - April"
                harvestText.text = "June - July"
                setupStages(listOf(Stage("Planting", "1 Week", imageRes), Stage("Flowering", "3 Weeks", imageRes), Stage("Harvesting", "1 Week", imageRes)))
            }
            "Cassava" -> {
                plantingText.text = "April - May"
                harvestText.text = "January - March (Next Year)"
                setupStages(listOf(Stage("Land Prep", "3 Weeks", imageRes), Stage("Planting", "2 Weeks", imageRes), Stage("Maturity", "8 Months", imageRes)))
            }
            "Banana" -> {
                plantingText.text = "Year Round"
                harvestText.text = "Year Round"
                setupStages(listOf(Stage("Sucker Prep", "2 Weeks", imageRes), Stage("Planting", "1 Week", imageRes), Stage("Fruiting", "9 Months", imageRes)))
            }
        }
    }

    private fun setupStages(stages: List<Stage>) {
        stagesContainer.removeAllViews()
        for (stage in stages) {
            val itemView = layoutInflater.inflate(R.layout.item_growth_stage, stagesContainer, false)
            itemView.findViewById<TextView>(R.id.stageName).text = stage.name
            itemView.findViewById<TextView>(R.id.stageDuration).text = stage.duration
            itemView.findViewById<ImageView>(R.id.stageImage).setImageResource(stage.imageRes)
            stagesContainer.addView(itemView)
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

    data class Stage(val name: String, val duration: String, val imageRes: Int)
}
