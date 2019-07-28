package com.quaksire.weatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.quaksire.database.entity.CityEntity
import com.quaksire.weatherapp.R
import com.quaksire.weatherapp.repositories.CitiesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Julio.
 */
class AddCityAdapter(private val context: Context, private val cityRepository: CitiesRepository) : BaseAdapter(), Filterable {

    private var resultList: MutableList<CityEntity> = ArrayList()

    override fun getCount(): Int {
        return resultList.size
    }

    override fun getItem(index: Int): CityEntity {
        return resultList[index]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.adapter_city_add, parent, false)
        }
        (convertView!!.findViewById<View>(R.id.cityName) as TextView).text = getItem(position).name
        return convertView
    }

    fun addCollection(citiesEntity: List<CityEntity>) {
        this.resultList.addAll(citiesEntity)
        this.notifyDataSetChanged()
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint != null) {
                    val cities = findCity(constraint.toString())
                    // Assign the data to the FilterResults
                    filterResults.values = cities
                    filterResults.count = cities.size
                }
                return filterResults
            }
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null && results.count > 0) {
                    resultList = results.values as ArrayList<CityEntity>
                    notifyDataSetChanged()
                } else {
                    notifyDataSetInvalidated()
                }
            }
        }
    }
    private fun findCity(cityName: String): List<CityEntity> {
        return cityRepository.searchCityByName(cityName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .blockingGet()
    }
}