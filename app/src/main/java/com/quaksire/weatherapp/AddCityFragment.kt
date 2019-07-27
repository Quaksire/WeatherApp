package com.quaksire.weatherapp

import android.os.Bundle
import android.view.*
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.quaksire.database.entity.CityEntity
import com.quaksire.weatherapp.databinding.FragmentAddCityBinding
import com.quaksire.weatherapp.repositories.CitiesRepository
import javax.inject.Inject

class AddCityFragment : Fragment() {

    @Inject
    lateinit var citiesRepository: CitiesRepository

    lateinit var cityAutoCompleteTextView: AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as WeatherApplication).appComponent.inject(this)
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAddCityBinding>(inflater, R.layout.fragment_add_city, container, false)

        this.cityAutoCompleteTextView = binding.cityAutoCompleteTextView

        this.cityAutoCompleteTextView.apply {

        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_location, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_location_menu_save) {
            citiesRepository.addCity(CityEntity(2643743, "London", "Uk"))
            return Navigation.findNavController(this.cityAutoCompleteTextView).popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }
}
