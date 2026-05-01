package com.example.agricapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TreatmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment)

        val backBtn = findViewById<ImageView>(R.id.backBtn)
        backBtn.setOnClickListener { finish() }

        // Get data from intent (from DiseaseActivity)
        val diseaseResult = intent.getStringExtra("result") ?: "Maize Leaf Blight"
        
        val diseaseName = findViewById<TextView>(R.id.diseaseName)
        val diseaseScientificName = findViewById<TextView>(R.id.diseaseScientificName)
        val drugName = findViewById<TextView>(R.id.drugName)
        val drugDose = findViewById<TextView>(R.id.drugDose)
        val usageInstructions = findViewById<TextView>(R.id.usageInstructions)

        // Populate with dynamic or dummy data based on the result
        if (diseaseResult.contains("Leaf Blight")) {
            diseaseName.text = "Maize Leaf Blight"
            diseaseScientificName.text = "(Exserohilum turcicum)"
            drugName.text = "Fungicide: Mancozeb 80% WP"
            drugDose.text = "Dose: 20g per 20 Litres of water"
            usageInstructions.text = "Spray evenly on leaves in the morning or evening. Repeat after 7-10 days."
        } else {
            // Default or other disease logic
            diseaseName.text = diseaseResult
        }
    }
}
