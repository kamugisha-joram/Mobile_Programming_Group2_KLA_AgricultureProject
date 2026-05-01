package com.example.agricapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MarketplaceActivity : AppCompatActivity() {

    private lateinit var productListContainer: LinearLayout
    private lateinit var searchEditText: EditText
    private var allProducts = listOf<Product>()
    private var currentCategory = "All"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)

        productListContainer = findViewById(R.id.productListContainer)
        searchEditText = findViewById(R.id.searchEditText)
        val backBtn = findViewById<ImageView>(R.id.backBtn)

        backBtn.setOnClickListener { finish() }

        // Initialize Marketplace Data
        allProducts = listOf(
            Product("Maize", "100kg available", "Kampala", "UGX 120,000", R.drawable.maize, "Crops"),
            Product("Beans", "50kg available", "Masaka", "UGX 100,000", R.drawable.bean_pods, "Crops"),
            Product("Matooke", "1 Bunch", "Mbarara", "UGX 5,000", R.drawable.banana, "Crops"),
            Product("Cow", "Healthy Friesian", "Jinji", "UGX 1,500,000", R.drawable.cow, "Livestock"),
            Product("Goat", "Male Boer", "Gulu", "UGX 350,000", R.drawable.goat, "Livestock"),
            Product("Tractor", "New Holland", "Kampala", "UGX 45M", R.drawable.tractor, "Equipment"),
            Product("Plow", "Disc Plow", "Kampala", "UGX 2M", R.drawable.plough, "Equipment")
        )

        setupFilters()
        setupSearch()
        filterAndDisplayProducts()
    }

    private fun setupSearch() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterAndDisplayProducts()
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupFilters() {
        val filterIds = mapOf(
            R.id.chipAll to "All",
            R.id.chipCrops to "Crops",
            R.id.chipLivestock to "Livestock",
            R.id.chipEquipment to "Equipment"
        )

        for ((id, category) in filterIds) {
            findViewById<TextView>(id).setOnClickListener {
                currentCategory = category
                updateChipStyles(id, filterIds.keys)
                filterAndDisplayProducts()
            }
        }
    }

    private fun updateChipStyles(selectedId: Int, allIds: Set<Int>) {
        for (id in allIds) {
            val textView = findViewById<TextView>(id)
            if (id == selectedId) {
                textView.setBackgroundResource(R.drawable.bg_chip_selected)
                textView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
            } else {
                textView.setBackgroundResource(R.drawable.bg_chip_unselected)
                textView.setTextColor(ContextCompat.getColor(this, R.color.gray))
            }
        }
    }

    private fun filterAndDisplayProducts() {
        val query = searchEditText.text.toString().lowercase()
        val filtered = allProducts.filter { product ->
            (currentCategory == "All" || product.category == currentCategory) &&
            (product.name.lowercase().contains(query) || product.location.lowercase().contains(query))
        }

        displayProducts(filtered)
    }

    private fun displayProducts(products: List<Product>) {
        productListContainer.removeAllViews()
        for (product in products) {
            val itemView = layoutInflater.inflate(R.layout.item_marketplace, productListContainer, false)
            itemView.findViewById<TextView>(R.id.productName).text = product.name
            itemView.findViewById<TextView>(R.id.productWeight).text = product.weight
            itemView.findViewById<TextView>(R.id.productLocation).text = "📍 ${product.location}"
            itemView.findViewById<TextView>(R.id.productPrice).text = product.price
            itemView.findViewById<ImageView>(R.id.productImage).setImageResource(product.imageRes)
            productListContainer.addView(itemView)
        }
    }

    data class Product(
        val name: String,
        val weight: String,
        val location: String,
        val price: String,
        val imageRes: Int,
        val category: String
    )
}
