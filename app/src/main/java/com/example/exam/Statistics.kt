package com.example.exam

data class Statistics (
    val ID: String,
    val country: String,
    val countryCode: String,
    val province: String,
    val city: String,
    val cityCode: String,
    val lat: Int,
    val lon: Int,
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int,
    val active: Int,
    val date: String
)