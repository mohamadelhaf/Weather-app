package com.example.myapplication

import android.icu.text.CaseMap

data class Day(

    var image_forecast: Int,
    var day_forecast: String,
    var month_forecast: String,
    var num_forecast: Int,
    var temp_forecast: Double,
    var temp_min_forecast: Double,
    var temp_max_forecast: Double,
    var humidity_forecast: Double,
    var pressure_forecast: Double,


)
