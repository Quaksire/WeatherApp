package com.quaksire.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.quaksire.database.entity.CityEntity
import com.quaksire.weatherapp.R

/**
 * Created by Julio.
 */
class CitiesAdapter : RecyclerView.Adapter<CitiesAdapter.CityItemViewHolder>() {

    private val citiesCollection = ArrayList<CityEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityItemViewHolder {
        val cityView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_city_item, parent, false)
        return CityItemViewHolder(cityView)
    }

    override fun onBindViewHolder(holder: CityItemViewHolder, position: Int) {
        holder.cityView.findViewById<TextView>(R.id.cityName).text = citiesCollection[position].name
        holder.cityView.setOnClickListener{
            val bundle = bundleOf("cityId" to citiesCollection[position].cityId)
            Navigation.findNavController(holder.itemView).navigate(R.id.action_cities_to_city, bundle)
        }
    }

    override fun getItemCount(): Int {
        return this.citiesCollection.size
    }

    fun addCollection(cities: List<CityEntity>) {
        this.citiesCollection.clear()
        this.citiesCollection.addAll(cities)
        notifyDataSetChanged()
    }

    class CityItemViewHolder(val cityView: View) : RecyclerView.ViewHolder(cityView)
}