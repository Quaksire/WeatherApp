package com.quaksire.weatherapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

class CitiesFragment : Fragment() {

    private lateinit var layoutView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layoutView = inflater.inflate(R.layout.fragment_cities, container, false)

        return layoutView
    }

}
