package com.example.agricapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CommunityTipsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_tips)

        findViewById<ImageView>(R.id.backBtn).setOnClickListener {
            finish()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.tipsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        val tips = listOf(
            Tip("Moses K.", "To avoid Fall Armyworm, plant early in the season before the pests multiply."),
            Tip("Sarah A.", "Using organic mulch helps retain moisture during dry spells in Mbarara."),
            Tip("John B.", "Intercropping maize with beans reduces the need for nitrogen fertilizers."),
            Tip("Grace N.", "Store your harvest in a dry place to prevent mold and aflatoxins.")
        )
        
        recyclerView.adapter = TipsAdapter(tips)
    }

    data class Tip(val author: String, val content: String)

    class TipsAdapter(private val tips: List<Tip>) : RecyclerView.Adapter<TipsAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val authorText: TextView = view.findViewById(R.id.tipAuthor)
            val contentText: TextView = view.findViewById(R.id.tipContent)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_community_tip, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val tip = tips[position]
            holder.authorText.text = tip.author
            holder.contentText.text = tip.content
        }

        override fun getItemCount() = tips.size
    }
}
