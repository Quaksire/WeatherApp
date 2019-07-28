package com.quaksire.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.quaksire.weatherapp.databinding.FragmentCityWeatherBinding
import com.quaksire.weatherapp.repositories.SettingsRepository
import com.quaksire.weatherapp.repositories.WeatherRepository
import com.quaksire.weatherapp.viewmodel.CityViewModel
import com.quaksire.weatherapp.viewmodel.CityViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers.*
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CityWeatherFragment : Fragment() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    @Inject
    lateinit var weatherRepository: WeatherRepository

    lateinit var binding: FragmentCityWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as WeatherApplication).appComponent.inject(this)
        // Inflate the layout for this fragment
        this.binding = DataBindingUtil.inflate<FragmentCityWeatherBinding>(inflater, R.layout.fragment_city_weather, container, false)

        val cityId = arguments!!.getInt("cityId", 0)
        val cityName = arguments!!.getString("cityName")

        (activity as MainActivity).supportActionBar?.title = cityName
        
        subscribeUI(cityId)

        return this.binding.root
    }

    private fun subscribeUI(cityId: Int) {
        val factory = CityViewModelFactory(settingsRepository)
        val viewModel = ViewModelProviders.of(this, factory).get(CityViewModel::class.java)

        viewModel.settings.observe(this, Observer { settings ->
            val useImperial = if (settings.isNotEmpty()) settings[0].displayTemperatureInFahrenheit else false
            weatherRepository.getWeather(cityId, useImperial)
                .observeOn(mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe (
                    {
                        binding.currentWeather = it.list[0]
                        binding.plusOne = it.list[8]
                        binding.plusTwo = it.list[16]
                        binding.plusThree = it.list[24]
                        binding.plusFour = it.list[32]

                        loadImage(binding.current, it.list[0].weather[0].icon)
                        loadImage(binding.icon1, it.list[8].weather[0].icon)
                        loadImage(binding.icon2, it.list[16].weather[0].icon)
                        loadImage(binding.icon3, it.list[24].weather[0].icon)
                        loadImage(binding.icon4, it.list[32].weather[0].icon)
                    },
                    { Log.e("QUAKSIRE", it.localizedMessage) }
            )
        })
    }

    private fun loadImage(image: ImageView, icon: String) {
        Glide.with(binding.root)
            .load(BuildConfig.WEATHER_ICONS_URL + icon + ".png")
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(image)
    }
}
