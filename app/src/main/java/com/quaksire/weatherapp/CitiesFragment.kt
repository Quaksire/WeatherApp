package com.quaksire.weatherapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CitiesFragment : Fragment() {

    private lateinit var layoutView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layoutView = inflater.inflate(R.layout.fragment_cities, container, false)

        layoutView.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_cities_to_add_city)
        }

        return layoutView
    }

}
