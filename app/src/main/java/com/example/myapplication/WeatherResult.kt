package com.example.myapplication

data class WeatherResult(
    var name: String,
    var main: MainJson,
    var weather: Array<WeatherJson>
)

data class MainJson(
    var temp: Double,
    var feels_like: Double,
    var temp_min: Double,
    var temp_max: Double,
    var pressure: Double,
    var humidity: Double
)

data class WeatherJson(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String


    )