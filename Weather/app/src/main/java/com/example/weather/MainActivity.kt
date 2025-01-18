package com.example.weather

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFetchWeather.setOnClickListener {
            val city = binding.etCity.text.toString()
            fetchWeather(city)
        }
    }

    private fun fetchWeather(city: String) {
        val apiKey = "your_api_key_here"
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitInstance.weatherApi.getWeather(city, apiKey)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    displayWeather(weatherResponse)
                } else {
                    binding.tvWeather.text = "Error: ${response.code()}"
                }
            }
        }
    }

    private fun displayWeather(weatherResponse: WeatherResponse?) {
        if (weatherResponse != null) {
            binding.tvWeather.text = "Temp: ${weatherResponse.main.temp}°C, ${weatherResponse.weather[0].description}"
        }
    }
}
