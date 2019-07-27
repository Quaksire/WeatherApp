package com.quaksire.weatherapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
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

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_location, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
