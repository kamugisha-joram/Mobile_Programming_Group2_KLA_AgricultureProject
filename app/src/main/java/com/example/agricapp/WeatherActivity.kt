package com.example.agricapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        findViewById<ImageView>(R.id.backBtn).setOnClickListener { finish() }

        val forecastContainer = findViewById<LinearLayout>(R.id.forecastContainer)
        setupForecast(forecastContainer)
    }

    private fun setupForecast(container: LinearLayout) {
        val forecasts = listOf(
            Forecast("Monday", "30° / 22°"),
            Forecast("Tuesday", "29° / 21°"),
            Forecast("Wednesday", "28° / 22°"),
            Forecast("Thursday", "31° / 23°"),
            Forecast("Friday", "27° / 20°"),
            Forecast("Saturday", "26° / 19°"),
            Forecast("Sunday", "28° / 21°")
        )

        container.removeAllViews()
        for (forecast in forecasts) {
            val itemView = layoutInflater.inflate(R.layout.item_weather_forecast, container, false)
            itemView.findViewById<TextView>(R.id.dayText).text = forecast.day
            itemView.findViewById<TextView>(R.id.forecastTemp).text = forecast.temp
            container.addView(itemView)
        }
    }

    data class Forecast(val day: String, val temp: String)
}
