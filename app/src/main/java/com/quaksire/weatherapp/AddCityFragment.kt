package com.quaksire.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.quaksire.weatherapp.databinding.FragmentAddCityBinding

class AddCityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as WeatherApplication).appComponent.inject(this)
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAddCityBinding>(inflater, R.layout.fragment_add_city, container, false)

        binding.cityAutoCompleteTextView.apply {

        }

        return binding.root
    }
}
