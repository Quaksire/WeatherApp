package com.quaksire.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.quaksire.weatherapp.repositories.SettingsRepository
import com.quaksire.weatherapp.repositories.WeatherRepository
import javax.inject.Inject

class CityWeatherFragment : Fragment() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    @Inject
    lateinit var weatherRepository: WeatherRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as WeatherApplication).appComponent.inject(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_weather, container, false)
    }
}
