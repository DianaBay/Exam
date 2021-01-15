package com.example.exam

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_country_list.*
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlinx.android.synthetic.main.recycler_item_statistics.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class StatisticsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_statistics, container, false)


        val statistics = ArrayList<Statistics>()
        for(i in 1..10){
            statistics.add(Statistics(" " + i, " ", " "))
        }
        val adapterStatistics = AdapterStatistics(statistics, this)

        val layoutManager = LinearLayoutManager(this)
        recyclerViewStatistics.layoutManager = layoutManager
        recyclerViewStatistics. adapter = adapterStatistics

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

        val statistics = args.statistics

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getCountry(statistics).enqueue(object: Callback<Statistics> {
            override fun onFailure(call: Call<Country>, t: Throwable){
                Log.e("Error", t.message!!)
            }
            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                Log.e("Response size: ", response.body()!!.statistics.size.toString()+"")
                list.addAll(response.body()!!.statistics)
            }
        })

        return view
    }
}