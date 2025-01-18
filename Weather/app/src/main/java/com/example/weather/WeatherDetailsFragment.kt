package com.example.weather


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class WeatherDetailsFragment : Fragment(R.layout.fragment_weather_details) {

    private lateinit var binding: FragmentWeatherDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWeatherDetailsBinding.bind(view)

        val weatherData = arguments?.getString("weather_data")
        binding.tvWeatherDetails.text = weatherData
    }
}
