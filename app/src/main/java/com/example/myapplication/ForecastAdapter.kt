package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ForecastAdapter(var items: List<Day>): RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ForecastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val Day =items[position]
        holder.bind(Day)


    }

    override fun getItemCount() = items.size


    inner class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView ) {

         var tv_day: TextView
         var tv_months: TextView
         var tv_num: TextView
         var tv_temp_forecast: TextView
         var tv_temp_min_forecast: TextView
         var tv_temp_max_forecast: TextView
         var tv_pressure_forecast: TextView
         var tv_humidity_forecast: TextView
         var iv_image_forecast: ImageView

        init {
            tv_day =itemView.findViewById(R.id.tv_day)
            tv_months =itemView.findViewById(R.id.tv_months)
            tv_num = itemView.findViewById(R.id.tv_num)
            tv_temp_forecast =itemView.findViewById(R.id.tv_temp_forecast)
            tv_temp_min_forecast =itemView.findViewById(R.id.tv_temp_min_forecast)
            tv_temp_max_forecast =itemView.findViewById(R.id.tv_temp_max_forecast)
            tv_pressure_forecast =itemView.findViewById(R.id.tv_pressure_forecast)
            tv_humidity_forecast =itemView.findViewById(R.id.tv_humidity_forecast)
            iv_image_forecast =itemView.findViewById(R.id.image_forecast)
        }
        fun bind(day: Day){
            //Picasso.get().load("https://openweathermap.org/img/w/${result?.weather?.get(0)?.icon}.png").into(image_forecast)
            //iv_image_forecast
            tv_day.text= day.day_forecast
            tv_months.text = day.month_forecast
            tv_num.text= day.num_forecast.toString()
            tv_temp_forecast.text = day.temp_forecast.toString()
            tv_temp_min_forecast.text = day.temp_min_forecast.toString()
            tv_temp_max_forecast.text = day.temp_max_forecast.toString()
            tv_humidity_forecast.text = day.humidity_forecast.toString()
            tv_pressure_forecast.text = day.pressure_forecast.toString()
        }

    }
}


