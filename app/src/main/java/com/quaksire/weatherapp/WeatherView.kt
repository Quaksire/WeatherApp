package com.quaksire.weatherapp

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter



/**
 * Created by Julio.
 */
class WeatherView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr) {

    private val imageView: ImageView
    private val temperatureTextView: TextView
    private val dayTextView: TextView

    init {
        View.inflate(context, R.layout.weather_view, this)

        imageView = findViewById(R.id.icon)
        temperatureTextView = findViewById(R.id.temperature)
        dayTextView = findViewById(R.id.day)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.WeatherView)
        temperatureTextView.text = attributes.getString(R.styleable.WeatherView_temperature)
        dayTextView.text = attributes.getString(R.styleable.WeatherView_day)
        attributes.recycle()
    }
}