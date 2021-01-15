package com.example.exam

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/countries")
    fun getCountry(): Call<List<Country>>

    @GET("/country/{slug}")
    fun getStatistics(@Path("slug") slug: String): Call<Statistics>
}