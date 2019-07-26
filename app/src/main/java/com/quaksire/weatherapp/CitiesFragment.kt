package com.quaksire.weatherapp

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.quaksire.weatherapp.adapters.CitiesAdapter
import com.quaksire.weatherapp.databinding.FragmentCitiesBinding
import com.quaksire.weatherapp.repositories.CitiesRepository
import com.quaksire.weatherapp.viewmodel.CitiesViewModel
import com.quaksire.weatherapp.viewmodel.CitiesViewModelFactory
import javax.inject.Inject

class CitiesFragment : Fragment() {

    @Inject
    lateinit var repository: CitiesRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as WeatherApplication).appComponent.inject(this)

        val binding = DataBindingUtil.inflate<FragmentCitiesBinding>(inflater, R.layout.fragment_cities, container, false)

        val viewAdapter = CitiesAdapter()

        binding.fab.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_cities_to_add_city)
        }

        binding.citiesRecyclerView.apply {
            setHasOptionsMenu(true)
            adapter = viewAdapter

        }

        subscribeUI(viewAdapter)

        return binding.root
    }

    private fun subscribeUI(adapter: CitiesAdapter) {
        val factory = CitiesViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(CitiesViewModel::class.java)

        viewModel.cities.observe(viewLifecycleOwner, Observer { cities ->
            adapter.addCollection(cities)
        })
    }

}
