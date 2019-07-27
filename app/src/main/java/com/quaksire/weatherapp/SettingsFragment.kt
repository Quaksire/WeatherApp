package com.quaksire.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.quaksire.database.entity.SettingsEntity
import com.quaksire.weatherapp.databinding.FragmentSettingsBinding
import com.quaksire.weatherapp.repositories.SettingsRepository
import com.quaksire.weatherapp.viewmodel.SettingsViewModel
import com.quaksire.weatherapp.viewmodel.SettingsViewModelFactory
import javax.inject.Inject

class SettingsFragment : Fragment() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    lateinit var temperatureSwitchCompat: SwitchCompat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as WeatherApplication).appComponent.inject(this)
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)

        val binding = DataBindingUtil.inflate<FragmentSettingsBinding>(inflater, R.layout.fragment_settings, container, false)

        this.temperatureSwitchCompat = binding.temperatureSwitch
        this.temperatureSwitchCompat.setOnCheckedChangeListener{_, value -> settingsRepository.saveSettings(SettingsEntity(1, value))}

        subscribeUI()

        return binding.root
    }

    private fun subscribeUI() {
        val factory = SettingsViewModelFactory(settingsRepository)
        val viewModel = ViewModelProviders.of(this, factory).get(SettingsViewModel::class.java)

        viewModel.displayFarenheit.observe(viewLifecycleOwner, Observer { settings ->
            temperatureSwitchCompat.isChecked = settings[0].displayTemperatureInFahrenheit
        })
    }
}
