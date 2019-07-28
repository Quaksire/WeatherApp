package com.quaksire.weatherapp

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.quaksire.database.entity.CityEntity
import com.quaksire.weatherapp.adapters.AddCityAdapter
import com.quaksire.weatherapp.databinding.FragmentAddCityBinding
import com.quaksire.weatherapp.repositories.CitiesRepository
import com.quaksire.weatherapp.viewmodel.AddCityViewModel
import com.quaksire.weatherapp.viewmodel.AddCityViewModelFactory
import javax.inject.Inject

/**
 * Created by Julio.
 */
class AddCityFragment : Fragment() {

    @Inject
    lateinit var citiesRepository: CitiesRepository

    lateinit var cityAutoCompleteTextView: AutoCompleteTextView
    lateinit var cityAutoCompleteTextViewAdapter: AddCityAdapter

    // Initialize to default value (London)
    private var selectedElement : CityEntity = CityEntity(2643743, "London", "Uk")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as WeatherApplication).appComponent.inject(this)
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAddCityBinding>(inflater, R.layout.fragment_add_city, container, false)

        this.cityAutoCompleteTextView = binding.cityAutoCompleteTextView
        this.cityAutoCompleteTextViewAdapter = AddCityAdapter(context!!, citiesRepository)

        this.cityAutoCompleteTextView.threshold = 1
        this.cityAutoCompleteTextView.setAdapter(cityAutoCompleteTextViewAdapter)
        this.cityAutoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener{
                    parent, _, position, _->
                 selectedElement = parent.getItemAtPosition(position) as CityEntity
                cityAutoCompleteTextView.setText(selectedElement.name)
            }

        this.cityAutoCompleteTextView.onFocusChangeListener = View.OnFocusChangeListener{
                _, focused ->
            if (focused){
                // Display the suggestion dropdown on focus
                cityAutoCompleteTextView.showDropDown()
            }
        }

        subscribeUI()

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_location, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_location_menu_save) {
            selectedElement.selected = 1
            citiesRepository.addCity(selectedElement)
            return Navigation.findNavController(this.cityAutoCompleteTextView).popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun subscribeUI() {
        val factory = AddCityViewModelFactory(citiesRepository)
        val viewModel = ViewModelProviders.of(this, factory).get(AddCityViewModel::class.java)

        viewModel.cities.observe(viewLifecycleOwner, Observer { cities ->
            cityAutoCompleteTextViewAdapter.addCollection(cities)
            cityAutoCompleteTextViewAdapter.notifyDataSetChanged()
        })
    }
}
