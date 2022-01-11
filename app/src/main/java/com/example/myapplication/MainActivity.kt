package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var Edit_City: EditText
    lateinit var Btn_Search: Button
    lateinit var Btn_forecast: Button
    lateinit var Btn_maps: Button
    lateinit var Btn_notifications: Button
    lateinit var Btn_camera:Button
    lateinit var iv_img_weather: ImageView
    lateinit var tv_Temperature: TextView
    lateinit var tv_City_Name: TextView
    lateinit var tv_Tempmin: TextView
    lateinit var tv_Tempmax: TextView
    lateinit var tv_pressure: TextView
    lateinit var tv_humidity: TextView
    lateinit var layout_weather: LinearLayout




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var database = FirebaseDatabase.getInstance().reference
        database.setValue("welcome")







        Edit_City = findViewById(R.id.Edit_City)
        Btn_Search = findViewById(R.id.Btn_Search)
        Btn_forecast = findViewById(R.id.Btn_forecast)
        Btn_maps = findViewById(R.id.Btn_maps)
        Btn_notifications = findViewById(R.id.Btn_notifications)
        Btn_camera = findViewById(R.id.Btn_camera)
        iv_img_weather = findViewById(R.id.iv_img_weather)
        tv_Temperature = findViewById(R.id.tv_temperature)
        tv_Tempmin = findViewById(R.id.tv_tempmin)
        tv_Tempmax = findViewById(R.id.tv_tempmax)
        tv_pressure = findViewById(R.id.tv_pressure)
        tv_humidity = findViewById(R.id.tv_humidity)
        tv_City_Name = findViewById(R.id.tv_city_name)
        layout_weather = findViewById(R.id.layout_weather)





        Btn_Search.setOnClickListener {
            val city = Edit_City.text.toString()

            if (city.isEmpty()){
                Toast.makeText(this, "City Name Can't Be Empty", Toast.LENGTH_SHORT).show()
            }else {
                getWeatherByCity(city)

            }
        }

        Btn_forecast.setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }

        Btn_maps.setOnClickListener {
            val intent = Intent(this,MapsActivity::class.java)
            startActivity(intent)
        }
        Btn_notifications.setOnClickListener {
            val intent =Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        Btn_camera.setOnClickListener {
            val intent =Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }

    }


    fun getWeatherByCity(city: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/weather/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val WeatherService = retrofit.create(WeatherService::class.java)



        val result = WeatherService.getWeatherByCity(city)

        result.enqueue(object : Callback<WeatherResult> {
            override fun onResponse(call: Call<WeatherResult>, response: Response<WeatherResult>) {
                if(response.isSuccessful){
                    val result = response.body()


                    Picasso.get().load("https://openweathermap.org/img/w/${result?.weather?.get(0)?.icon}.png").into(iv_img_weather)

                    tv_Temperature.text = "${result?.main?.temp} ºC"
                    tv_Tempmin.text = "${result?.main?.temp_min} ºC"
                    tv_Tempmax.text = "${result?.main?.temp_max} ºC"
                    tv_humidity.text = "${result?.main?.humidity} ºC"
                    tv_pressure.text = "${result?.main?.pressure} ºC"
                    tv_City_Name.text = result?.name
                    layout_weather.visibility = View.VISIBLE
                }

            }

            override fun onFailure(call: Call<WeatherResult>, t: Throwable) {
                Toast.makeText(applicationContext, "Error Server", Toast.LENGTH_SHORT ).show()
            }

        })

    }
}