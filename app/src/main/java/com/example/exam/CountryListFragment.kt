package com.example.exam

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_country_list.*
import kotlinx.android.synthetic.main.recycler_item_country.*
import kotlinx.android.synthetic.main.recycler_item_country.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CountryListFragment: Fragment() {

    val args: CountryListFragmentArgs by navArgs()

    val list = mutableListOf<Country>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_country_list, container, false)

        view.countryText.setOnClickListener{
            val action = CountryListFragmentDirections.actionCountryListFragmentToStatisticsFragment(args.country)
            Navigation.findNavController(view).navigate(action)
        }

        val countries = ArrayList<Country>()
        for(i in 1..10){
            countries.add(Country(" " + i, " ", " "))
        }
        val adapterCountry = AdapterCountry(countries, this)

        val layoutManager = LinearLayoutManager(this)
        recyclerViewCountry.layoutManager = layoutManager
        recyclerViewCountry. adapter = adapterCountry

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor((loggingInterceptor))
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.covid19api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val country = args.country

        val apiService = retrofit.create(ApiService::class.java)


        apiService.getCountry(country).enqueue(object: Callback<Country>{
            override fun onFailure(call: Call<Country>, t: Throwable){
                Log.e("Error", t.message!!)
            }
            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                Log.e("Response size: ", response.body()!!.country.size.toString()+"")
                list.addAll(response.body()!!.country)
            }
        })
        return view

    }
}