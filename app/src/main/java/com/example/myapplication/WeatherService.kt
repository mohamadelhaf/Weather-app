package com.example.myapplication


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

    companion object{
        const val API_KEY= "805d08b3c7c6068100abf674260ed710"
    }
    @GET("?units=metric&appid=$API_KEY")
    fun getWeatherByCity(@Query("q")city: String): Call<WeatherResult>
}