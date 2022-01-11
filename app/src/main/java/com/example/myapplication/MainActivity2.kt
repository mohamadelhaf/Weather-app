package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity() {

    lateinit var recyclerViewForecast: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        recyclerViewForecast = findViewById(R.id.recyclerViewForecast)

        val items= listOf(
        Day(R.drawable.ic_baseline_cloud_24,"mon","january",7,17.2,16.00,18.00,14.5,13.2,),
        Day(R.drawable.ic_baseline_cloud_24,"tue","january",8,17.2,16.00,18.00,14.5,13.2,),
        Day(R.drawable.ic_baseline_cloud_24,"wed","january",9,17.2,16.00,18.00,14.5,13.2,),
        Day(R.drawable.ic_baseline_cloud_24,"thu","january",10,17.2,16.00,18.00,14.5,13.2,),
        Day(R.drawable.ic_baseline_cloud_24,"fri","january",11,17.2,16.00,18.00,14.5,13.2,),
        Day(R.drawable.ic_baseline_cloud_24,"sat","january",12,17.2,16.00,18.00,14.5,13.2,),
        Day(R.drawable.ic_baseline_cloud_24,"su ","january",13,17.2,16.00,18.00,14.5,13.2,),

        )
        recyclerViewForecast.apply {
            layoutManager = LinearLayoutManager(this@MainActivity2)
            adapter = ForecastAdapter(items)
        }
    }
}